package ciolty.Implementation.actions;

import ciolty.Implementation.entities.ConsumerData;
import ciolty.Implementation.entities.CostChangeData;
import ciolty.Implementation.entities.DistributorData;
import ciolty.Implementation.entities.MonthlyUpdateData;

import java.util.List;

public class MonthlyUpdateAction extends ImplementedAction {
    MonthlyUpdateData data;
    @Override
    public String start() {
        data = (MonthlyUpdateData) actionData;
        System.out.println("started");
        return null;
    }

    @Override
    public String execute() {
        addConsumers(data.getNewConsumers());
        changeCosts(data.getCostsChanges());
        return null;
    }

    private void addConsumers(List<ConsumerData> newConsumers) {
        for (ConsumerData newConsumer : newConsumers) {
            getUnitOfWork().getConsumerRepository()
                    .add(Integer.toString(newConsumer.getId()), newConsumer);
        }
    }

    private void changeCosts(List<CostChangeData> costsChanges) {
        for (CostChangeData costChange : costsChanges) {
            DistributorData distributor = getUnitOfWork().getDistributorRepository()
                    .get(Integer.toString(costChange.getId()));
            distributor.setInfrastructureCost(costChange.getInfrastructureCost());
            distributor.setProductionCost(costChange.getProductionCost());
        }
    }
}
