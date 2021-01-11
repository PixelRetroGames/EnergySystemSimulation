package ciolty.energySystemImplementation.repositories;

import ciolty.energySystemImplementation.entities.ConsumerData;
import ciolty.energySystemImplementation.entities.DistributorData;
import ciolty.energySystemImplementation.entities.InputData;
import ciolty.energySystemImplementation.entities.ProducerData;
import ciolty.energySystemImplementation.resourceManagers.ResourceManagerLinkedHashMap;
import ciolty.engine.database.ResourceManager;
import ciolty.engine.database.UnitOfWork;
import ciolty.engine.server.Input;

public final class EnergySystemUnitOfWork implements UnitOfWork {
    private ConsumerRepository consumerRepository;
    private DistributorRepository distributorRepository;
    private ProducerRepository producerRepository;

    private void populateConsumerRepository(final InputData inputData) {
        ResourceManager<ConsumerData> resourceManager = new ResourceManagerLinkedHashMap<>();
        for (ConsumerData consumerData : inputData.getConsumers()) {
            resourceManager.add(Integer.toString(consumerData.getId()),
                    new ConsumerData(consumerData));
        }
        consumerRepository = new ConsumerRepository(resourceManager);
    }

    private void populateDistributorRepository(final InputData inputData) {
        ResourceManager<DistributorData> resourceManager = new ResourceManagerLinkedHashMap<>();
        for (DistributorData distributorData : inputData.getDistributors()) {
            resourceManager.add(Integer.toString(distributorData.getId()),
                    new DistributorData(distributorData));
        }
        distributorRepository = new DistributorRepository(resourceManager);
    }

    private void populateProducerRepository(final InputData inputData) {
        ResourceManager<ProducerData> resourceManager = new ResourceManagerLinkedHashMap<>();
        for (ProducerData producerData : inputData.getProducers()) {
            resourceManager.add(Integer.toString(producerData.getId()),
                    new ProducerData(producerData));
        }
        producerRepository = new ProducerRepository(resourceManager);
    }

    @Override
    public void populate(final Input input) {
        InputData inputData = (InputData) input;

        populateConsumerRepository(inputData);
        populateDistributorRepository(inputData);
        populateProducerRepository(inputData);
    }

    public ConsumerRepository getConsumerRepository() {
        return consumerRepository;
    }

    public DistributorRepository getDistributorRepository() {
        return distributorRepository;
    }

    public ProducerRepository getProducerRepository() {
        return producerRepository;
    }
    @Override
    public void terminate() { }
}
