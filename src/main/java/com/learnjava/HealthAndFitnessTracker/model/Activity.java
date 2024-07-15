package com.learnjava.HealthAndFitnessTracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_id")
    private int activityId;
    @NotEmpty(message = "Activity type cannot be empty")
    private String type;
    private int duration;
    private double distance;
    private double caloriesBurned;
    @NotEmpty(message = "Activity date cannot be empty")
    private LocalDate activity_date;

}
