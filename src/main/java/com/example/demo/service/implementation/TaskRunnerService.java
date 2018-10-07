package com.example.demo.service.implementation;

import com.example.demo.entity.RunResult;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import org.joor.Reflect;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskRunnerService {

    private AtomicLong counter = new AtomicLong();

    public RunResult run(String classString, List<List<String>> input) {
        long executionId = counter.incrementAndGet();
        RunResult result = new RunResult();
        result.setSuccessful(true);
        result.setOutput(new ArrayList<>());

        Reflect compile;
        try {
            String replace = classString.replace("public class Task {", "public class Task" + executionId + " {");
            compile = Reflect.compile("com.example.demo.Task" + executionId, replace).create();
            result.setCompiled(true);
        } catch (Exception e){
            e.printStackTrace();
            result.setCompiled(false);
            result.setSuccessful(false);
            return result;
        }

        Stopwatch stopwatch = Stopwatch.createStarted();

        for (List<String> strings : input) {
            try {
                List<String> run = compile.call("run", strings).get();
                result.getOutput().add(run);
            } catch (Exception e){
                e.printStackTrace();
                result.setSuccessful(false);
                result.getOutput().add(Lists.newArrayList("ERROR"));
            }
        }
        result.setDurationMills(stopwatch.stop().elapsed(TimeUnit.MILLISECONDS));
        return result;
    }

}
