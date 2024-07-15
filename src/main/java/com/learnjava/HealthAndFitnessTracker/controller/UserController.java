package com.learnjava.HealthAndFitnessTracker.controller;

import com.learnjava.HealthAndFitnessTracker.model.User;
import com.learnjava.HealthAndFitnessTracker.repisitory.UserRepository;
import jakarta.validation.Valid;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job job;

    @PostMapping(value = "/SignUp",consumes = "application/jason")
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@Valid @RequestBody User user){
        userRepository.save(user);
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public User findById(@PathVariable Integer id){
        Optional<User> exists = userRepository.findById(id);
        if(exists.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found");
        }
        return exists.get();
    }
    @GetMapping("/{email}")
    @ResponseStatus(HttpStatus.FOUND)
    public User findByMail(@PathVariable String email){
        return userRepository.findUserByEmail(email);
    }
    @GetMapping("")
    @ResponseStatus(HttpStatus.FOUND)
    public List<User> findAll(){
        return (List<User>) userRepository.findAll();
    }
    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Integer id){
        userRepository.deleteById(id);
    }
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateUser(@Valid @RequestBody User user, @PathVariable int id){
        if (!userRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found");
        }
        findById(id);
        userRepository.save(user);
    }
    @PostMapping
    public void importCsvToDBJob(){
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("startAt", System.currentTimeMillis())
                .toJobParameters();
        try {
            jobLauncher.run(job, jobParameters);
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobParametersInvalidException |
                 JobInstanceAlreadyCompleteException e) {
            e.printStackTrace();
        }
    }
}
