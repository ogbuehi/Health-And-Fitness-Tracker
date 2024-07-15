package com.learnjava.HealthAndFitnessTracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Nutrition {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "nutrition_id")
        private int nutritionId;
        @NotEmpty(message = "Meal type cannot be empty")
        private String mealType;
        private String foodItem;
        private double quantity;
        private double calories;
        @NotEmpty(message = "Meal time cannot be empty")
        private LocalDateTime time;
}
