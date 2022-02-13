package swingy.model.DAO;

import java.util.List;

public interface Dao <T>{
    List<T> getAll();
    T getByName(String name);
    void save(T object);
    void update(T object);
    void delete(T object);
}
