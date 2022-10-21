package ua.com.foxminded.volodymyrtolpiekin.school.dao;

import java.util.List;
import java.util.Optional;

public interface DAO <T>{

    Optional<T> findById(int id);

    Optional<T> findByName(String name);

    List<T> getAll();

    void addItem(T t);

    void updateItem(T t);

    void deleteById(int id);

}
