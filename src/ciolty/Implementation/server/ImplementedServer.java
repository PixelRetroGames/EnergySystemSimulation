package ciolty.Implementation.server;

import ciolty.Implementation.actions.*;
import ciolty.Implementation.controllers.ImplementedController;
import ciolty.Implementation.entities.Data;
import ciolty.Implementation.entities.InputData;
import ciolty.Implementation.entities.OutputData;
import ciolty.Implementation.repositories.ImplementedUnitOfWork;
import ciolty.engine.action.ActionData;
import ciolty.Implementation.actions.ConsumersGetPaidAction;
import ciolty.engine.action.Output;
import ciolty.engine.server.ServerAbstract;

import java.util.Map;

public final class ImplementedServer extends ServerAbstract {
    private InputData input;
    public ImplementedServer(final InputData input) {
        this.input = input;
        unitOfWork = new ImplementedUnitOfWork();
        unitOfWork.populate(input);

        actionController = new ImplementedController(unitOfWork, Map.ofEntries(
            Map.entry("monthly-update",                 MonthlyUpdateAction::new),
            Map.entry("distributor-set-price",          DistributorSetPriceAction::new),
            Map.entry("consumers-choose-contract",      ConsumersChooseContractAction::new),
            Map.entry("consumers-get-paid",             ConsumersGetPaidAction::new),
            Map.entry("consumers-pay-distributors",     ConsumersPayDistributorsAction::new),
            Map.entry("distributors-pay-action",        DistributorsPayAction::new),
            Map.entry("distributors-clean-contracts",   DistributorCleanContractsAction::new),
            Map.entry("consumers-clean-contracts",      ConsumersCleanContractsAction::new),
            Map.entry("month-ended-action",             MonthEndedAction::new)));
    }

    @Override
    public void runAllActions() {
        runMonthlyActions();
        for (int i = 0; i < input.getMonthlyUpdates().size(); i++) {
            runAction(i);
        }
    }

    private void runInitialRound() {
        runMonthlyActions();
    }

    private void runMonthlyActions() {
        ActionData actionData = new Data("distributor-set-price");
        actionController.execute(actionData);
        actionData = new Data("consumers-choose-contract");
        actionController.execute(actionData);
        actionData = new Data("consumers-get-paid");
        actionController.execute(actionData);
        actionData = new Data("consumers-pay-distributors");
        actionController.execute(actionData);
        actionData = new Data("distributors-pay-action");
        actionController.execute(actionData);

        actionData = new Data("month-ended-action");
        actionController.execute(actionData);

        actionData = new Data("consumers-clean-contracts");
        actionController.execute(actionData);
        actionData = new Data("distributors-clean-contracts");
        actionController.execute(actionData);
    }

    private void runAction(final int position) {
        ActionData actionData = input.getMonthlyUpdates().get(position);
        actionController.execute(actionData);

        runMonthlyActions();
    }

    @Override
    public Output getOutput() {
        if (output == null) {
            populateOutput();
        }
        return output;
    }

    private void populateOutput() {
        ImplementedUnitOfWork implementedUnitOfWork = (ImplementedUnitOfWork) unitOfWork;
        output = new OutputData(implementedUnitOfWork.getConsumerRepository().getAll(),
                implementedUnitOfWork.getDistributorRepository().getAll());
    }
}
