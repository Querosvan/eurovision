package com.eurovision.homework.util.permutator;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PermutationTest {

  @Test
  public void test_permutation_with_repetition() {
    final List<String> permutations = new Permutation("maa").stream().collect(Collectors.toList());
    final List<String> expectedList = Arrays.asList("maa", "ama", "aam");

    System.out.println("Simple permutations of [mad]:");
    permutations.stream().forEach(System.out::println);

    Assertions.assertEquals(expectedList.size(), permutations.size());
    final AtomicInteger index = new AtomicInteger();
    permutations.stream().forEach(str -> Assertions.assertEquals(expectedList.get(index.get()), permutations.get(index.getAndIncrement())));
  }

}
