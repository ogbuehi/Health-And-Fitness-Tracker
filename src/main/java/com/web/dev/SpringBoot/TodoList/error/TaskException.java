package com.web.dev.SpringBoot.TodoList.error;

public class TaskException extends RuntimeException {
    public String taskException (String message){
        return message;
    }
}
