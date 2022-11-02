package ua.com.foxminded.volodymyrtolpiekin.school.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Group;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Transactional
public interface JpaGroupDao extends JpaRepository<Group, Integer> {

    Optional<Group> findById(int id);

    Optional<Group> findByName(String name);

    List<Group> getAll();

    Optional<Group> addGroup(Group group);

    Optional<Group> updateGroup(Group group);

    void delGroup(int id);

    @Query("select s, count(s) from Student s" +
            "join s.group_id g " +
            "where count(s) <= groupSize, :groupSize")
    List<Map<String, Object>> smallerThen(int groupSize);
}
