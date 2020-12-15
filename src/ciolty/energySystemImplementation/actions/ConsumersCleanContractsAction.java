package ciolty.energySystemImplementation.actions;

import ciolty.energySystemImplementation.debugger.DebugLogger;
import ciolty.energySystemImplementation.entities.ConsumerData;

import java.util.List;

public final class ConsumersCleanContractsAction extends ImplementedAction {
    private List<ConsumerData> consumers;

    @Override
    public String start() {
        consumers = getUnitOfWork().getConsumerRepository().getActiveConsumers();
        return null;
    }

    @Override
    public String execute() {
        DebugLogger.log("Consumers contracts cleaned!");
        consumers.forEach(consumer -> {
            if (distributorFilledBankruptcy(consumer.getContract().getDistributorId())) {
                consumer.getContract().setRemainedContractMonths(0);
            }
        });
        return null;
    }

    private boolean distributorFilledBankruptcy(final int distributorId) {
        return getUnitOfWork().getDistributorRepository()
                .get(Integer.toString(distributorId)).isBankrupt();
    }
}
