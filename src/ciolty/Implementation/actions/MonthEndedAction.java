package ciolty.Implementation.actions;

import ciolty.Implementation.entities.ConsumerData;
import ciolty.Implementation.entities.ContractData;
import ciolty.Implementation.entities.DistributorData;

import java.util.List;

public class MonthEndedAction extends ImplementedAction {
    List<ConsumerData> consumers;
    List<DistributorData> distributors;

    @Override
    public String start() {
        consumers = getUnitOfWork().getConsumerRepository().getActiveConsumers();
        distributors = getUnitOfWork().getDistributorRepository().getActiveDistributors();
        return null;
    }

    @Override
    public String execute() {
        /*consumers.forEach(consumer -> {
            ContractData contract = consumer.getContract();
            contract.setRemainedContractMonths(contract.getRemainedContractMonths() - 1);
        });*/

        distributors.forEach(distributor -> {
            distributor.getActiveContracts().forEach(contract -> {
                contract.setRemainedContractMonths(contract.getRemainedContractMonths() - 1);
            });
        });
        return null;
    }
}
