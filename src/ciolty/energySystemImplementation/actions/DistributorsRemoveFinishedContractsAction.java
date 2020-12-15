package ciolty.energySystemImplementation.actions;

import ciolty.energySystemImplementation.debugger.DebugLogger;
import ciolty.energySystemImplementation.entities.DistributorData;

import java.util.List;

public final class DistributorsRemoveFinishedContractsAction extends ImplementedAction {
    private List<DistributorData> distributors;

    @Override
    public String start() {
        distributors = getUnitOfWork().getDistributorRepository().getActiveDistributors();
        return null;
    }

    @Override
    public String execute() {
        DebugLogger.log("Distributors removed finished contracts!");
        for (DistributorData distributor : distributors) {
            removeFinishedContracts(distributor);
        }
        return null;
    }

    private void removeFinishedContracts(final DistributorData distributor) {
        distributor.getActiveContracts().removeIf(contract -> {
            return contract.getRemainedContractMonths() == 0;
        });
    }
}
