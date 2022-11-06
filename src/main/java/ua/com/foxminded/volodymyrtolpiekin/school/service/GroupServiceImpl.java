package ua.com.foxminded.volodymyrtolpiekin.school.service;

import org.springframework.stereotype.Service;
import ua.com.foxminded.volodymyrtolpiekin.school.dao.JpaGroupDao;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Group;

import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {

    private final JpaGroupDao jpaGroupDao;

    public GroupServiceImpl(JpaGroupDao jpaGroupDao) {
        this.jpaGroupDao = jpaGroupDao;
    }

    @Override
    public Optional<Group> findById(int id) throws GroupNotFoundException {
        return Optional.ofNullable(jpaGroupDao.findById(id)).orElseThrow(() ->
                new GroupNotFoundException(id));
    }

    @Override
    public List<Group> getAll() {
        return jpaGroupDao.findAll();
    }

    @Override
    public Optional<Group> addGroup(Group group) {
        return Optional.of(jpaGroupDao.save(group));
    }

    @Override
    public Optional<Group> updateGroup(Group group, int id) {
        group.setId(id);
        jpaGroupDao.save(group);
        return jpaGroupDao.findById(group.getId());
    }

    @Override
    public void delGroup(int id) throws GroupNotFoundException {
        Optional<Group> returnedGroup = Optional.ofNullable(jpaGroupDao.findById(id).orElseThrow(() ->
                new GroupNotFoundException(id)));
        jpaGroupDao.deleteById(returnedGroup.get().getId());
    }

    @Override
    public List<String> findGroupLessThan(int size){
        List<String> grouplist =  jpaGroupDao.findGroupsLessThan(size);
        grouplist.forEach((k)-> System.out.println(k));
        return grouplist;
    }
}
