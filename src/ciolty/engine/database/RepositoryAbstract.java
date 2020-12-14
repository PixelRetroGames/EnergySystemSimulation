package ciolty.engine.database;

import ciolty.Implementation.entities.ConsumerData;

import java.util.ArrayList;
import java.util.List;

public abstract class RepositoryAbstract<T> implements Repository<T> {
    protected final ResourceManager<T> resourceManager;

    public RepositoryAbstract(final ResourceManager<T> resourceManager) {
        this.resourceManager = resourceManager;
    }

    @Override
    public void add(String key, T value) {
        resourceManager.add(key, value);
    }

    @Override
    public final T get(final String name) {
        return resourceManager.get(name);
    }

    @Override
    public List<T> getAll() {
        return resourceManager.getAll();
    }

    @Override
    public final List<T> find(final Filter filter) {
        List<T> allObjects = resourceManager.getAll();
        List<T> filteredObjects = new ArrayList<T>();
        for (T object : allObjects) {
            if (filter.isValid(object)) {
                filteredObjects.add(object);
            }
        }
        return filteredObjects;
    }
}
