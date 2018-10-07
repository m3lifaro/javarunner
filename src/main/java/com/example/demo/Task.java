package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class Task {

  public List<String> run(List<String> input){

    List<String> output = new ArrayList<>();

    output.add(String.valueOf(input.get(0).equals(input.get(1))));

    return output;
  }
}
