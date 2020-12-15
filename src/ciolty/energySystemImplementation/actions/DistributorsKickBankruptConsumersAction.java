package ciolty.energySystemImplementation.actions;

import ciolty.energySystemImplementation.debugger.DebugLogger;
import ciolty.energySystemImplementation.entities.ConsumerData;
import ciolty.energySystemImplementation.entities.DistributorData;

import java.util.List;

public final class DistributorsKickBankruptConsumersAction extends ImplementedAction {
    private List<DistributorData> distributors;

    @Override
    public String start() {
        distributors = getUnitOfWork().getDistributorRepository().getActiveDistributors();
        return null;
    }

    @Override
    public String execute() {
        DebugLogger.log("Distributors kicked bankrupt consumers!");
        for (DistributorData distributor : distributors) {
            removeBankruptConsumers(distributor);
        }
        return null;
    }

    private void removeBankruptConsumers(final DistributorData distributor) {
        distributor.getActiveContracts().removeIf(contract -> {
            ConsumerData consumer = getUnitOfWork().getConsumerRepository()
                    .get(Integer.toString(contract.getConsumerId()));
            return consumer.isBankrupt();
        });
    }
}
