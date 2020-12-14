package ciolty.Implementation.actions;

import ciolty.Implementation.entities.ConsumerData;
import ciolty.Implementation.entities.ContractData;
import ciolty.Implementation.entities.DistributorData;

import java.util.List;

public class ConsumersChooseContractAction extends ImplementedAction {
    private List<ConsumerData> consumers;
    private DistributorData cheapestDistributor;
    @Override
    public String start() {
        consumers = getUnitOfWork().getConsumerRepository().getConsumersWithNoActiveContract();
        cheapestDistributor = getUnitOfWork().getDistributorRepository().getCheapest();
        return null;
    }

    @Override
    public String execute() {
        for (ConsumerData consumer : consumers) {
            ContractData newContract = getNewContract(consumer);
            consumer.setContract(newContract);
            cheapestDistributor.getActiveContracts().add(newContract);

            System.out.println("Consumer #" + consumer.getId()
                    + " chose contract: \n" + consumer.getContract().toString());
        }
        return null;
    }

    private final ContractData getNewContract(ConsumerData consumer) {
        return new ContractData(consumer.getId(), cheapestDistributor.getId(),
                cheapestDistributor.getContractPrice(), cheapestDistributor.getContractLength());
    }
}
