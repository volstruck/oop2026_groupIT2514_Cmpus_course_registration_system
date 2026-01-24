package campus.repository;

import java.util.List;

public interface Repository<T>{
    T findById(int id);
    void create(T entity);
    void delete(int id);
    List<T> findAll();
}
