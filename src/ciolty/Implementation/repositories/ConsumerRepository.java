package ciolty.Implementation.repositories;

import ciolty.Implementation.entities.ConsumerData;
import ciolty.engine.database.Filter;
import ciolty.engine.database.RepositoryAbstract;
import ciolty.engine.database.ResourceManager;

import java.util.List;

public class ConsumerRepository extends RepositoryAbstract<ConsumerData> {
    public ConsumerRepository(final ResourceManager<ConsumerData> resourceManager) {
        super(resourceManager);
    }

    public List<ConsumerData> getConsumersWithNoActiveContract() {
        return find(new Filter() {
            @Override
            public boolean isValid(Object object) {
                if (((ConsumerData) object).isBankrupt()) {
                    return false;
                }
                return  (((ConsumerData) object).getContract().getRemainedContractMonths() == 0);
            }
        });
    }

    public List<ConsumerData> getActiveConsumers() {
        return find(new Filter() {
            @Override
            public boolean isValid(Object object) {
                return !((ConsumerData) object).isBankrupt();
            }
        });
    }
}
