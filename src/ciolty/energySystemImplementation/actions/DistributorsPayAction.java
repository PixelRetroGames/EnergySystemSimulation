package ciolty.energySystemImplementation.actions;

import ciolty.energySystemImplementation.debugger.DebugLogger;
import ciolty.energySystemImplementation.entities.DistributorData;

import java.util.List;

public final class DistributorsPayAction extends ImplementedAction {
    private List<DistributorData> distributors;

    @Override
    public String start() {
        distributors = getUnitOfWork().getDistributorRepository().getActiveDistributors();
        return null;
    }

    @Override
    public String execute() {
        for (DistributorData distributor : distributors) {
            pay(distributor);
        }
        return null;
    }

    private void pay(final DistributorData distributor) {
        if (canPay(distributor)) {
            distributor.setBudget(distributor.getBudget() - getTotalPrice(distributor));
            DebugLogger.log("Distributor #" + distributor.getId()
                            + " paid " + getTotalPrice(distributor));
            DebugLogger.log("Distributor #" + distributor.getId()
                            + " budget = " + distributor.getBudget());
        } else {
            distributor.setBudget(distributor.getBudget() - getTotalPrice(distributor));
            distributor.setBankrupt(true);
            DebugLogger.log("Distributor #" + distributor.getId()
                            + " filled bankruptcy!");
        }
    }

    private boolean canPay(final DistributorData distributor) {
        return distributor.getBudget() >= getTotalPrice(distributor);
    }

    private int getTotalPrice(final DistributorData distributor) {
        return distributor.getInfrastructureCost()
                + distributor.getNumberOfClients() * distributor.getProductionCost();
    }
}
