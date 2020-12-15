package ciolty.energySystemImplementation.repositories;

import ciolty.energySystemImplementation.entities.ConsumerData;
import ciolty.engine.database.RepositoryAbstract;
import ciolty.engine.database.ResourceManager;

import java.util.List;

public final class ConsumerRepository extends RepositoryAbstract<ConsumerData> {
    public ConsumerRepository(final ResourceManager<ConsumerData> resourceManager) {
        super(resourceManager);
    }

    public List<ConsumerData> getConsumersWithNoActiveContract() {
        return find(object -> {
            ConsumerData consumer = (ConsumerData) object;
            if (consumer.isBankrupt()) {
                return false;
            }
            return  (consumer.getContract().getRemainedContractMonths() == 0);
        });
    }

    public List<ConsumerData> getActiveConsumers() {
        return find(object -> !((ConsumerData) object).isBankrupt());
    }
}
