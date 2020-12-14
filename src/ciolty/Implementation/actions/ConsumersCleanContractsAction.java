package ciolty.Implementation.actions;

import ciolty.Implementation.entities.ConsumerData;

import java.util.List;

public class ConsumersCleanContractsAction extends ImplementedAction {
    private List<ConsumerData> consumers;

    @Override
    public String start() {
        consumers = getUnitOfWork().getConsumerRepository().getActiveConsumers();
        return null;
    }

    @Override
    public String execute() {
        for (ConsumerData consumer : consumers) {
            if (distributorFilledBankruptcy(consumer.getContract().getDistributorId())) {
                consumer.getContract().setRemainedContractMonths(0);
            }
        }
        return null;
    }

    private boolean distributorFilledBankruptcy(int distributorId) {
        return getUnitOfWork().getDistributorRepository().get(Integer.toString(distributorId)).isBankrupt();
    }
}
