package ciolty.energySystemImplementation.entities;

import java.util.List;

public final class MonthlyUpdateData extends Data {
    private List<ConsumerData> newConsumers;
    private List<CostChangeData> costsChanges;

    public List<ConsumerData> getNewConsumers() {
        return newConsumers;
    }

    public List<CostChangeData> getCostsChanges() {
        return costsChanges;
    }

    public MonthlyUpdateData() {
        super("monthly-update");
    }

    public void setNewConsumers(final List<ConsumerData> newConsumers) {
        this.newConsumers = newConsumers;
    }

    public void setCostsChanges(final List<CostChangeData> costsChanges) {
        this.costsChanges = costsChanges;
    }
}
