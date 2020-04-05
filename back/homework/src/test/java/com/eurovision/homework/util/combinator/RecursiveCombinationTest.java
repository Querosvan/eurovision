package com.eurovision.homework.util.combinator;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RecursiveCombinationTest {

  @Test
  public void test_3_recursive_combination_with_madrid() {

    final List<String> expectedList = Arrays.asList("rid", "did", "drd", "dri", "aid", "ard", "ari", "add", "adi", "adr", "mid", "mrd",
        "mri", "mdd", "mdi", "mdr", "mad", "mai", "mar", "mad");
    final List<String> combinations = RecursiveCombination.combinations("madrid", 3);

    System.out.println("Combinations size 3 of madrid:");
    combinations.stream().forEach(System.out::println);

    Assertions.assertEquals(expectedList.size(), combinations.size());
    final AtomicInteger index = new AtomicInteger();
    combinations.stream().forEach(str -> Assertions.assertEquals(expectedList.get(index.get()), combinations.get(index.getAndIncrement())));
  }
}
