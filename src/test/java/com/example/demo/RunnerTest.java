package com.example.demo;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.Lists;
import org.assertj.core.api.Assertions;
import org.joor.Reflect;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class RunnerTest {

    public static Reflect instance;

    private static String classString = "package com.example.demo;\n\nimport java.util.ArrayList;\nimport java.util.List;\n\npublic class Task1 {\n\n  public List<String> run(List<String> input){\n\n    List<String> output = new ArrayList<>();\n\n    output.add(String.valueOf(input.get(0).equals(input.get(1))));\n\n\n    return output;\n  }\n}\n";

    @Parameterized.Parameters
    public static Collection
        <Object[]> data(){
        return Arrays.asList(new Object[][]{
                {Lists.newArrayList("yes","no"),    Lists.newArrayList("false")},
                {Lists.newArrayList("bla","bla"),    Lists.newArrayList("true")},
            }
        );

    }

    @Parameterized.Parameter
    public List
        <String> input;

    @Parameterized.Parameter(1)
    public List
        <String> output;

    @BeforeClass
    public static void setup(){
        instance = Reflect.compile("com.example.demo.Task1", classString).create();
    }

    @Test
    public void test() {
        List
            <String> result = instance.call("run", input).get();
        Assertions.assertThat(result).isEqualTo(output);
    }

}
