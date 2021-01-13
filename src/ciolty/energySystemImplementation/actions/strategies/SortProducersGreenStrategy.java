package ciolty.energySystemImplementation.actions.strategies;

import ciolty.energySystemImplementation.entities.DistributorData;
import ciolty.energySystemImplementation.entities.ProducerData;
import entities.EnergyType;

import java.util.ArrayList;
import java.util.List;

public final class SortProducersGreenStrategy implements SortProducersStrategy {
    @Override
    public List<ProducerData> getProducersSorted(DistributorData distributor,
                                                 List<ProducerData> producers) {
        List<ProducerData> producersSorted = new ArrayList<>();
        producersSorted.addAll(producers);
        producersSorted.sort((o1, o2) -> {
            if (isRenewable(o1.getEnergyType()) == isRenewable(o2.getEnergyType())) {
                if (Double.compare(o1.getPriceKW(), o2.getPriceKW()) == 0) {
                    if (o1.getEnergyPerDistributor() == o2.getEnergyPerDistributor()) {
                        return Integer.compare(o1.getId(),
                                o2.getId());
                    }
                    return Integer.compare(o2.getEnergyPerDistributor(),
                            o1.getEnergyPerDistributor());
                }
                return Double.compare(o1.getPriceKW(), o2.getPriceKW());
            }
            if (isRenewable(o1.getEnergyType())) {
                return -1;
            }
            return 1;
        });
        return producersSorted;
    }

    private boolean isRenewable(String str) {
        return EnergyType.valueOf(str).isRenewable();
    }
}
