package ua.com.foxminded.volodymyrtolpiekin.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.volodymyrtolpiekin.school.dao.JGroupDao;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Group;

import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {
    private final JGroupDao jGroupDao;

    @Autowired
    public GroupServiceImpl(JGroupDao jGroupDao) {
        this.jGroupDao = jGroupDao;
    }

    @Override
    public Optional<Group> findById(int id) {
        return jGroupDao.findById(id);
    }

    @Override
    public Optional<Group> findByName(String name) {
        return jGroupDao.findByName(name);
    }

    @Override
    public List<Group> getAll() {
        return jGroupDao.getAll();
    }

    @Override
    public Optional<Group> addGroup(Group group) {
        return jGroupDao.addItem(group);
    }

    @Override
    public Optional<Group> updateGroup(Group group) {
        return jGroupDao.updateItem(group);
    }

    @Override
    public void delGroup(int id){
        jGroupDao.deleteById(id);
    }

    @Override
    public List<Group> smallerThen(int size){
        return jGroupDao.findGroupsSmallerThenNumber(size);
    }
}
