package ua.com.foxminded.volodymyrtolpiekin.school.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Group;

import java.util.List;

@Repository
public interface JpaGroupDao extends JpaRepository<Group, Integer> {
    List<Group> findGroupSmallerThen(int size);
}
