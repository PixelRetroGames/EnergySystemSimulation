package ciolty.energySystemImplementation.actions;

import ciolty.energySystemImplementation.actions.strategies.SortProducersGreenStrategy;
import ciolty.energySystemImplementation.actions.strategies.SortProducersPriceStrategy;
import ciolty.energySystemImplementation.actions.strategies.SortProducersQuantityStrategy;
import ciolty.energySystemImplementation.actions.strategies.SortProducersStrategy;
import ciolty.energySystemImplementation.debugger.DebugLogger;
import ciolty.energySystemImplementation.entities.DistributorData;
import ciolty.energySystemImplementation.entities.ProducerData;
import ciolty.engine.factory.Factory;

import java.util.List;
import java.util.Map;

public final class DistributorsChooseProducersAction extends ImplementedAction {
    private List<DistributorData> distributors;
    private List<ProducerData> producers;
    static final double FACTOR = 10.0;

    @Override
    public String start() {
        distributors = getUnitOfWork().getDistributorRepository()
                .find(object -> ((DistributorData) object).getChangedProducers()
                        || ((DistributorData) object).getProducersIds().isEmpty());

        producers = getUnitOfWork().getProducerRepository().getAll();
        return null;
    }

    @Override
    public String execute() {
        DebugLogger.log("Distributors chose producers!");

        distributors.forEach(distributor -> {
            removeDistributorsFromProducers(distributor);
            chooseProducers(distributor);
        });

        return null;
    }

    private void removeDistributorsFromProducers(DistributorData distributor) {
        for (int producerId : distributor.getProducersIds()) {
            ProducerData producer = getUnitOfWork().getProducerRepository()
                    .get(Integer.toString(producerId));
            producer.getRegisteredDistributors()
                    .removeIf(distributorId -> distributorId == distributor.getId());
        }
    }

    private void chooseProducers(DistributorData distributor) {
        Factory<SortProducersStrategy> strategyFactory = new Factory<>(Map.of(
                "GREEN",    SortProducersGreenStrategy::new,
                "PRICE",    SortProducersPriceStrategy::new,
                "QUANTITY", SortProducersQuantityStrategy::new
        ));

        SortProducersStrategy strategy = strategyFactory.get(
                distributor.getProducerStrategy());

        if (strategy == null) {
            return;
        }

        List<ProducerData> producersSorted = strategy.getProducersSorted(distributor, producers);

        distributor.getProducersIds().clear();
        distributor.setChangedProducers(false);
        int neededKW = distributor.getEnergyNeededKW();
        int pos = 0;
        DebugLogger.log("Distributor #" + distributor.getId() + " neededKW = " + neededKW);
        while (neededKW > 0) {
            ProducerData producer = producersSorted.get(pos++);
            if (producer.getRegisteredDistributors().size() < producer.getMaxDistributors()) {
                producer.getRegisteredDistributors().add(distributor.getId());
                neededKW -= producer.getEnergyPerDistributor();
                distributor.getProducersIds().add(producer.getId());
                DebugLogger.log("Producer #" + producer.getId() + " registered distributors: "
                        + producer.getRegisteredDistributors().toString());
            }
        }
        setProductionCost(distributor);

        DebugLogger.log("Distributor #" + distributor.getId() + " new producers: \n"
                + distributor.getProducersIds().toString());
        DebugLogger.log("New production cost: " + distributor.getProductionCost());
    }

    private void setProductionCost(DistributorData distributor) {
        int productionCost = 0;
        for (Integer producerId : distributor.getProducersIds()) {
            ProducerData producer = getUnitOfWork().getProducerRepository()
                    .get(Integer.toString(producerId));
            productionCost += producer.getPriceKW() * producer.getEnergyPerDistributor();
        }

        productionCost = Math.round((int) Math.floor(productionCost / FACTOR));
        distributor.setProductionCost(productionCost);
    }
}
