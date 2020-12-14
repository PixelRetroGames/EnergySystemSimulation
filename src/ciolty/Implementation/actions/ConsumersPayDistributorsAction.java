package ciolty.Implementation.actions;

import ciolty.Implementation.entities.ConsumerData;
import ciolty.Implementation.entities.ContractData;
import ciolty.Implementation.entities.DistributorData;

import java.util.List;

public class ConsumersPayDistributorsAction extends ImplementedAction {
    private List<ConsumerData> consumers;

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

    private void pay(ConsumerData consumer) {
        if (canPay(consumer)) {
            consumer.setBudget(consumer.getBudget() - getTotalPrice(consumer));
            DistributorData distributor = getUnitOfWork().getDistributorRepository()
                    .get(Integer.toString(consumer.getContract().getDistributorId()));
            distributor.setBudget(distributor.getBudget() + consumer.getContract().getPrice());

            System.out.println("Consumer #" + consumer.getId()
                    + " paid: " + getTotalPrice(consumer));
            System.out.println("Consumer #" + consumer.getId()
                    + " budget = " + consumer.getBudget());
            System.out.println("Distributor #" + distributor.getId()
                    + " received " + consumer.getContract().getPrice());
            System.out.println("Distributor #" + distributor.getId()
                    + " budget = " + distributor.getBudget());

            if (consumer.getUnpaidBill().getPrice() != 0) {
                distributor = getUnitOfWork().getDistributorRepository()
                        .get(Integer.toString(consumer.getUnpaidBill().getDistributorId()));
                distributor.setBudget(distributor.getBudget() + getUnpaidBillPrice(consumer));
                consumer.getUnpaidBill().setPrice(0);

                System.out.println("Distributor #" + distributor.getId()
                        + " received " + consumer.getContract().getPrice()
                        + " for last month");
                System.out.println("Distributor #" + distributor.getId()
                        + " budget = " + distributor.getBudget());
            }
        } else if (consumer.getUnpaidBill().getPrice() == 0) {
            consumer.setUnpaidBill(consumer.getContract());
            System.out.println("Consumer #" + consumer.getId() + " will pay next month!");
        } else {
            consumer.setBankrupt(true);
            System.out.println("Consumer #" + consumer.getId() + " filled bankruptcy!");
        }
    }

    private boolean canPay(ConsumerData consumer) {
        return consumer.getBudget() >= getTotalPrice(consumer);
    }

    private int getTotalPrice(ConsumerData consumer) {
        return consumer.getContract().getPrice()
                + getUnpaidBillPrice(consumer);
    }

    private int getUnpaidBillPrice(ConsumerData consumer) {
        return (int) Math.round(Math.floor(1.2 * consumer.getUnpaidBill().getPrice()));
    }
}
