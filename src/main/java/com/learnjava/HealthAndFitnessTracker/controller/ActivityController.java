package com.learnjava.HealthAndFitnessTracker.controller;

import com.learnjava.HealthAndFitnessTracker.model.Activity;
import com.learnjava.HealthAndFitnessTracker.repisitory.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/activity")
public class ActivityController {
    @Autowired
    private ActivityRepository activityRepository;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addActivity(@RequestBody List<Activity> activities){
        activityRepository.saveAll(activities);
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Activity getActivity(@PathVariable int id){
        Optional<Activity> find = activityRepository.findByUser_user_id(id);
        if(find.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Activity not found");
        }
        return find.get();
    }
    @GetMapping("/")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Activity> findAllById(@PathVariable int id){
        return activityRepository.findAllById(id);
    }
    @GetMapping("/calories")
    @ResponseStatus(HttpStatus.FOUND)
    public Double getCaloriesBurned(@PathVariable int id){
        return activityRepository.findCaloriesBurnedById(id);
    }
    @GetMapping("/date")
    @ResponseStatus(HttpStatus.FOUND)
    public LocalDate findActivityDate(@PathVariable int id){
        return activityRepository.findActivityDateById(id);
    }
    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteActivity(@PathVariable int id){
        activityRepository.deleteById(id);
    }
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateActivity(@RequestBody Activity activity, @PathVariable int id){
        if (!activityRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Activity not found");
        }
        activityRepository.findById(id);
        activityRepository.save(activity);
    }
}
