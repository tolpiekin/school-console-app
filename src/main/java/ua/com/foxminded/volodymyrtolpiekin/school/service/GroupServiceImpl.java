package ua.com.foxminded.volodymyrtolpiekin.school.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
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
    public Optional<Group> findById(int id) {
        try {
            return Optional.of(jpaGroupDao.findById(id)).orElseThrow(() ->
                    new GroupNotFoundException(id));
        }
        catch (GroupNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group Not Found", exc);
        }
    }

    @Override
    public List<Group> getAll() {
        return jpaGroupDao.findAll();
    }

    @Override
    public Optional<Group> addGroup(Group group) {
        jpaGroupDao.save(group);
        return jpaGroupDao.findById(group.getId());
    }

    @Override
    public Optional<Group> updateGroup(Group group, int id) {
        group.setId(id);
        jpaGroupDao.save(group);
        return jpaGroupDao.findById(group.getId());
    }

    @Override
    public void delGroup(int id) {
        Optional<Group> returnedGroup;
        try {
            returnedGroup = Optional.of(jpaGroupDao.findById(id).orElseThrow(() ->
                    new GroupNotFoundException(id)));
        }
        catch (GroupNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group Not Found", exc);
        }

        jpaGroupDao.deleteById(returnedGroup.get().getId());
    }

    @Override
    public List<String> findGroupLessThan(int size){
        List<String> grouplist =  jpaGroupDao.findGroupsLessThan(size);
        grouplist.forEach((k)-> System.out.println(k));
        return grouplist;
    }
}
