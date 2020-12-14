package ciolty.engine.database;

import java.util.List;

/**
 * Repository that uses the class T for data mapping.
 * @param <T>
 */
public interface Repository<T> {
    void add(String key, T value);

    /**
     * @param name
     * @return object with the given name from the data mapper
     */
    T get(String name);

    List<T> getAll();

    /**
     * @param filter
     * @return list of objects that match filter pattern
     */
    List<T> find(Filter filter);
}
