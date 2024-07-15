package com.learnjava.HealthAndFitnessTracker.repisitory;

import com.learnjava.HealthAndFitnessTracker.model.Goal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface GoalRepository extends CrudRepository<Goal, Integer> {
    String findGoalTypeById(int id);
    Double findTargetValueById(int id);
    Double findProgressById(int id);
    LocalDate findDeadlineById(int id);
    List<Goal> findAllById(int id);
    void deleteById(int id);
}
