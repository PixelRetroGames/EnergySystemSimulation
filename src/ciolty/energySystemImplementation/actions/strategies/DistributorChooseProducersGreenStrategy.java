package ciolty.energySystemImplementation.actions.strategies;

import ciolty.energySystemImplementation.entities.DistributorData;
import ciolty.energySystemImplementation.entities.ProducerData;
import entities.EnergyType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DistributorChooseProducersGreenStrategy implements DistributorChooseProducersStrategy {
    @Override
    public List<ProducerData> getProducersSorted(DistributorData distributor, List<ProducerData> producers) {
        List<ProducerData> producersSorted = new ArrayList<>();
        producersSorted.addAll(producers);
        producersSorted.sort(new Comparator<ProducerData>() {
            @Override
            public int compare(ProducerData o1, ProducerData o2) {
                if (isRenewable(o1.getEnergyType()) == isRenewable(o2.getEnergyType())) {
                    if (o1.getPriceKW() == o2.getPriceKW()) {
                        if (o1.getEnergyPerDistributor() == o2.getEnergyPerDistributor()) {
                            return o1.getId() - o2.getId();
                        }
                        return -o1.getEnergyPerDistributor() + o2.getEnergyPerDistributor();
                    }
                    return -o1.getPriceKW() + o2.getPriceKW();
                }
                if (isRenewable(o1.getEnergyType())) {
                    return 1;
                }
                return -1;
            }
        });
        return producersSorted;
    }
}
