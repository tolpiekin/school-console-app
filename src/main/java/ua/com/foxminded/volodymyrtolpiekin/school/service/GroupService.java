package ua.com.foxminded.volodymyrtolpiekin.school.service;

import ua.com.foxminded.volodymyrtolpiekin.school.models.Group;

import java.util.List;
import java.util.Optional;

public interface GroupService {

    Optional<Group> findById(int id);

    Optional<Group> findByName(String name);

    List<Group> getAll();

    Optional<Group> addGroup(Group group);

    Optional<Group> updateGroup(Group group);

    void delGroup(int id);

    List<Group> smallerThen(int size);
}
