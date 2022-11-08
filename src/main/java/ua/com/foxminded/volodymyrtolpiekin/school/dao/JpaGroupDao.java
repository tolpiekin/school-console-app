package ua.com.foxminded.volodymyrtolpiekin.school.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Group;

import java.util.List;

@Repository
public interface JpaGroupDao extends JpaRepository<Group, Integer> {

    @Query(value = "SELECT groups.group_name, count(students.group_id) FROM groups " +
            "INNER JOIN students ON students.group_id = groups.id " +
            "GROUP BY groups.group_name " +
            "HAVING count(students.group_id) <= :size", nativeQuery = true)
    List<String> findGroupsLessThan(int size);
}