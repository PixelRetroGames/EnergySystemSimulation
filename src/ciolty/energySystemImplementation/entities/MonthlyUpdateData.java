package ciolty.energySystemImplementation.entities;

import java.util.List;

public final class MonthlyUpdateData extends Data {
    private List<ConsumerData> newConsumers;
    private List<DistributorChangeData> distributorChanges;
    private List<ProducerChangeData> producerChanges;

    public List<ConsumerData> getNewConsumers() {
        return newConsumers;
    }

    public List<DistributorChangeData> getDistributorChanges() {
        return distributorChanges;
    }

    public List<ProducerChangeData> getProducerChanges() {
        return producerChanges;
    }

    public MonthlyUpdateData() {
        super("monthly-update");
    }

    public void setNewConsumers(final List<ConsumerData> newConsumers) {
        this.newConsumers = newConsumers;
    }

    public void setDistributorChanges(final List<DistributorChangeData> distributorChanges) {
        this.distributorChanges = distributorChanges;
    }

    public void setProducerChanges(List<ProducerChangeData> producerChanges) {
        this.producerChanges = producerChanges;
    }
}
