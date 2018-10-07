package com.example.demo.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestData {

    private Map<Long, List<String>> input;
    private Map<Long, List<String>> output;

    public Map<Long, List<String>> getInput() {
        return input;
    }

    public void setInput(Map<Long, List<String>> input) {
        this.input = input;
    }

    public Map<Long, List<String>> getOutput() {
        return output;
    }

    public void setOutput(Map<Long, List<String>> output) {
        this.output = output;
    }

    public Map<List<String>, List<String>> toInputOutput() {
        Map<List<String>, List<String>> result = new HashMap<>();

        for (Map.Entry<Long, List<String>> listEntry : input.entrySet()) {
            result.put(listEntry.getValue(), output.get(listEntry.getKey()));
        }

        return result;
    }
}
