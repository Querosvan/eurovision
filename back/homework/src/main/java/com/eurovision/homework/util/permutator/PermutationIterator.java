package com.eurovision.homework.util.permutator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class PermutationIterator implements Iterator<String> {

  private final int length;

  private String currentPermutation;

  private long currentIndex;

  private final int[] data;

  private boolean firstIteration = true;

  private final List<Character> initialOrderedPermutation;

  public PermutationIterator(final Permutation permutator) {
    this.length = permutator.originalValues.size();
    this.data = new int[this.length];

    final List<Character> originalVector = permutator.originalValues;
    String initialPermutation = "";

    // Create a set of the initial data
    final Set<Character> initialSet = new LinkedHashSet<>(originalVector);

    // Create internal data
    int dataValue = 0;
    int dataIndex = 0;

    this.initialOrderedPermutation = new ArrayList<>(initialSet);

    for (final Character value : this.initialOrderedPermutation) {

      dataValue++;

      if (!initialPermutation.contains(String.valueOf(value))) {
        // Determine how many duplicates of the value in the original vector
        final int count = PermutationIterator.intcountElements(originalVector, value);

        for (int countIndex = 0; countIndex < count; countIndex++) {
          this.data[dataIndex++] = dataValue;
          initialPermutation = initialPermutation.concat(String.valueOf(value));
        }
      }
    }

    // Initial status
    this.currentIndex = 0;
    this.currentPermutation = "";

    // Build first permutation
    for (int i = 0; i < this.length; i++) {
      final int index = this.data[i] - 1;
      this.currentPermutation = this.currentPermutation.concat(String.valueOf(this.initialOrderedPermutation.get(index)));
    }
  }

  /**
   * Change two values positions in an array
   *
   * @param data array
   * @param x first position
   * @param y second position
   */
  private static void swap(final int[] data, final int x, final int y) {
    final int temp = data[x];
    data[x] = data[y];
    data[y] = temp;
  }

  /**
   * Count repetitions in list
   *
   * @param list List with values
   * @param value Value to search repetitions
   * @return number of repetitions
   */
  private static int intcountElements(final List<Character> list, final Character value) {
    return Math.toIntExact(list.stream().filter(item -> item.equals(value)).count());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasNext() {
    return !this.isFinished() || this.firstIteration;
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public String next() {

    if (!this.hasNext()) {
      throw new NoSuchElementException();
    }

    // if firstIteration return the current permutation
    if (this.firstIteration) {
      this.firstIteration = false;
      return this.currentPermutation;
    }

    // start searching at the end
    int firstElementToCompare = this.data.length - 2;

    // search until which position is ordered
    while (this.data[firstElementToCompare] >= this.data[firstElementToCompare + 1]) {
      firstElementToCompare--;
    }

    // get the next value to compare
    int secondElementToCompare = this.data.length - 1;
    while (this.data[firstElementToCompare] >= this.data[secondElementToCompare]) {
      secondElementToCompare--;
    }

    // change positions
    PermutationIterator.swap(this.data, firstElementToCompare, secondElementToCompare);
    final int newLength = this.data.length - (firstElementToCompare + 1);
    for (int i = 0; i < (newLength / 2); i++) {
      PermutationIterator.swap(this.data, firstElementToCompare + 1 + i, this.data.length - i - 1);
    }

    // advance in index and reset permutation value
    this.currentIndex++;
    this.currentPermutation = "";

    // build permutation value with the current status
    for (int i = 0; i < this.length; i++) {
      final int index = this.data[i] - 1;
      this.currentPermutation = this.currentPermutation.concat(String.valueOf(this.initialOrderedPermutation.get(index)));
    }

    return this.currentPermutation;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "DuplicatedPermutationIterator=[#" + (this.currentIndex + 1) + ", " + this.currentPermutation + "]";
  }

  /**
   * Determines if all data is sorted
   *
   * @return true if algorithm is finished, false if not finished
   */
  private boolean isFinished() {
    int x = this.data.length - 2;
    while (this.data[x] >= this.data[x + 1]) {
      x--;
      if (x < 0) {
        return true;
      }
    }
    return false;
  }
}
