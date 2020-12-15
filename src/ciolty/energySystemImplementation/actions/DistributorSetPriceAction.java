package ciolty.energySystemImplementation.actions;

import ciolty.energySystemImplementation.debugger.DebugLogger;
import ciolty.energySystemImplementation.entities.DistributorData;

import java.util.List;

public final class DistributorSetPriceAction extends ImplementedAction {
    private List<DistributorData> distributors;
    private static final double PROFIT_FACTOR = 0.2;

    @Override
    public String start() {
        distributors = getUnitOfWork().getDistributorRepository().getActiveDistributors();
        return null;
    }

    @Override
    public String execute() {
        for (DistributorData distributor: distributors) {
            distributor.setContractPrice(getNewContractPrice(distributor));
            DebugLogger.log("Distributor #" + distributor.getId()
                            + " set price to " + distributor.getContractPrice());
        }
        return null;
    }

    private static int getNewContractPrice(final DistributorData distributor) {
        int profit = (int) Math.round(Math.floor(PROFIT_FACTOR * distributor.getProductionCost()));
        int price;
        if (distributor.getNumberOfClients() == 0) {
            price = distributor.getInfrastructureCost()
                    + distributor.getProductionCost() + profit;
        } else {
            price = (int) Math.round(Math.floor(
                    1.0 * distributor.getInfrastructureCost() / distributor.getNumberOfClients()
                    + distributor.getProductionCost()
                    + profit));
        }
        return price;
    }
}
