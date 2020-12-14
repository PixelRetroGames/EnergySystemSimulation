package ciolty.Implementation.actions;

import ciolty.Implementation.entities.DistributorData;

import java.util.List;

public class DistributorSetPriceAction extends ImplementedAction {
    private List<DistributorData> distributors;

    @Override
    public String start() {
        distributors = getUnitOfWork().getDistributorRepository().getActiveDistributors();
        return null;
    }

    @Override
    public String execute() {
        for (DistributorData distributor: distributors) {
            distributor.setContractPrice(getNewContractPrice(distributor));
            System.out.println("Distributor #" + distributor.getId()
                    + " set price to " + distributor.getContractPrice());
        }
        return null;
    }

    private static final int getNewContractPrice(DistributorData distributor) {
        int profit = (int) Math.round(Math.floor(0.2 * distributor.getProductionCost()));
        int price;
        if (distributor.getNumberOfClients() == 0) {
            price = distributor.getInfrastructureCost()
                    + distributor.getProductionCost() + profit;
        } else {
            price = (int) Math.round(Math.floor(
                    distributor.getInfrastructureCost() / distributor.getNumberOfClients()));
        }
        return price;
    }
}
