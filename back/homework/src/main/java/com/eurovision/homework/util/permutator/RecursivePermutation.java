package com.eurovision.homework.util.permutator;

import java.util.ArrayList;
import java.util.List;

public class RecursivePermutation {

  private RecursivePermutation() {
    throw new IllegalStateException("Utility class");
  }

  public static List<String> generatePermutations(final String sequence) {
    final List<String> permutations = new ArrayList<>();
    RecursivePermutation.permutationsInternal(sequence, permutations, 0);
    return permutations;
  }

  private static void permutationsInternal(String sequence, final List<String> results, final int index) {
    if (index == (sequence.length() - 1)) {
      results.add(sequence);
    }

    for (int i = index; i < sequence.length(); i++) {
      sequence = RecursivePermutation.swap(sequence, i, index);
      RecursivePermutation.permutationsInternal(sequence, results, index + 1);
      sequence = RecursivePermutation.swap(sequence, i, index);
    }
  }

  private static String swap(final String input, final int a, final int b) {
    final char tmp = input.charAt(a);
    final StringBuilder builder = new StringBuilder(input);
    builder.setCharAt(a, input.charAt(b));
    builder.setCharAt(b, tmp);
    return builder.toString();
  }

}
