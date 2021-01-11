package ciolty.energySystemImplementation.actions;

import ciolty.energySystemImplementation.entities.MonthlyUpdateData;
import ciolty.energySystemImplementation.entities.ProducerChangeData;
import ciolty.energySystemImplementation.entities.ProducerData;

import java.util.List;

public final class MonthlyProducersUpdateAction extends ImplementedAction {
    private MonthlyUpdateData data;

    @Override
    public String start() {
        data = (MonthlyUpdateData) actionData;
        if (data.getDistributorChanges() == null) {
            return "Data is null";
        }

        return null;
    }

    @Override
    public String execute() {
        List<ProducerChangeData> costsChanges = data.getProducerChanges();
        for (ProducerChangeData costChange : costsChanges) {
            ProducerData producer = getUnitOfWork().getProducerRepository()
                    .get(Integer.toString(costChange.getId()));
            producer.setEnergyPerDistributor(costChange.getEnergyPerDistributor());
            for (int distributorId : producer.getRegisteredDistributors()) {
                getUnitOfWork().getDistributorRepository()
                        .get(Integer.toString(distributorId)).setChangedProducers(true);
            }
        }
        return null;
    }
}
