package ciolty.energySystemImplementation.entities;

import ciolty.engine.server.Input;

import java.util.List;

public final class InputData implements Input {
    private int numberOfTurns;
    private InitialData initialData;
    private List<MonthlyUpdateData> monthlyUpdates;

    public InputData() {
        super();
    }

    public InputData(final int numberOfTurns, final InitialData initialData,
                     final List<MonthlyUpdateData> monthlyUpdates) {
        this.numberOfTurns = numberOfTurns;
        this.initialData = initialData;
        this.monthlyUpdates = monthlyUpdates;
    }

    public InitialData getInitialData() {
        return initialData;
    }

    public void setInitialData(final InitialData initialData) {
        this.initialData = initialData;
    }

    public void setMonthlyUpdates(final List<MonthlyUpdateData> monthlyUpdates) {
        this.monthlyUpdates = monthlyUpdates;
    }

    public List<ConsumerData> getConsumers() {
        return initialData.consumers;
    }

    public List<DistributorData> getDistributors() {
        return initialData.distributors;
    }

    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    public void setNumberOfTurns(final int numberOfTurns) {
        this.numberOfTurns = numberOfTurns;
    }

    public List<MonthlyUpdateData> getMonthlyUpdates() {
        return monthlyUpdates;
    }

    public final class InitialData {
        private List<ConsumerData> consumers;
        private List<DistributorData> distributors;

        public List<ConsumerData> getConsumers() {
            return consumers;
        }

        public List<DistributorData> getDistributors() {
            return distributors;
        }

        public void setConsumers(final List<ConsumerData> consumers) {
            this.consumers = consumers;
        }

        public void setDistributors(final List<DistributorData> distributors) {
            this.distributors = distributors;
        }
    }
}
