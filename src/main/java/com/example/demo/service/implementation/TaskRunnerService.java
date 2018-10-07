package com.example.demo.service.implementation;

import freemarker.template.TemplateException;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.springframework.stereotype.Service;

@Service
public class TaskRunnerService {

    private AtomicLong counter;

    private final TestInstanceProducer testInstanceProducer;

    public TaskRunnerService(TestInstanceProducer testInstanceProducer) {
        this.counter = new AtomicLong();
        this.testInstanceProducer = testInstanceProducer;
    }

    public Result run(String classString, Long taskId) throws IOException, TemplateException {
        long executionId = counter.incrementAndGet();

        Class testInstance = testInstanceProducer.newTestInstance(executionId, taskId, classString);

        return JUnitCore.runClasses(testInstance);
    }

}
