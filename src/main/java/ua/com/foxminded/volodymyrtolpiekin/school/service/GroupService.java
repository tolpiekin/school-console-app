package ua.com.foxminded.volodymyrtolpiekin.school.service;

import ua.com.foxminded.volodymyrtolpiekin.school.models.Group;

import java.util.List;
import java.util.Optional;

public interface GroupService {

    Optional<Group> findById(int id) throws GroupIdNotFoundException;

    List<Group> getAll();

    Optional<Group> addGroup(Group group);

    Optional<Group> updateGroup(Group group, int id);

    void delGroup(int id) throws GroupIdNotFoundException;

    List<String> findGroupLessThan(int size);
}
