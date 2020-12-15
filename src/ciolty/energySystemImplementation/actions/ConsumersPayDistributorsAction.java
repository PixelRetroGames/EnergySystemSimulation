package ciolty.energySystemImplementation.actions;

import ciolty.energySystemImplementation.debugger.DebugLogger;
import ciolty.energySystemImplementation.entities.ConsumerData;
import ciolty.energySystemImplementation.entities.DistributorData;

import java.util.List;

public final class ConsumersPayDistributorsAction extends ImplementedAction {
    private List<ConsumerData> consumers;
    private static final double LATE_FEE_FACTOR = 1.2;

    @Override
    public String start() {
        consumers = getUnitOfWork().getConsumerRepository().getActiveConsumers();
        return null;
    }

    @Override
    public String execute() {
        for (ConsumerData consumer : consumers) {
            pay(consumer);
        }
        return null;
    }

    private void pay(final ConsumerData consumer) {
        if (canPay(consumer)) {
            payCurrentBill(consumer);
            if (consumerHasUnpaidBill(consumer)) {
                payLateBill(consumer);
            }
        } else if (!consumerHasUnpaidBill(consumer)) {
            consumer.setUnpaidBill(consumer.getContract());
            DebugLogger.log("Consumer #" + consumer.getId() + " will pay next month!");
        } else {
            consumer.setBankrupt(true);
            DebugLogger.log("Consumer #" + consumer.getId() + " filled bankruptcy!");
        }
    }

    private boolean consumerHasUnpaidBill(final ConsumerData consumer) {
        return (consumer.getUnpaidBill().getPrice() != 0);
    }

    private void payCurrentBill(final ConsumerData consumer) {
        consumer.setBudget(consumer.getBudget() - getTotalPrice(consumer));
        DistributorData distributor = getUnitOfWork().getDistributorRepository()
                .get(Integer.toString(consumer.getContract().getDistributorId()));
        distributor.setBudget(distributor.getBudget() + consumer.getContract().getPrice());

        DebugLogger.log("Consumer #" + consumer.getId()
                + " paid: " + getTotalPrice(consumer));
        DebugLogger.log("Consumer #" + consumer.getId()
                + " budget = " + consumer.getBudget());
        DebugLogger.log("Distributor #" + distributor.getId()
                + " received " + consumer.getContract().getPrice());
        DebugLogger.log("Distributor #" + distributor.getId()
                + " budget = " + distributor.getBudget());
    }

    private void payLateBill(final ConsumerData consumer) {
        DistributorData distributor = getUnitOfWork().getDistributorRepository()
                .get(Integer.toString(consumer.getUnpaidBill().getDistributorId()));
        distributor.setBudget(distributor.getBudget() + getUnpaidBillPrice(consumer));
        consumer.getUnpaidBill().setPrice(0);

        DebugLogger.log("Distributor #" + distributor.getId()
                + " received " + consumer.getContract().getPrice()
                + " for last month");
        DebugLogger.log("Distributor #" + distributor.getId()
                + " budget = " + distributor.getBudget());
    }

    private boolean canPay(final ConsumerData consumer) {
        return consumer.getBudget() >= getTotalPrice(consumer);
    }

    private int getTotalPrice(final ConsumerData consumer) {
        return consumer.getContract().getPrice()
                + getUnpaidBillPrice(consumer);
    }

    private int getUnpaidBillPrice(final ConsumerData consumer) {
        return (int) Math.round(Math.floor(LATE_FEE_FACTOR * consumer.getUnpaidBill().getPrice()));
    }
}
