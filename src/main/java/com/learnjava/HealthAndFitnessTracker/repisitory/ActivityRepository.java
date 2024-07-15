package com.learnjava.HealthAndFitnessTracker.repisitory;

import com.learnjava.HealthAndFitnessTracker.model.Activity;
import com.learnjava.HealthAndFitnessTracker.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityRepository extends CrudRepository<Activity, Integer> {
    @Query("SELECT * FROM heath.activity WHERE fk_user_id = ?1")
    Optional<Activity> findByUser_user_id(int user_id);
    @Query("SELECT caloriesBurned FROM health.activity WHERE activity_id = ?1")
    Double findCaloriesBurnedById(int id);
    @Query("SELECT activity_date FROM health.activity WHERE activity_id = ?1")
    LocalDate findActivityDateById(int id);

    List<Activity> findAllById(int id);
}
