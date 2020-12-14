package ciolty.Implementation.actions;

import ciolty.Implementation.actions.ImplementedAction;
import ciolty.Implementation.entities.ConsumerData;

import java.util.List;

public class ConsumersGetPaidAction extends ImplementedAction {
    List<ConsumerData> consumers;

    @Override
    public String start() {
        consumers = getUnitOfWork().getConsumerRepository().getActiveConsumers();
        return null;
    }

    @Override
    public String execute() {
        for (ConsumerData consumer : consumers) {
            consumer.setBudget(consumer.getBudget() + consumer.getMonthlyIncome());
            System.out.println("Consumer #" + consumer.getId()
                    + " budget = " + consumer.getBudget());
        }
        return null;
    }
}
