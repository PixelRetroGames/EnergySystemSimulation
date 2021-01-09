package ciolty.energySystemImplementation.entities;

import ciolty.engine.action.Output;

import java.util.ArrayList;
import java.util.List;

public final class OutputData implements Output {
    private final List<ConsumerOutputData> consumers = new ArrayList<>();
    private final List<DistributorOutputData> distributors = new ArrayList<>();
    private final List<ProducerOutputData> energyProducers = new ArrayList<>();

    public List<ConsumerOutputData> getConsumers() {
        return consumers;
    }

    public List<DistributorOutputData> getDistributors() {
        return distributors;
    }

    public List<ProducerOutputData> getEnergyProducers() {
        return energyProducers;
    }

    public OutputData() {
    }

    public OutputData(final List<ConsumerData> consumers,
                      final List<DistributorData> distributors,
                      final List<ProducerData> producers) {
        consumers.forEach(consumer -> this.consumers.add(new ConsumerOutputData(consumer)));
        distributors.forEach(distributor -> {
            this.distributors.add(new DistributorOutputData(distributor));
        });
        producers.forEach(producer -> this.energyProducers.add(new ProducerOutputData(producer)));
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
        private final int energyNeededKW;
        private final int contractCost;
        private final int budget;
        private final String producerStrategy;
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

        public int getEnergyNeededKW() {
            return energyNeededKW;
        }

        public int getContractCost() {
            return contractCost;
        }

        public String getProducerStrategy() {
            return producerStrategy;
        }

        public boolean isBankrupt() {
            return isBankrupt;
        }

        DistributorOutputData(final DistributorData data) {
            id = data.getId();
            budget = data.getBudget();
            isBankrupt = data.isBankrupt();
            contracts = new ArrayList<ContractOutputData>();
            data.getActiveContracts()
                    .forEach(contract -> contracts.add(new ContractOutputData(contract)));
            energyNeededKW = data.getEnergyNeededKW();
            contractCost = data.getContractPrice();
            producerStrategy = data.getProducerStrategy();
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

    private static class ProducerOutputData {
        private final int id;
        private final int maxDistributors;
        private final int priceKW;
        private final String energyType;
        private final int energyPerDistributor;
        private final List<MonthlyStat> monthlyStats;

        public int getId() {
            return id;
        }

        public int getMaxDistributors() {
            return maxDistributors;
        }

        public int getPriceKW() {
            return priceKW;
        }

        public String getEnergyType() {
            return energyType;
        }

        public int getEnergyPerDistributor() {
            return energyPerDistributor;
        }

        public List<MonthlyStat> getMonthlyStats() {
            return monthlyStats;
        }

        public ProducerOutputData(final ProducerData data) {
            id = data.getId();
            maxDistributors = data.getMaxDistributors();
            priceKW = data.getPriceKW();
            energyType = data.getEnergyType();
            energyPerDistributor = data.getEnergyPerDistributor();
            monthlyStats = null;
            // monthlyStats = data.getMonthly;
        }

        private static class MonthlyStat {
            private final int month;
            private final List<Integer> distributorsIds;

            public int getMonth() {
                return month;
            }

            public List<Integer> getDistributorsIds() {
                return distributorsIds;
            }

            public MonthlyStat(int month, List<Integer> distributorsIds) {
                this.month = month;
                this.distributorsIds = distributorsIds;
            }
        }
    }
}
