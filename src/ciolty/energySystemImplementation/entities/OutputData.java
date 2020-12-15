package ciolty.energySystemImplementation.entities;

import ciolty.engine.action.Output;

import java.util.ArrayList;
import java.util.List;

public final class OutputData implements Output {
    private final List<ConsumerOutputData> consumers = new ArrayList<>();
    private final List<DistributorOutputData> distributors = new ArrayList<>();

    public List<ConsumerOutputData> getConsumers() {
        return consumers;
    }

    public List<DistributorOutputData> getDistributors() {
        return distributors;
    }

    public OutputData() {
    }

    public OutputData(final List<ConsumerData> consumers,
                      final List<DistributorData> distributors) {
        consumers.forEach(consumer -> this.consumers.add(new ConsumerOutputData(consumer)));
        distributors.forEach(distributor -> {
            this.distributors.add(new DistributorOutputData(distributor));
        });
    }

    private static class ConsumerOutputData {
        private int id;
        private boolean isBankrupt;
        private int budget;

        public int getId() {
            return id;
        }

        public boolean getIsBankrupt() {
            return isBankrupt;
        }

        public int getBudget() {
            return budget;
        }

        ConsumerOutputData(final ConsumerData data) {
            id = data.getId();
            isBankrupt = data.isBankrupt();
            budget = data.getBudget();
        }
    }

    private static class DistributorOutputData {
        private final int id;
        private final int budget;
        private final boolean isBankrupt;
        private final List<ContractOutputData> contracts;

        public int getId() {
            return id;
        }

        public int getBudget() {
            return budget;
        }

        public boolean getIsBankrupt() {
            return isBankrupt;
        }

        public List<ContractOutputData> getContracts() {
            return contracts;
        }

        DistributorOutputData(final DistributorData data) {
            id = data.getId();
            budget = data.getBudget();
            isBankrupt = data.isBankrupt();
            contracts = new ArrayList<ContractOutputData>();
            data.getActiveContracts()
                    .forEach(contract -> contracts.add(new ContractOutputData(contract)));
        }

        private static class ContractOutputData {
            private final int consumerId;
            private final int price;
            private final int remainedContractMonths;

            public int getConsumerId() {
                return consumerId;
            }

            public int getPrice() {
                return price;
            }

            public int getRemainedContractMonths() {
                return remainedContractMonths;
            }

            ContractOutputData(final ContractData data) {
                consumerId = data.getConsumerId();
                price = data.getPrice();
                remainedContractMonths = data.getRemainedContractMonths();
            }
        }
    }
}
