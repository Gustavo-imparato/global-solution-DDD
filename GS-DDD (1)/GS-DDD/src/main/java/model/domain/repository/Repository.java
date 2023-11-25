package model.domain.repository;

import java.util.List;

public interface Repository<T, U> {

    public List<T> findAll();

    public T findById(U id);

    public T persist(T t);

    public T update(T t);

    public boolean delete(T t);
}