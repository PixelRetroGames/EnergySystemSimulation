package ciolty.energySystemImplementation.repositories;

import ciolty.energySystemImplementation.entities.ProducerData;
import ciolty.engine.database.RepositoryAbstract;
import ciolty.engine.database.ResourceManager;

import java.util.List;

public class ProducerRepository extends RepositoryAbstract<ProducerData> {
    public ProducerRepository(ResourceManager<ProducerData> resourceManager) {
        super(resourceManager);
    }
}
