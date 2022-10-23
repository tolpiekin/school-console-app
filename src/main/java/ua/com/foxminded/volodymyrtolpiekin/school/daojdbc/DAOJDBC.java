package ua.com.foxminded.volodymyrtolpiekin.school.daojdbc;

import java.util.List;
import java.util.Optional;

public interface DAOJDBC<T>{

    Optional<T> findById(int id);

    Optional<T> findByName(String name);

    List<T> getAll();

    Optional<T> addItem(T t);

    Optional<T> updateItem(T t);

    void deleteById(int id);

}
