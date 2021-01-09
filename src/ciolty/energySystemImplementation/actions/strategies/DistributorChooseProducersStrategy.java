package ciolty.energySystemImplementation.actions.strategies;

import ciolty.energySystemImplementation.entities.DistributorData;
import ciolty.energySystemImplementation.entities.ProducerData;
import entities.EnergyType;

import java.util.List;

public interface DistributorChooseProducersStrategy {
    public List<ProducerData> getProducersSorted(DistributorData distributor, List<ProducerData> producers);

    default boolean isRenewable(String str) {
        return EnergyType.valueOf(str).isRenewable();
    }
}
