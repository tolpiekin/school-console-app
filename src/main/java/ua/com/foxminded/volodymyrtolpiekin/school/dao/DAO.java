package ua.com.foxminded.volodymyrtolpiekin.school.dao;

import java.util.List;
import java.util.Optional;

public interface DAO <T>{

    Optional<T> getById(int id);

    Optional<T> findByName(String name);

    List<T> getAll();

    void create(T t);

    void update(T t);

    void deleteById(int id);

}
