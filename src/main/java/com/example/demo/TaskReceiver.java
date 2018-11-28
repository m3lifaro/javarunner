package com.example.demo;

import com.example.demo.entity.RunResult;
import com.example.demo.entity.TaskExecutionRequest;
import com.example.demo.entity.TaskExecutionResponse;
import com.example.demo.service.implementation.TaskRunnerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskReceiver {

    private final TaskRunnerService taskRunnerService;

    public TaskReceiver(TaskRunnerService taskRunnerService) {
        this.taskRunnerService = taskRunnerService;
    }

    @PostMapping(path = "/execute")
    public TaskExecutionResponse executeRequest(@RequestBody TaskExecutionRequest request) {

        RunResult run = taskRunnerService.run(request.getTaskSource(), request.getInput());

        TaskExecutionResponse response = new TaskExecutionResponse();
        response.setCompiled(run.isCompiled());
        response.setDurationMs(run.getDurationMills());
        response.setRqUid(request.getRqUid());
        response.setSuccessful(run.isSuccessful());
        response.setTestCount(run.getOutput().size());
        response.setTestFailed(run.getOutput().stream().filter(strings -> strings.contains("ERROR")).count());
        response.setOutput(run.getOutput());
        return response;
    }

}
