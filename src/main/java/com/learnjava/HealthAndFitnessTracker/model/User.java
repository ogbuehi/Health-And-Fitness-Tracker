package com.learnjava.HealthAndFitnessTracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;
    @NotEmpty
    private String name;
    @NotEmpty(message = "Gender cannot be empty")
    private String gender;
    @NotEmpty(message = "Age cannot be empty")
    private int age;
    private double height;
    private double weight;
    @NotEmpty(message = "Email cannot be empty")
    private String email;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_user_id" , referencedColumnName = "user_id")
    private List<Activity> activities;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_user_id",referencedColumnName = "user_id")
    private List<Nutrition> nutritionList;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_user_id",referencedColumnName = "user_id")
    private List<Goal> goals;

}
