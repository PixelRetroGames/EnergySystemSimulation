package ciolty.energySystemImplementation.actions;

import ciolty.energySystemImplementation.actions.strategies.DistributorChooseProducersGreenStrategy;
import ciolty.energySystemImplementation.actions.strategies.DistributorChooseProducersStrategy;
import ciolty.energySystemImplementation.debugger.DebugLogger;
import ciolty.energySystemImplementation.entities.DistributorData;
import ciolty.energySystemImplementation.entities.ProducerData;
import ciolty.engine.database.Filter;
import ciolty.engine.factory.Factory;

import java.util.List;
import java.util.Map;

public class DistributorsChooseProducersAction extends ImplementedAction {
    List<DistributorData> distributors;
    List<ProducerData> producers;

    @Override
    public String start() {
        distributors = getUnitOfWork().getDistributorRepository().find(new Filter() {
            @Override
            public boolean isValid(Object object) {
                DistributorData distributor = (DistributorData) object;
                int sum = distributor.getProducersIds().stream()
                        .mapToInt(id -> getUnitOfWork().getProducerRepository().get(Integer.toString(id))
                                .isPriceChanged() ? 1 : 0)
                        .sum();
                return (sum != distributor.getProducersIds().size()
                        || distributor.getProducersIds().size() == 0);
            }
        });

        producers = getUnitOfWork().getProducerRepository().getAll();
        return null;
    }

    @Override
    public String execute() {
        distributors.forEach(distributor -> chooseProducers(distributor));
        return null;
    }

    private final void chooseProducers(DistributorData distributor) {
        Factory<DistributorChooseProducersStrategy> strategyFactory = new Factory<>(Map.of(
                "GREEN",    DistributorChooseProducersGreenStrategy::new
        ));

        DistributorChooseProducersStrategy strategy = strategyFactory.get(
                distributor.getProducerStrategy());

        if (strategy == null) {
            return;
        }

        List<ProducerData> producersSorted = strategy.getProducersSorted(distributor, producers);

        int neededKW = distributor.getEnergyNeededKW();
        int pos = 0;
        while (neededKW > 0) {
            neededKW -= producersSorted.get(pos).getEnergyPerDistributor();
            distributor.getProducersIds().add(producersSorted.get(pos).getId());
        }

        DebugLogger.log("Distributor #" + distributor.getId() + " new distributors: \n" + distributor.getProducersIds().toString());
    }
}
