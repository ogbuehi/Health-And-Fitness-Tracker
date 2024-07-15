package com.web.dev.SpringBoot.TodoList.services;

import com.web.dev.SpringBoot.TodoList.error.TaskException;
import com.web.dev.SpringBoot.TodoList.models.Task;
import com.web.dev.SpringBoot.TodoList.models.TaskDto;
import com.web.dev.SpringBoot.TodoList.repositories.TaskRepository;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public ResponseEntity<String> createNewTask(TaskDto taskDto) {
        Task task = new Task();
        try {
            task.setTask(taskDto.getTask());
            task.setDeadLine(taskDto.getDeadLine());
            task.setCompleted(taskDto.getCompleted());
            taskRepository.save(task);
            return new ResponseEntity<>("SUCCESS", HttpStatus.ACCEPTED);
        }catch (TaskException e){
            e.taskException("ERROR OCCURRED WHILE ADDING TASK");
        }
        return new ResponseEntity<>("FAILED", HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<List<TaskDto>> getAllTasks(){
        List<TaskDto> taskDtos = new ArrayList<>();
        try {
            List<Task> tasks = taskRepository.findAll();
            for (Task task :
                    tasks) {
                TaskDto taskDto = new TaskDto();
                taskDto.setTask(task.getTask());
                taskDto.setDeadLine(task.getDeadLine());
                taskDto.setCompleted(task.isCompleted());
                taskDtos.add(taskDto);
            }
            return new ResponseEntity<>(taskDtos,HttpStatus.OK);
        }catch(TaskException e){
            e.taskException("Couldn't Find Tasks!!");
        }
        return new ResponseEntity<>(taskDtos,HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<TaskDto> findTaskById(Long id){
        TaskDto taskDto = new TaskDto();
        try {
            Optional<Task> task = taskRepository.findById(id);
            task.ifPresent(value -> {
                taskDto.setTask(value.getTask());
                taskDto.setDeadLine(value.getDeadLine());
            });
            return new ResponseEntity<>(taskDto,HttpStatus.FOUND);
        }catch (TaskException e){
            e.taskException("Task not found!!");
        }
        return new ResponseEntity<>(taskDto,HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<List<TaskDto>> findAllCompletedTasks(){
        List<TaskDto> taskDtos = new ArrayList<>();
        try {
            List<Task> completed = taskRepository.findByCompletedTrue();
            for (Task task :
                    completed) {
                TaskDto taskDto = new TaskDto();
                taskDto.setTask(task.getTask());
                taskDto.setDeadLine(task.getDeadLine());
                taskDto.setCompleted(task.isCompleted());
                taskDtos.add(taskDto);
            }
            return new ResponseEntity<>(taskDtos,HttpStatus.FOUND);
        }catch(TaskException e){
            e.taskException("Couldn't Find Completed Tasks!!");
        }
        return new ResponseEntity<>(taskDtos,HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<List<TaskDto>> findAllInCompletedTasks(){
        List<TaskDto> taskDtos = new ArrayList<>();
        try {
            List<Task> incomplete = taskRepository.findByCompletedFalse();
            for (Task task :
                    incomplete) {
                TaskDto taskDto = new TaskDto();
                taskDto.setTask(task.getTask());
                taskDto.setDeadLine(task.getDeadLine());
                taskDto.setCompleted(task.isCompleted());
                taskDtos.add(taskDto);
            }
            return new ResponseEntity<>(taskDtos,HttpStatus.FOUND);
        }catch (TaskException e){
            e.taskException("Couldn't Find Incomplete Tasks!!");
        }
        return new ResponseEntity<>(taskDtos,HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<TaskDto> findByDeadLine(LocalDate date){
        TaskDto taskDto = new TaskDto();
        try {
            Task task = taskRepository.findByDeadLine(date);
            taskDto.setTask(task.getTask());
            taskDto.setDeadLine(task.getDeadLine());
            taskDto.setCompleted(task.isCompleted());
            return new ResponseEntity<>(taskDto,HttpStatus.FOUND);
        }catch (TaskException e){
            e.taskException("Couldn't Find Task From DeadLine!!");
        }
        return new ResponseEntity<>(taskDto,HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<String> deleteTaskById(Long id){
        taskRepository.deleteById(id);
        return new ResponseEntity<>("DELETED",HttpStatus.OK);
    }
    public ResponseEntity<String> updateTask(Long id, TaskDto taskDto){
        Optional<Task> task = taskRepository.findById(id);
        try {
            task.ifPresent(value -> {
                value.setTask(taskDto.getTask());
                value.setDeadLine(taskDto.getDeadLine());
                value.setCompleted(taskDto.getCompleted());
                taskRepository.save(task.get());
            });
            return new ResponseEntity<>("UPDATED",HttpStatus.OK);
        }catch (TaskException e){
            e.taskException("Task not found!!");
        }
        return new ResponseEntity<>("FAILED",HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<TaskDto> findTaskByDescription(String description){
        TaskDto taskDto = new TaskDto();
        try {
            Task task = taskRepository.findByDescription(description);
            taskDto.setTask(task.getTask());
            taskDto.setDeadLine(task.getDeadLine());
            taskDto.setCompleted(task.isCompleted());
        }catch (TaskException e){
            e.taskException("Couldn't Find Task By Description!!");
        }
        return new ResponseEntity<>(taskDto,HttpStatus.OK);
    }
}
