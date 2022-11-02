package ua.com.foxminded.volodymyrtolpiekin.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Group;
import ua.com.foxminded.volodymyrtolpiekin.school.dao.jpa.JpaGroupDao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {
    private final JpaGroupDao jpaGroupDAO;

    @Autowired
    public GroupServiceImpl(JpaGroupDao jpaGroupDAO) {
        this.jpaGroupDAO = jpaGroupDAO;
    }

    @Override
    public Optional<Group> findById(int id) {
        return jpaGroupDAO.findById(id);
    }

    @Override
    public Optional<Group> findByName(String name) {
        return jpaGroupDAO.findByName(name);
    }

    @Override
    public List<Group> getAll() {
        return jpaGroupDAO.getAll();
    }

    @Override
    public Optional<Group> addGroup(Group group) {
        return jpaGroupDAO.addGroup(group);
    }

    @Override
    public Optional<Group> updateGroup(Group group) {
        return jpaGroupDAO.updateGroup(group);
    }

    @Override
    public void delGroup(int id){
        jpaGroupDAO.deleteById(id);
    }

    @Override
    public List<Map<String, Object>> smallerThen(int size){
        return jpaGroupDAO.smallerThen(size);
    }
}
