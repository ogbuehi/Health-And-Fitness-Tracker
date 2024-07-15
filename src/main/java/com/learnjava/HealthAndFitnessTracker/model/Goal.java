package com.learnjava.HealthAndFitnessTracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goal_id")
    private int goalId;
    @NotEmpty(message = "goal type cannot be empty")
    private String goalType;
    private double targetValue;
    private double progress;
    @NotEmpty(message = "goal deadline cannot be empty")
    private LocalDate deadline;
}
