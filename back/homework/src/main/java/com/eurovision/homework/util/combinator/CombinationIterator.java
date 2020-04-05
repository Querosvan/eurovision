package com.eurovision.homework.util.combinator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CombinationIterator implements Iterator<String> {

  private final Combination generator;

  private final int valuesLength;

  private final int combinationLength;

  private String currentCombination = null;

  private long currentIndex = 0;

  // Criteria to stop iterating
  private int endIndex = 0;

  // Auxiliary array for positions
  private int[] auxPositions = null;

  CombinationIterator(final Combination generator) {
    this.generator = generator;
    this.valuesLength = generator.originalValues.size();
    this.combinationLength = generator.combinationLength;
    this.currentCombination = "";
    this.auxPositions = new int[this.combinationLength + 1];
    for (int i = 0; i <= this.combinationLength; i++) {
      this.auxPositions[i] = i;
    }
    if (this.valuesLength > 0) {
      this.endIndex = 1;
    }
    this.currentIndex = 0;
  }

  /**
   *
   *
   * @param myString
   * @param index
   * @param value
   * @return
   */
  private static String setValue(final String myString, final int index, final Character value) {
    final StringBuilder sb = (index < myString.length()) ? new StringBuilder("") : new StringBuilder(myString);
    sb.insert(index, value);
    return sb.toString();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasNext() {
    return !((this.endIndex == 0) || (this.combinationLength > this.valuesLength));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String next() {
    if (!this.hasNext()) {
      throw new NoSuchElementException();
    }

    this.currentIndex++;

    for (int i = 1; i <= this.combinationLength; i++) {
      final int index = this.auxPositions[i] - 1;
      if (!this.generator.originalValues.isEmpty()) {
        this.currentCombination = CombinationIterator.setValue(this.currentCombination, i - 1, this.generator.originalValues.get(index));
      }
    }

    this.endIndex = this.combinationLength;

    while (this.auxPositions[this.endIndex] == ((this.valuesLength - this.combinationLength) + this.endIndex)) {
      this.endIndex--;
      if (this.endIndex == 0) {
        break;
      }
    }
    this.auxPositions[this.endIndex]++;
    for (int i = this.endIndex + 1; i <= this.combinationLength; i++) {
      this.auxPositions[i] = this.auxPositions[i - 1] + 1;
    }

    // return the current combination
    return this.currentCombination;
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
    return "CombinationIterator=[#" + this.currentIndex + ", " + this.currentCombination + "]";
  }

}
