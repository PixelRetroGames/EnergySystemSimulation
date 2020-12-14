package ciolty.Implementation.actions;

import ciolty.Implementation.entities.ConsumerData;
import ciolty.Implementation.entities.ContractData;
import ciolty.Implementation.entities.DistributorData;

import java.util.List;

public class DistributorCleanContractsAction extends ImplementedAction {
    List<DistributorData> distributors;

    @Override
    public String start() {
        distributors = getUnitOfWork().getDistributorRepository().getActiveDistributors();
        return null;
    }

    @Override
    public String execute() {
        for (DistributorData distributor : distributors) {
            removeBankruptConsumers(distributor);
            removeFinishedContracts(distributor);
        }
        return null;
    }

    private void removeBankruptConsumers(DistributorData distributor) {
        distributor.getActiveContracts().removeIf(contract -> {
            ConsumerData consumer = getUnitOfWork().getConsumerRepository()
                    .get(Integer.toString(contract.getConsumerId()));
            return consumer.isBankrupt();
        });
    }

    private void removeFinishedContracts(DistributorData distributor) {
        distributor.getActiveContracts().removeIf(contract -> {
            return contract.getRemainedContractMonths() == 0;
        });
    }
}
