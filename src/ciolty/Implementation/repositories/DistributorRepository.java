package ciolty.Implementation.repositories;

import ciolty.Implementation.entities.DistributorData;
import ciolty.engine.database.Filter;
import ciolty.engine.database.RepositoryAbstract;
import ciolty.engine.database.ResourceManager;

import java.util.List;

public class DistributorRepository extends RepositoryAbstract<DistributorData> {
    public DistributorRepository(ResourceManager<DistributorData> resourceManager) {
        super(resourceManager);
    }

    public List<DistributorData> getActiveDistributors() {
        return find(new Filter() {
            @Override
            public boolean isValid(Object object) {
                return !((DistributorData) object).isBankrupt();
            }
        });
    }

    public DistributorData getCheapest() {
        DistributorData cheapestDistributor = null;
        int minPrice = Integer.MAX_VALUE;
        List<DistributorData> activeDistributors = getActiveDistributors();

        for (DistributorData distributor: activeDistributors) {
            if (distributor.getContractPrice() < minPrice) {
                minPrice = distributor.getContractPrice();
                cheapestDistributor = distributor;
            }
        }

        return cheapestDistributor;
    }
}
