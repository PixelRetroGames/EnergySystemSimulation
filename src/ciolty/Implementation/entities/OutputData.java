package ciolty.Implementation.entities;

import ciolty.engine.action.Output;

import java.util.ArrayList;
import java.util.List;

public class OutputData implements Output {
    List<ConsumerOutputData> consumers = new ArrayList<>();
    List<DistributorOutputData> distributors = new ArrayList<>();

    public List<ConsumerOutputData> getConsumers() {
        return consumers;
    }

    public List<DistributorOutputData> getDistributors() {
        return distributors;
    }

    public OutputData() {
    }

    public OutputData(List<ConsumerData> consumers, List<DistributorData> distributors) {
        consumers.forEach(consumer -> this.consumers.add(new ConsumerOutputData(consumer)));
        distributors.forEach(distributor -> this.distributors.add(new DistributorOutputData(distributor)));
    }

    class ConsumerOutputData {
        int id;
        boolean isBankrupt;
        int budget;

        public int getId() {
            return id;
        }

        public boolean getIsBankrupt() {
            return isBankrupt;
        }

        public int getBudget() {
            return budget;
        }

        public ConsumerOutputData() {
        }

        public ConsumerOutputData(ConsumerData data) {
            id = data.getId();
            isBankrupt = data.isBankrupt();
            budget = data.getBudget();
        }
    }

    class DistributorOutputData {
        int id;
        int budget;
        boolean isBankrupt;
        List<ContractOutputData> contracts;

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

        public DistributorOutputData() {

        }

        public DistributorOutputData(DistributorData data) {
            id = data.getId();
            budget = data.getBudget();
            isBankrupt = data.isBankrupt();
            contracts = new ArrayList<ContractOutputData>();
            data.getActiveContracts()
                    .forEach(contract -> contracts.add(new ContractOutputData(contract)));
        }

        class ContractOutputData {
            int consumerId;
            int price;
            int remainedContractMonths;

            public int getConsumerId() {
                return consumerId;
            }

            public int getPrice() {
                return price;
            }

            public int getRemainedContractMonths() {
                return remainedContractMonths;
            }

            public ContractOutputData() {
            }

            public ContractOutputData(ContractData data) {
                consumerId = data.getConsumerId();
                price = data.getPrice();
                remainedContractMonths = data.getRemainedContractMonths();
            }
        }
    }
}
