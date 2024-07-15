package com.web.dev.SpringBoot.TodoList.controllers;

import com.web.dev.SpringBoot.TodoList.models.Task;
import com.web.dev.SpringBoot.TodoList.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("")
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }
    @GetMapping("/completed")
    public ResponseEntity<List<Task>> getCompletedTasks(){
        return ResponseEntity.ok(taskService.findAllCompletedTasks());
    }
    @GetMapping("/incomplete")
    public ResponseEntity<List<Task>> getInCompleteTasks(){
        return ResponseEntity.ok(taskService.findAllInCompletedTasks());
    }
    @PostMapping("/")
    public ResponseEntity<Task> createNewTask(@RequestBody Task task){
        return ResponseEntity.ok(taskService.createNewTask(task));
    }
    @GetMapping("/deadline")
    public ResponseEntity<Optional<Task>> getTaskByDeadline(@PathVariable LocalDateTime time){
        return ResponseEntity.ok(taskService.findByDeadLine(time));
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateTask(@RequestBody Task task, @PathVariable Long id){
         taskService.updateTask(task,id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteTask(@PathVariable Long id) {
        taskService.deleteTaskById(id);
        return ResponseEntity.ok(true);
    }
}
