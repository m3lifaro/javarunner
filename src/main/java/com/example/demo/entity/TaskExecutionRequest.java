package com.example.demo.entity;

import java.io.Serializable;
import java.util.List;

public class TaskExecutionRequest implements Serializable{

    private String taskSource;
    private String rqUid;
    private List<List<String>> input;

    public void setTaskSource(String taskSource) {
        this.taskSource = taskSource;
    }

    public void setRqUid(String rqUid) {
        this.rqUid = rqUid;
    }

    public String getTaskSource() {
        return taskSource;
    }

    public String getRqUid() {
        return rqUid;
    }

    public List<List<String>> getInput() {
        return input;
    }

    public void setInput(List<List<String>> input) {
        this.input = input;
    }
}
