package ua.com.foxminded.volodymyrtolpiekin.school.dao;

import ua.com.foxminded.volodymyrtolpiekin.school.models.Group;

import java.util.List;
import java.util.Optional;

public interface GroupDAO {
    Optional<Group> findById(int id);

    Optional<Group> findByName(String name);

    List<Group> getAll();

    Optional<Group> addItem(Group group);

    Optional<Group> updateItem(Group group);

    void deleteById(int id);

    List<Group> smallerThen (int size);
}
