package ciolty.Implementation.actions;

import ciolty.Implementation.entities.ConsumerData;
import ciolty.Implementation.entities.DistributorData;

import java.util.List;

public class DistributorsPayAction extends ImplementedAction {
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

    private void pay(DistributorData distributor) {
        if (canPay(distributor)) {
            distributor.setBudget(distributor.getBudget() - getTotalPrice(distributor));
            System.out.println("Distributor #" + distributor.getId()
                    + " paid " + getTotalPrice(distributor));
            System.out.println("Distributor #" + distributor.getId()
                    + " budget = " + distributor.getBudget());
        } else {
            distributor.setBankrupt(true);
            System.out.println("Distributor #" + distributor.getId()
                    + " filled bankruptcy!");
        }
    }

    private boolean canPay(DistributorData distributor) {
        return distributor.getBudget() >= getTotalPrice(distributor);
    }

    private int getTotalPrice(DistributorData distributor) {
        return distributor.getInfrastructureCost()
                + distributor.getNumberOfClients() * distributor.getProductionCost();
    }
}
