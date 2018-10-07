package com.example.demo;

import com.example.demo.entity.TaskExecutionRequest;
import com.example.demo.entity.TaskExecutionResponse;
import com.example.demo.service.implementation.TaskRunnerService;
import freemarker.template.TemplateException;
import org.junit.runner.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class TaskReceiver {

    private final TaskRunnerService taskRunnerService;

    public TaskReceiver(TaskRunnerService taskRunnerService) {
        this.taskRunnerService = taskRunnerService;
    }

    @PostMapping(path = "/execute")
    public TaskExecutionResponse executeRequest(@RequestBody TaskExecutionRequest request) throws IOException, TemplateException {

        Result run = taskRunnerService.run(request.getTaskSource(), Long.parseLong(request.getTask()));

        TaskExecutionResponse response = new TaskExecutionResponse();
        response.setCompiled(true);
        response.setDurationMs(run.getRunTime());
        response.setRqUid(request.getRqUid());
        response.setSuccessful(run.wasSuccessful());
        response.setTestCount(run.getRunCount());
        response.setTestFailed(run.getFailureCount());
        response.setFailures(run.getFailures());
        return response;
    }


}
