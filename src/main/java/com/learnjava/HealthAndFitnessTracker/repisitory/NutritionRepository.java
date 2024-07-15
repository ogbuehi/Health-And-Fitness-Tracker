package com.learnjava.HealthAndFitnessTracker.repisitory;

import com.learnjava.HealthAndFitnessTracker.model.Nutrition;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NutritionRepository extends CrudRepository<Nutrition,Integer> {
    LocalDateTime findNutritionDateById(int id);
    List<Nutrition> saveAll(List<Nutrition> nutritionList);
}
