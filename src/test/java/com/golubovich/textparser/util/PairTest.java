package com.golubovich.textparser.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PairTest {

  Pair<Integer, Integer> pair = new Pair<>(1, 2);

  @Test
  void testGetFirstMethod() {
    assertEquals(1, pair.getFirst());
  }

  @Test
  void testGetSecondMethod() {
    assertEquals(2, pair.getSecond());
  }
}