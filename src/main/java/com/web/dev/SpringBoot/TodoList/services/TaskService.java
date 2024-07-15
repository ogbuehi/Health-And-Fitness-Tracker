package com.web.dev.SpringBoot.TodoList.services;

import com.web.dev.SpringBoot.TodoList.models.Task;
import com.web.dev.SpringBoot.TodoList.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private List<Task> tasks = new ArrayList<>();

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createNewTask(Task task) {
        return taskRepository.save(task);
    }
    public List<Task> getAllTasks(){
        return (List<Task>) taskRepository.findAll();
    }
    public Optional<Task> findTaskById(Long id){
        return taskRepository.findById(id);
    }
    public List<Task> findAllCompletedTasks(){
        return taskRepository.findByCompletedTrue();
    }
    public List<Task> findAllInCompletedTasks(){
        return taskRepository.findByCompletedFalse();
    }
    public Optional<Task> findByDeadLine(LocalDateTime time){
        return tasks.stream().filter(task -> task.getDeadLine().equals(time)).findFirst();
    }
    public void deleteTaskById(Long id){
        taskRepository.deleteById(id);
    }
    public void updateTask(Task task, Long id){
        Optional<Task> existingTask = findTaskById(id);
        existingTask.ifPresent(value -> tasks.set(tasks.indexOf(value), task));
    }
    public Optional<Task> findTaskByDescription(String description){
        return tasks.stream().filter(task -> task.getTask().
                contains(description)).findFirst();
    }
}
