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
    public static final String LESS_THEN =
            "select g.* from Group " +
                    "inner join (select count(student_id) as studCount, group_id as group_id_counter from Student " +
                    "group by group_id) as counter on group_id = group_id_counter where studCount <= :groupSize";

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
    public List<Group> findGroupsSmallerThenNumber(int groupSize) {
        return entityManager.createQuery(LESS_THEN, Group.class)
                .setParameter("groupSize", groupSize)
                .getResultList();
    }
}
