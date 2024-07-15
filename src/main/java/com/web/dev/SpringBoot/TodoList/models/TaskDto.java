package com.web.dev.SpringBoot.TodoList.models;


import java.time.LocalDate;
import java.time.LocalDateTime;
//@Data
public class TaskDto {
    private String task;
    private LocalDate deadLine;
    private Boolean isCompleted;

    public TaskDto() {
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public LocalDate getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDate deadLine) {
        this.deadLine = deadLine;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }
}
