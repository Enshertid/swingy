package swingy.model.DAO;

import java.util.List;

public interface Dao <T>{
    List<T> getAll();
    T getById(int id);
    T save(T object);
    void update(T object);
    void delete(T object);
}
