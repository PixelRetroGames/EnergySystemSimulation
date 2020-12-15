package ciolty.energySystemImplementation.actions;

import ciolty.energySystemImplementation.debugger.DebugLogger;
import ciolty.energySystemImplementation.entities.ConsumerData;
import ciolty.energySystemImplementation.entities.ContractData;
import ciolty.energySystemImplementation.entities.DistributorData;

import java.util.List;

public final class ConsumersChooseContractAction extends ImplementedAction {
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

            DebugLogger.log("Consumer #" + consumer.getId()
                            + " chose contract: \n"
                            + consumer.getContract().toString());
        }
        return null;
    }

    private ContractData getNewContract(final ConsumerData consumer) {
        return new ContractData(consumer.getId(), cheapestDistributor.getId(),
                cheapestDistributor.getContractPrice(), cheapestDistributor.getContractLength());
    }
}
