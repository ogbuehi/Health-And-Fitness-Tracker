package com.web.dev.SpringBoot.TodoList.controllers;

import com.web.dev.SpringBoot.TodoList.models.Task;
import com.web.dev.SpringBoot.TodoList.models.TaskDto;
import com.web.dev.SpringBoot.TodoList.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    private final TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @PostMapping("/create")
    public ResponseEntity<String> createNewTask(@RequestBody TaskDto task){
        return taskService.createNewTask(task);
    }
    @GetMapping("")
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        return taskService.getAllTasks();
    }
    @GetMapping("find/{id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable Long id) {
        return taskService.findTaskById(id);
    }
    @GetMapping("/completed")
    public ResponseEntity<List<TaskDto>> getCompletedTasks(){
        return taskService.findAllCompletedTasks();
    }
    @GetMapping("/incomplete")
    public ResponseEntity<List<TaskDto>> getInCompleteTasks(){
        return taskService.findAllInCompletedTasks();
    }

    @GetMapping("/deadline/{date}")
    public ResponseEntity<TaskDto> getTaskByDeadline(@PathVariable("date") LocalDate date){
        return taskService.findByDeadLine(date);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateTask(@PathVariable Long id, @RequestBody TaskDto taskDto){
         return taskService.updateTask(id,taskDto);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        return taskService.deleteTaskById(id);
    }
}
