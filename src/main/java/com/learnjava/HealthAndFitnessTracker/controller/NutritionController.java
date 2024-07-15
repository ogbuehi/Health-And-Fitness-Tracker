package com.learnjava.HealthAndFitnessTracker.controller;

import com.learnjava.HealthAndFitnessTracker.model.Nutrition;
import com.learnjava.HealthAndFitnessTracker.repisitory.NutritionRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/nutrition")
public class NutritionController {
    @Autowired
    private NutritionRepository nutritionRepository;
    @PostMapping("/list")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Nutrition> addNutritionList(@Valid @RequestBody List<Nutrition> nutrition){
        return  nutritionRepository.saveAll(nutrition);
    }
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNutrition(@Valid @RequestBody Nutrition nutrition, BindingResult result){
          nutritionRepository.save(nutrition);
    }
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void getById(@PathVariable int id){
        nutritionRepository.findById(id);
    }
    @GetMapping("{date}")
    @ResponseStatus(HttpStatus.OK)
    public LocalDateTime getByDate(@PathVariable int id){
        return nutritionRepository.findNutritionDateById(id);
    }
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateNutrition(@Valid @RequestBody Nutrition nutrition, @PathVariable int id){
        if (!nutritionRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nutrition not found");
        }
        nutritionRepository.findById(id);
        nutritionRepository.save(nutrition);
    }
    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteNutrition(@PathVariable int id){
        nutritionRepository.deleteById(id);
    }
}
