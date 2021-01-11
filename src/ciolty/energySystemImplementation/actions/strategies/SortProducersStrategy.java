package ciolty.energySystemImplementation.actions.strategies;

import ciolty.energySystemImplementation.entities.DistributorData;
import ciolty.energySystemImplementation.entities.ProducerData;

import java.util.List;

public interface SortProducersStrategy {
    /**
     * @param distributor
     * @param producers
     * @return sorted producer
     */
    List<ProducerData> getProducersSorted(DistributorData distributor,
                                          List<ProducerData> producers);
}
