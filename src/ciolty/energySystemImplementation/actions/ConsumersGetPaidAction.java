package ciolty.energySystemImplementation.actions;

import ciolty.energySystemImplementation.debugger.DebugLogger;
import ciolty.energySystemImplementation.entities.ConsumerData;

import java.util.List;

public final class ConsumersGetPaidAction extends ImplementedAction {
    private List<ConsumerData> consumers;

    @Override
    public String start() {
        consumers = getUnitOfWork().getConsumerRepository().getActiveConsumers();
        return null;
    }

    @Override
    public String execute() {
        consumers.forEach(consumer -> {
            consumer.setBudget(consumer.getBudget() + consumer.getMonthlyIncome());
            DebugLogger.log("Consumer #" + consumer.getId()
                            + " budget = " + consumer.getBudget());
        });
        return null;
    }
}
