package ua.com.foxminded.volodymyrtolpiekin.school.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DAO<T> {
    Optional<T> findById(int id);

    Optional<T> findByName(String courseName);

    List<T> getAll();

    void addItem(T t);

    void updateItem(T t, int id);

    void deleteById(int id);
}
