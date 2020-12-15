package ciolty.energySystemImplementation.actions;

import ciolty.energySystemImplementation.debugger.DebugLogger;
import ciolty.energySystemImplementation.entities.DistributorData;

import java.util.List;

public final class MonthEndedAction extends ImplementedAction {
    private List<DistributorData> distributors;

    @Override
    public String start() {
        distributors = getUnitOfWork().getDistributorRepository().getActiveDistributors();
        return null;
    }

    @Override
    public String execute() {
        DebugLogger.log("Month ended!");
        distributors.forEach(distributor -> {
            distributor.getActiveContracts().forEach(contract -> {
                contract.setRemainedContractMonths(contract.getRemainedContractMonths() - 1);
            });
        });
        return null;
    }
}
