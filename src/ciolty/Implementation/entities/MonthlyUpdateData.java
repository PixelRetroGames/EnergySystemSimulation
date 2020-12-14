package ciolty.Implementation.entities;

import java.util.List;

public class MonthlyUpdateData extends Data {
    public List<ConsumerData> newConsumers;
    public List<CostChangeData> costsChanges;

    public List<ConsumerData> getNewConsumers() {
        return newConsumers;
    }

    public List<CostChangeData> getCostsChanges() {
        return costsChanges;
    }

    public MonthlyUpdateData() {
        super("monthly-update");
    }
}
