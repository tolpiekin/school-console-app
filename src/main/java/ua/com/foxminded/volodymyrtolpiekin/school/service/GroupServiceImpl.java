package ua.com.foxminded.volodymyrtolpiekin.school.service;

import org.springframework.stereotype.Service;
import ua.com.foxminded.volodymyrtolpiekin.school.dao.JpaGroupDao;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Group;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {

    private final JpaGroupDao jpaGroupDAO;

    public GroupServiceImpl(JpaGroupDao jpaGroupDAO) {
        this.jpaGroupDAO = jpaGroupDAO;
    }

    @Override
    public Optional<Group> findById(int id) {
        Optional<Group> returnedGroup =  jpaGroupDAO.findById(id);
        if(returnedGroup.isPresent()){
            return returnedGroup;
        } else {
            throw new EntityNotFoundException(String.format("404.Group with id %d not found", id));
        }

    }

    @Override
    public List<Group> getAll() {
        return jpaGroupDAO.findAll();
    }

    @Override
    public Optional<Group> addGroup(Group group) {
        return Optional.of(jpaGroupDAO.save(group));
    }

    @Override
    public Optional<Group> updateGroup(Group group, int id) {
        group.setId(id);
        jpaGroupDAO.save(group);
        return jpaGroupDAO.findById(group.getId());
    }

    @Override
    public void delGroup(int id){
        Optional<Group> returnedGroup =  jpaGroupDAO.findById(id);
        if(returnedGroup.isPresent()){
            jpaGroupDAO.deleteById(id);
        } else {
            throw new EntityNotFoundException(String.format("404. Group with id %d not found", id));
        }
    }

    @Override
    public List<String> findGroupLessThan(int size){
        List<String> grouplist =  jpaGroupDAO.findGroupsLessThan(size);
        grouplist.forEach((k)-> System.out.println(k));
        return grouplist;
    }
}
