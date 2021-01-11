package ciolty.energySystemImplementation.actions.strategies;

import ciolty.energySystemImplementation.entities.DistributorData;
import ciolty.energySystemImplementation.entities.ProducerData;

import java.util.ArrayList;
import java.util.List;

public final class SortProducersPriceStrategy implements SortProducersStrategy {
    @Override
    public List<ProducerData> getProducersSorted(DistributorData distributor,
                                                 List<ProducerData> producers) {
        List<ProducerData> producersSorted = new ArrayList<>();
        producersSorted.addAll(producers);
        producersSorted.sort((o1, o2) -> {
            if (Double.compare(o1.getPriceKW(), o2.getPriceKW()) == 0) {
                if (o1.getEnergyPerDistributor() == o2.getEnergyPerDistributor()) {
                    return o1.getId() - o2.getId();
                }
                return -o1.getEnergyPerDistributor() + o2.getEnergyPerDistributor();
            }
            return Double.compare(o1.getPriceKW(), o2.getPriceKW());
        });
        return producersSorted;
    }
}
