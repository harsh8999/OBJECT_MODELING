package com.crio.jukebox.repositories.interfaces;

import java.util.List;

public interface CRUDRepository<T, ID> {
    T save(T entity);
    void deleteById(ID id);
    List<T> findAll();
    T findById(ID id);
}
