package ua.com.foxminded.volodymyrtolpiekin.school.service;

import ua.com.foxminded.volodymyrtolpiekin.school.models.Group;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface GroupService {

    Optional<Group> findById(int id);

    List<Group> getAll();

    Optional<Group> addGroup(Group group);

    Optional<Group> updateGroup(Group group, int id);

    void delGroup(int id);

    List<Map<String, Integer>> smallerThen(int size);
}
