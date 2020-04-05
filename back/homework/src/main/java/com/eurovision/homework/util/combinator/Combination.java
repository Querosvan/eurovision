package com.eurovision.homework.util.combinator;

import com.google.common.primitives.Chars;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Combination {

  final List<Character> originalValues;

  final int combinationLength;

  public Combination(final String word, final int length) {
    this.originalValues = Chars.asList(word.toCharArray());
    this.combinationLength = length;
  }

  public Iterator<String> iterator() {
    return new CombinationIterator(this);
  }

  public Stream<String> stream() {
    return StreamSupport.stream(Spliterators.spliteratorUnknownSize(this.iterator(), 0), false);
  }
}
