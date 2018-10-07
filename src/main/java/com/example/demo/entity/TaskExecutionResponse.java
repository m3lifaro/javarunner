package com.example.demo.entity;

import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.io.Serializable;
import java.util.List;

public class TaskExecutionResponse implements Serializable {

    private String rqUid;
    private long durationMs;
    private boolean successful;
    private boolean compiled;
    private long testCount;
    private long testFailed;
    private List<Failure> failures;


    public String getRqUid() {
        return rqUid;
    }

    public void setRqUid(String rqUid) {
        this.rqUid = rqUid;
    }

    public long getDurationMs() {
        return durationMs;
    }

    public void setDurationMs(long durationMs) {
        this.durationMs = durationMs;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public boolean isCompiled() {
        return compiled;
    }

    public void setCompiled(boolean compiled) {
        this.compiled = compiled;
    }

    public long getTestCount() {
        return testCount;
    }

    public void setTestCount(long testCount) {
        this.testCount = testCount;
    }

    public long getTestFailed() {
        return testFailed;
    }

    public void setTestFailed(long testFailed) {
        this.testFailed = testFailed;
    }

    public List<Failure> getFailures() {
        return failures;
    }

    public void setFailures(List<Failure> failures) {
        this.failures = failures;
    }
}
