package com.learnjava.HealthAndFitnessTracker.controller;

import com.learnjava.HealthAndFitnessTracker.model.Goal;
import com.learnjava.HealthAndFitnessTracker.model.Nutrition;
import com.learnjava.HealthAndFitnessTracker.repisitory.GoalRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/goal")
public class GoalController {
    @Autowired
    private GoalRepository goalRepository;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@Valid @RequestBody Goal gaol){
        goalRepository.save(gaol);
    }
    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteGoal(@PathVariable int id){
        goalRepository.deleteById(id);
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Goal findGoal(@PathVariable int id){
        Optional<Goal> goalOptional = goalRepository.findById(id);
        if (goalOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Goal not found");
        }
        return goalOptional.get();
    }
    @GetMapping("goal_type/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public String getGoalType(@PathVariable int id){
        return goalRepository.findGoalTypeById(id);
    }
    @GetMapping("target_value/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Double getTargetValue(@PathVariable int id){
        return goalRepository.findTargetValueById(id);
    }
    @GetMapping("progress/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Double getProgress(@PathVariable int id){
        return goalRepository.findProgressById(id);
    }
    @GetMapping("deadline/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public LocalDate getDeadline(@PathVariable int id){
        return goalRepository.findDeadlineById(id);
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Goal> getGoals(@PathVariable int id){
        return goalRepository.findAllById(id);
    }
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateGoal(@Valid @RequestBody Goal goal, @PathVariable int id){
        if (!goalRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Goal not found");
        }
        goalRepository.findById(id);
        goalRepository.save(goal);
    }
}
