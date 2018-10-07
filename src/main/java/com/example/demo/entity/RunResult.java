package com.example.demo.entity;

import java.util.List;

public class RunResult {

    private List<List<String>> output;
    private long durationMills;
    private boolean compiled;
    private boolean successful;

    public List<List<String>> getOutput() {
        return output;
    }

    public void setOutput(List<List<String>> output) {
        this.output = output;
    }

    public long getDurationMills() {
        return durationMills;
    }

    public void setDurationMills(long durationMills) {
        this.durationMills = durationMills;
    }

    public boolean isCompiled() {
        return compiled;
    }

    public void setCompiled(boolean compiled) {
        this.compiled = compiled;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }
}
