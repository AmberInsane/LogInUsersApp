package com.tms.model;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    Optional<T> get(String name, String password);

    List<T> getAll();

    boolean save(T t);
    boolean update(T t);
    boolean delete(T t);
}