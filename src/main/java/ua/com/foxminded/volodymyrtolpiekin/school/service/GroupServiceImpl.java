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
    private GroupDAOImpl jdbcGroupDAOImpl;

    @Override
    public Optional<Group> findById(int id) {
        return jdbcGroupDAOImpl.findById(id);
    }

    @Override
    public Optional<Group> findByName(String name) {
        return jdbcGroupDAOImpl.findByName(name);
    }

    @Override
    public List<Group> getAll() {
        return jdbcGroupDAOImpl.getAll();
    }

    @Override
    public Optional<Group> addGroup(Group group) {
        return jdbcGroupDAOImpl.addItem(group);
    }

    @Override
    public Optional<Group> updateGroup(Group group) {
        return jdbcGroupDAOImpl.updateItem(group);
    }

    @Override
    public void delGroup(int id){
        jdbcGroupDAOImpl.deleteById(id);
    }

    @Override
    public List<Group> smallerThen(int size){
        return jdbcGroupDAOImpl.smallerThen(size);
    }
}
