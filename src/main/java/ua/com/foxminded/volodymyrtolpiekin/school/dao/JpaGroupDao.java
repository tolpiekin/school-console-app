package ua.com.foxminded.volodymyrtolpiekin.school.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Group;

import java.util.List;
import java.util.Map;

@Repository
public interface JpaGroupDao extends JpaRepository<Group, Integer> {
    @Query("SELECT g.groupName, COUNT(s.groupId) FROM Group g LEFT OUTER JOIN Student s ON g.id = s.groupId " +
            "WHERE COUNT(s.groupId) <= :size")
    List<Map<String, Integer>> findGroupSmallerThen(int size);
}