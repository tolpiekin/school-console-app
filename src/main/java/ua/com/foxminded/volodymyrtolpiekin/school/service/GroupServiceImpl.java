package ua.com.foxminded.volodymyrtolpiekin.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
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
        try{
            return Optional.of(jGroupDao.findById(id)).orElseThrow(() ->
                    new GroupNotFoundException(id)
            );
        }
        catch(GroupNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group Not Found", exc);
        }
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
        Optional<Group> returnedGroup;
        try {
            returnedGroup = Optional.of(jGroupDao.findById(id).orElseThrow(() ->
                    new GroupNotFoundException(id)));
        }
        catch (GroupNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group Not Found", exc);
        }
        jGroupDao.deleteById(returnedGroup.get().getGroupId());
    }

    @Override
    public List<Group> smallerThen(int size){
        return jGroupDao.findGroupsSmallerThenNumber(size);
    }
}
