package com.foxminded.volodymyrtolpiekin.school.dao;

import java.util.List;
import java.util.Optional;

public interface DAO <T>{

    Optional<T> get(int id);

    Optional<T> findByName(String name);

    List<T> getAll();

    void create(T t);

    void update(T t);

    void delete(int id);

}
