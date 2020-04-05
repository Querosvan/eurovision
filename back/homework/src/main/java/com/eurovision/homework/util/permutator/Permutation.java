package com.eurovision.homework.util.permutator;

import com.google.common.primitives.Chars;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Permutation {

  final List<Character> originalValues;

  public Permutation(final String word) {
    this.originalValues = Chars.asList(word.toCharArray());
  }

  public Iterator<String> iterator() {
    return new PermutationIterator(this);
  }

  public Stream<String> stream() {
    return StreamSupport.stream(Spliterators.spliteratorUnknownSize(this.iterator(), 0), false);
  }
}
