package com.eurovision.homework.util.permutator;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RecursivePermutationTest {
  @Test
  public void test_recursive_permutation_with_repetition() {
    final List<String> permutations = RecursivePermutation.generatePermutations("maa");
    final List<String> expectedList = Arrays.asList("maa", "maa", "ama", "aam", "aam", "ama");

    System.out.println("Simple permutations of [maa]:");
    permutations.stream().forEach(System.out::println);

    Assertions.assertEquals(expectedList.size(), permutations.size());
    final AtomicInteger index = new AtomicInteger();
    permutations.stream().forEach(str -> Assertions.assertEquals(expectedList.get(index.get()), permutations.get(index.getAndIncrement())));
  }
}
