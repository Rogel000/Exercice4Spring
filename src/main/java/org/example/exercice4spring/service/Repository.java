package org.example.exercice4spring.service;

import java.util.List;
import java.util.UUID;

public interface Repository<T> {
    T create(T entity);
    List<T> findAll();
    void delete(UUID id);
    T update(T entity);
    T findById(UUID id);
}
