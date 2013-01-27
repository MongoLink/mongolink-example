package org.mongolink.example.domain;


import java.util.List;

public interface Repository<T> {

    T get(Object id);

    void delete(T entité);

    void add(T entité);

    List<T> all();
}
