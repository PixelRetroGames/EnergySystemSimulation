package ciolty.energySystemImplementation.repositories;

import ciolty.energySystemImplementation.entities.DistributorData;
import ciolty.engine.database.RepositoryAbstract;
import ciolty.engine.database.ResourceManager;

import java.util.List;

public final class DistributorRepository extends RepositoryAbstract<DistributorData> {
    public DistributorRepository(final ResourceManager<DistributorData> resourceManager) {
        super(resourceManager);
    }

    public List<DistributorData> getActiveDistributors() {
        return find(object -> !((DistributorData) object).isBankrupt());
    }

    /**
     * @return distributor with the cheapest contract
     */
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
