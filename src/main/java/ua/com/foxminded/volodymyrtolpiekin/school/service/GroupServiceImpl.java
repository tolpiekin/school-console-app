package ua.com.foxminded.volodymyrtolpiekin.school.service;

import org.springframework.stereotype.Service;
import ua.com.foxminded.volodymyrtolpiekin.school.dao.jpa.JpaGroupDao;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Group;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {

    private final JpaGroupDao jpaGroupDAO;

    public GroupServiceImpl(JpaGroupDao jpaGroupDAO) {
        this.jpaGroupDAO = jpaGroupDAO;
    }

    @Override
    public Optional<Group> findById(int id) {
        return jpaGroupDAO.findById(id);
    }

    @Override
    public List<Group> getAll() {
        return jpaGroupDAO.findAll();
    }

    @Override
    public Optional<Group> addGroup(Group group) {
        jpaGroupDAO.save(group);
        return jpaGroupDAO.findById(group.getId());
    }

    @Override
    public Optional<Group> updateGroup(Group group, int id) {
        group.setId(id);
        jpaGroupDAO.save(group);
        return jpaGroupDAO.findById(group.getId());
    }

    @Override
    public void delGroup(int id){
        jpaGroupDAO.deleteById(id);
    }

    @Override
    public List<Group> smallerThen(int size){
        return jpaGroupDAO.findGroupSmallerThen(size);
    }
}
