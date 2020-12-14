package ciolty.Implementation.entities;

import ciolty.engine.server.Input;

import java.util.List;

public class InputData implements Input {
    public int numberOfTurns;

    public InputData() {
        super();
    }

    public InputData(int numberOfTurns, InitialData initialData, List<MonthlyUpdateData> monthlyUpdates) {
        this.numberOfTurns = numberOfTurns;
        this.initialData = initialData;
        this.monthlyUpdates = monthlyUpdates;
    }

    public List<ConsumerData> getConsumers() {
        return initialData.consumers;
    }

    public List<DistributorData> getDistributors() {
        return initialData.distributors;
    }

    public class InitialData {
        public List<ConsumerData> consumers;
        public List<DistributorData> distributors;

        public InitialData() {
            super();
        }

        public InitialData(List<ConsumerData> consumers, List<DistributorData> distributors) {
            this.consumers = consumers;
            this.distributors = distributors;
        }
    }

    public InitialData initialData;
    public List<MonthlyUpdateData> monthlyUpdates;

    public List<MonthlyUpdateData> getMonthlyUpdates() {
        return monthlyUpdates;
    }
}
