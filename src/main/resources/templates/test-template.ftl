package com.example.demo;

import com.google.common.collect.Lists;
import org.assertj.core.api.Assertions;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.joor.Reflect;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class ${testClassName} {

    public static Reflect instance;

    private static String classString = "${classString}";

        @Parameterized.Parameters
        public static Collection
<Object[]> data(){
        return Arrays.asList(new Object[][]{
<#list testData?keys as key>
        {Lists.newArrayList(${key}),    Lists.newArrayList(${testData[key]})},
</#list>
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
        instance = Reflect.compile("${className}", classString).create();
        }

        @Test
        public void test() {
        List
        <String> result = instance.call("run", input).get();
            Assertions.assertThat(result).isEqualTo(output);
            }

            }
