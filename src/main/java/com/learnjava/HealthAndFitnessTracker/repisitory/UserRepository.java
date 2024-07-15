package com.learnjava.HealthAndFitnessTracker.repisitory;

import com.learnjava.HealthAndFitnessTracker.model.Activity;
import com.learnjava.HealthAndFitnessTracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findUserByEmail(String email);
}
