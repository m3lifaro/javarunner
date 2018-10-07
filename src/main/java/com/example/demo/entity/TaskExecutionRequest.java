package com.example.demo.entity;

import java.io.Serializable;

public class TaskExecutionRequest implements Serializable{

    private String task;
    private String taskSource;
    private String rqUid;

    public void setTask(String task) {
        this.task = task;
    }

    public void setTaskSource(String taskSource) {
        this.taskSource = taskSource;
    }

    public void setRqUid(String rqUid) {
        this.rqUid = rqUid;
    }

    public String getTask() {
        return task;
    }

    public String getTaskSource() {
        return taskSource;
    }

    public String getRqUid() {
        return rqUid;
    }
}
