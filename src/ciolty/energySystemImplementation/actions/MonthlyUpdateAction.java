package ciolty.energySystemImplementation.actions;

import ciolty.energySystemImplementation.debugger.DebugLogger;
import ciolty.energySystemImplementation.entities.ConsumerData;
import ciolty.energySystemImplementation.entities.DistributorChangeData;
import ciolty.energySystemImplementation.entities.DistributorData;
import ciolty.energySystemImplementation.entities.MonthlyUpdateData;

import java.util.List;

public final class MonthlyUpdateAction extends ImplementedAction {
    private MonthlyUpdateData data;

    @Override
    public String start() {
        data = (MonthlyUpdateData) actionData;
        return null;
    }

    @Override
    public String execute() {
        DebugLogger.log("Monthly update!");
        addConsumers(data.getNewConsumers());
        changeCostsDistributors(data.getDistributorChanges());
        return null;
    }

    private void addConsumers(final List<ConsumerData> newConsumers) {
        for (ConsumerData newConsumer : newConsumers) {
            getUnitOfWork().getConsumerRepository()
                    .add(Integer.toString(newConsumer.getId()), newConsumer);
        }
    }

    private void changeCostsDistributors(final List<DistributorChangeData> costsChanges) {
        for (DistributorChangeData costChange : costsChanges) {
            DistributorData distributor = getUnitOfWork().getDistributorRepository()
                    .get(Integer.toString(costChange.getId()));
            distributor.setInfrastructureCost(costChange.getInfrastructureCost());
        }
    }
}
