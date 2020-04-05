package com.eurovision.homework.util.combinator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public final class CombinationTest {

  @Test
  public void test_3_combination_with_madrid() {

    final List<String> expectedList = Arrays.asList("mad", "mar", "mai", "mad", "mdr", "mdi", "mdd", "mri", "mrd", "mid", "adr", "adi",
        "add", "ari", "ard", "aid", "dri", "drd", "did", "rid");
    final List<String> combinations = new Combination("madrid", 3).stream().collect(Collectors.toList());

    System.out.println("Combinations size 3 of madrid:");
    combinations.stream().forEach(System.out::println);

    Assertions.assertEquals(expectedList.size(), combinations.size());
    final AtomicInteger index = new AtomicInteger();
    combinations.stream().forEach(str -> Assertions.assertEquals(expectedList.get(index.get()), combinations.get(index.getAndIncrement())));
  }

  @Test
  public void test_0_combination_of_madrid() {
    final List<String> combinations = new Combination("madrid", 0).stream().collect(Collectors.toList());

    System.out.println("Combinations size 0 of madrid:");
    combinations.stream().forEach(System.out::println);

    Assertions.assertEquals(1, combinations.size());
    Assertions.assertTrue(combinations.get(0).isEmpty());
  }


  @Test
  public void test_combination_iterator_remove_operation() {
    final Iterator<String> combinations = new Combination("madrid", 2).iterator();

    Assertions.assertNotNull(combinations);
    Assertions.assertTrue(combinations.hasNext());

    // this method should throw a UnsupportedOperationException
    Assertions.assertThrows(UnsupportedOperationException.class, combinations::remove);
  }

  @Test
  public void test_combination_iterator_toString() {
    final Iterator<String> combinations = new Combination("madrid", 2).iterator();

    Assertions.assertNotNull(combinations);
    Assertions.assertTrue(combinations.hasNext());
    Assertions.assertTrue(combinations.next().contains("ma"));
    Assertions.assertEquals("CombinationIterator=[#1, ma]", combinations.toString());
  }

  @Test
  public void test_2_combination_of_empty() {
    final List<String> combinations = new Combination("", 2).stream().collect(Collectors.toList());
    System.out.println("Combinations size 0 of madrid:");
    combinations.stream().forEach(System.out::println);
    Assertions.assertTrue(combinations.isEmpty());
  }
}
