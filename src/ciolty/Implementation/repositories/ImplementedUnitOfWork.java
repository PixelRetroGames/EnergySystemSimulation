package ciolty.Implementation.repositories;

import ciolty.Implementation.entities.ConsumerData;
import ciolty.Implementation.entities.DistributorData;
import ciolty.Implementation.entities.InputData;
import ciolty.engine.database.ResourceManager;
import ciolty.VideoDBImplementation.resourceManagers.ResourceManagerHashMap;
import ciolty.engine.database.UnitOfWork;
import ciolty.engine.server.Input;

public final class ImplementedUnitOfWork implements UnitOfWork {
    private ConsumerRepository consumerRepository;
    private DistributorRepository distributorRepository;

    private void populateConsumerRepository(final InputData inputData) {
        ResourceManager<ConsumerData> resourceManager = new ResourceManagerHashMap<ConsumerData>();
        for (ConsumerData consumerData : inputData.getConsumers()) {
            resourceManager.add(Integer.toString(consumerData.getId()), new ConsumerData(consumerData));
        }
        consumerRepository = new ConsumerRepository(resourceManager);
    }

    private void populateDistributorRepository(final InputData inputData) {
        ResourceManager<DistributorData> resourceManager = new ResourceManagerHashMap<DistributorData>();
        for (DistributorData distributorData : inputData.getDistributors()) {
            resourceManager.add(Integer.toString(distributorData.getId()), new DistributorData(distributorData));
        }
        distributorRepository = new DistributorRepository(resourceManager);
    }

    @Override
    public void populate(final Input input) {
        InputData inputData = (InputData) input;

        populateConsumerRepository(inputData);
        populateDistributorRepository(inputData);
    }

    public ConsumerRepository getConsumerRepository() {
        return consumerRepository;
    }

    public DistributorRepository getDistributorRepository() {
        return distributorRepository;
    }

    @Override
    public void terminate() { }
}
