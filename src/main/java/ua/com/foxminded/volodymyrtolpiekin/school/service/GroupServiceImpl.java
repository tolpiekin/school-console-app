package ua.com.foxminded.volodymyrtolpiekin.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Group;
import ua.com.foxminded.volodymyrtolpiekin.school.dao.GroupDAOImpl;

import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupDAOImpl groupDAOImpl;

    @Override
    public Optional<Group> findById(int id) {
        return groupDAOImpl.findById(id);
    }

    @Override
    public Optional<Group> findByName(String name) {
        return groupDAOImpl.findByName(name);
    }

    @Override
    public List<Group> getAll() {
        return groupDAOImpl.getAll();
    }

    @Override
    public Optional<Group> addGroup(Group group) {
        return groupDAOImpl.addItem(group);
    }

    @Override
    public Optional<Group> updateGroup(Group group) {
        return groupDAOImpl.updateItem(group);
    }

    @Override
    public void delGroup(int id){
        groupDAOImpl.deleteById(id);
    }

    @Override
    public boolean isTableEmpty() {
        return groupDAOImpl.isTableEmpty();
    }

    @Override
    public List<Group> smallerThen(int size){
        return groupDAOImpl.smallerThen(size);
    }
}
