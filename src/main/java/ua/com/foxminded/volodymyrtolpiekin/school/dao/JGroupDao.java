package ua.com.foxminded.volodymyrtolpiekin.school.dao;

import org.springframework.stereotype.Repository;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Group;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class JGroupDao implements GroupDAO {
    public static final String FIND_BY_NAME = "select g from Group g where g.groupName = :groupName";
    public static final String GET_ALL = "select g from Group g";
    public static final String LESS_THEN = "SELECT g FROM Group g WHERE (SELECT count(s) FROM Student s WHERE s.groupId = g.groupId) <= :groupSize";
        //"select g From Group g, Student s where g.groupId = s.groupId";
            //"select g.* from Group g inner join (select count(s.studentId) as studCount, s.groupId " +
            //        "as groupIdCounter from Student s group by groupId) as counter on " +
            //        "groupId = groupIdCounter where studCount <= :groupSize";

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public Optional<Group> findById(int groupId) {
        Group group = entityManager.find(Group.class, groupId);
        return Optional.ofNullable(group);
    }

    @Transactional
    @Override
    public Optional<Group> findByName(String name) {
        Group group = entityManager.createQuery(FIND_BY_NAME, Group.class)
                .setParameter("groupName", name)
                .getSingleResult();
        return Optional.ofNullable(group);
    }

    @Transactional
    @Override
    public List<Group> getAll() {
        return entityManager.createQuery(GET_ALL, Group.class)
                .getResultList();
    }

    @Transactional
    @Override
    public Optional<Group> addItem(Group group) {
        entityManager.persist(group);
        return findById(group.getGroupId());
    }

    @Transactional
    @Override
    public Optional<Group> updateItem(Group group) {
        entityManager.merge(group);
        return findById(group.getGroupId());
    }

    @Transactional
    @Override
    public void deleteById(int groupId) {
        Optional<Group> returnedGroup = findById(groupId);
        if (returnedGroup.isPresent()) {
            entityManager.remove(returnedGroup.get());
        }
    }

    @Transactional
    @Override
    public List<Group> findGroupsSmallerThenNumber(int size) {
        long groupSize = size;
        return entityManager.createQuery(LESS_THEN, Group.class)
                .setParameter("groupSize", (Long) groupSize)
                .getResultList();
    }
}
