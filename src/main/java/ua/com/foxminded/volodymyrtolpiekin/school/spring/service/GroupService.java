package ua.com.foxminded.volodymyrtolpiekin.school.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Group;
import ua.com.foxminded.volodymyrtolpiekin.school.spring.jdbc.JdbcGroupDAO;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    @Autowired
    private JdbcGroupDAO jdbcGroupDAO;

    public Optional<Group> findById(int id) {
        return jdbcGroupDAO.findById(id);
    }

    public Optional<Group> findByName(String name) {
        return jdbcGroupDAO.findByName(name);
    }

    public List<Group> getAll() {
        return jdbcGroupDAO.getAll();
    }

    public Optional<Group> addGroup(Group group) {
        return jdbcGroupDAO.addItem(group);
    }

    public Optional<Group> updateGroup(Group group) {
        return jdbcGroupDAO.updateItem(group);
    }

    public void delGroup(int id){
        jdbcGroupDAO.deleteById(id);
    }

    public List<Group> smallerThen(int size){
        return jdbcGroupDAO.smallerThen(size);
    }
}
