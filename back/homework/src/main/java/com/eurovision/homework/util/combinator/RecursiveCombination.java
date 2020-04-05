package com.eurovision.homework.util.combinator;

import java.util.ArrayList;
import java.util.List;

public class RecursiveCombination {

  private RecursiveCombination() {
    throw new IllegalStateException("Utility class");
  }

  public static List<String> combinations(final String inputSet, final int k) {
    final List<String> results = new ArrayList<>();
    RecursiveCombination.combinationsInternal(inputSet, k, results, "", 0);
    return results;
  }

  private static void combinationsInternal(final String inputSet, final int k, final List<String> results, String accumulator,
      final int index) {
    final int needToAccumulate = k - accumulator.length();
    final int canAcculumate = inputSet.length() - index;

    if (accumulator.length() == k) {
      results.add(accumulator);
    } else if (needToAccumulate <= canAcculumate) {
      RecursiveCombination.combinationsInternal(inputSet, k, results, accumulator, index + 1);
      accumulator = RecursiveCombination.addChar(accumulator, inputSet.charAt(index));
      RecursiveCombination.combinationsInternal(inputSet, k, results, accumulator, index + 1);
      accumulator = RecursiveCombination.removeCharAtPosition(accumulator, accumulator.length() - 1);
    }
  }


  /**
   *
   *
   * @param accumulator
   * @param inputSet
   * @return
   */
  private static String addChar(final String accumulator, final char inputSet) {
    final StringBuilder builder = new StringBuilder(accumulator);
    builder.append(inputSet);
    return builder.toString();
  }

  /**
   *
   *
   * @param accumulator
   * @param index
   * @return
   */
  private static String removeCharAtPosition(final String accumulator, final int index) {
    final StringBuilder builder = new StringBuilder(accumulator);
    builder.deleteCharAt(index);
    return builder.toString();
  }
}
