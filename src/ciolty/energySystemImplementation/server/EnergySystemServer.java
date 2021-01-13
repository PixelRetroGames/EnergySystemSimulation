package ciolty.energySystemImplementation.server;

import ciolty.energySystemImplementation.JSON.JsonOutputConverter;
import ciolty.energySystemImplementation.actions.ConsumersChooseContractAction;
import ciolty.energySystemImplementation.actions.ConsumersCleanContractsAction;
import ciolty.energySystemImplementation.actions.ConsumersGetPaidAction;
import ciolty.energySystemImplementation.actions.ConsumersPayDistributorsAction;
import ciolty.energySystemImplementation.actions.DistributorSetPriceAction;
import ciolty.energySystemImplementation.actions.DistributorsChooseProducersAction;
import ciolty.energySystemImplementation.actions.DistributorsKickBankruptConsumersAction;
import ciolty.energySystemImplementation.actions.DistributorsPayAction;
import ciolty.energySystemImplementation.actions.DistributorsRemoveFinishedContractsAction;
import ciolty.energySystemImplementation.actions.MonthEndedAction;
import ciolty.energySystemImplementation.actions.MonthlyProducersUpdateAction;
import ciolty.energySystemImplementation.actions.MonthlyUpdateAction;
import ciolty.energySystemImplementation.controllers.EnergySystemController;
import ciolty.energySystemImplementation.debugger.DebuggingVariables;
import ciolty.energySystemImplementation.entities.Data;
import ciolty.energySystemImplementation.entities.InputData;
import ciolty.energySystemImplementation.entities.MonthlyUpdateData;
import ciolty.energySystemImplementation.entities.OutputData;
import ciolty.energySystemImplementation.repositories.EnergySystemUnitOfWork;
import ciolty.engine.JSON.Writer;
import ciolty.engine.action.ActionData;
import ciolty.engine.action.Output;
import ciolty.engine.server.ServerAbstract;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class EnergySystemServer extends ServerAbstract {
    private final InputData input;
    private int currentRound = -1;
    private final String debugPath;
    private final List<String> monthlyActions;
    private final List<String> initialRoundActions;

    public EnergySystemServer(final InputData input, final String debugPath) {
        this.input = input;
        this.debugPath = debugPath;
        unitOfWork = new EnergySystemUnitOfWork();
        unitOfWork.populate(input);

        actionController = new EnergySystemController(unitOfWork, Map.ofEntries(
        Map.entry("monthly-update",             MonthlyUpdateAction::new),
        Map.entry("distributor-set-price",      DistributorSetPriceAction::new),
        Map.entry("consumers-choose-contract",  ConsumersChooseContractAction::new),
        Map.entry("consumers-get-paid",         ConsumersGetPaidAction::new),
        Map.entry("consumers-pay-distributors", ConsumersPayDistributorsAction::new),
        Map.entry("distributors-pay-action",    DistributorsPayAction::new),
        Map.entry("distributors-remove-finished-contracts",
                                                   DistributorsRemoveFinishedContractsAction::new),
        Map.entry("distributors-kick-bankrupt-consumers",
                                                   DistributorsKickBankruptConsumersAction::new),
        Map.entry("consumers-clean-contracts",  ConsumersCleanContractsAction::new),
        Map.entry("month-ended",                MonthEndedAction::new),
        Map.entry("distributors-choose-producers", DistributorsChooseProducersAction::new),
        Map.entry("monthly-producers-update",   MonthlyProducersUpdateAction::new)));

        monthlyActions = new ArrayList<String>();
        monthlyActions.addAll(Arrays.asList(
                "monthly-update",
                "distributor-set-price",
                "consumers-clean-contracts",
                "distributors-remove-finished-contracts",
                "consumers-choose-contract",
                "consumers-get-paid",
                "consumers-pay-distributors",
                "distributors-pay-action",
                "distributors-kick-bankrupt-consumers",
                "monthly-producers-update",
                "distributors-choose-producers",
                "month-ended"));

        initialRoundActions = new ArrayList<String>();
        initialRoundActions.addAll(Arrays.asList(
                "distributors-choose-producers",
                "distributor-set-price",
                "consumers-choose-contract",
                "consumers-get-paid",
                "consumers-pay-distributors",
                "distributors-pay-action",
                "distributors-kick-bankrupt-consumers",
                "month-ended"));
    }

    private void print() {
        if (DebuggingVariables.isDebuggingActive()) {
            populateOutput();
            Writer writer = new Writer(debugPath + currentRound + ".json");
            writer.write(JsonOutputConverter.convert(getOutput()));
            writer.close();
        }
    }

    @Override
    public void runAllActions() {
        runInitialRound();
        for (int i = 0; i < input.getMonthlyUpdates().size(); i++) {
            runAction(i);
        }
    }

    private void runInitialRound() {
        initialRoundActions.forEach(action -> {
            ActionData actionData = new Data(action);
            actionController.execute(actionData);
        });
        print();
        currentRound++;
    }

    private void runMonthlyActions(MonthlyUpdateData monthlyUpdateData) {
        monthlyActions.forEach(action -> {
            ActionData actionData = new MonthlyUpdateData(monthlyUpdateData, action);
            actionController.execute(actionData);
        });
    }

    private void runAction(final int position) {
        MonthlyUpdateData actionData = input.getMonthlyUpdates().get(position);
        runMonthlyActions(actionData);
        print();
        currentRound++;
    }

    @Override
    public Output getOutput() {
        if (output == null) {
            populateOutput();
        }
        return output;
    }

    private void populateOutput() {
        EnergySystemUnitOfWork energySystemUnitOfWork = (EnergySystemUnitOfWork) unitOfWork;
        output = new OutputData(energySystemUnitOfWork.getConsumerRepository().getAll(),
                energySystemUnitOfWork.getDistributorRepository().getAll(),
                energySystemUnitOfWork.getProducerRepository().getAll());
    }
}
