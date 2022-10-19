package com.foxminded.volodymyrtolpiekin.school.dao;

import com.foxminded.volodymyrtolpiekin.school.models.Group;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GroupDAO implements DAO<Group> {

    private List<Group> groups = new ArrayList<>();
    @Override
    public Optional<Group> get(int id) {
        return Optional.ofNullable(groups.get(id));
    }

    @Override
    public List<Group> getAll() {
        return groups;
    }

    @Override
    public void create(Group group) {
        groups.add(group);
    }

    @Override
    public void update(Group group) {
        groups.set(group.getId(), group);
    }

    @Override
    public void delete(int id) {
        groups.remove(id);
    }
}
