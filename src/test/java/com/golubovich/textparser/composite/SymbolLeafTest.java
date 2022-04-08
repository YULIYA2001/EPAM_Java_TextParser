package com.golubovich.textparser.composite;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SymbolLeafTest {

  @Test
  void testGetComponentTypeMethod() {
    SymbolLeaf symbol = new SymbolLeaf(TextComponentType.SIGN, '*');
    assertEquals(TextComponentType.SIGN, symbol.getComponentType());
  }

  @Test
  void testGetStringMethod() {
    SymbolLeaf symbol = new SymbolLeaf(TextComponentType.SIGN, '*');
    assertEquals("*", symbol.getString());
  }

  @Test
  void testAddLeafMethod() {
    SymbolLeaf symbol = new SymbolLeaf(TextComponentType.LETTER, 'a');

    try {
      symbol.add(symbol);
    } catch (UnsupportedOperationException e) {
      assertEquals("Can't use add() method. Symbol is the smallest indivisible unit",
          e.getMessage());
    }
  }

  @Test
  void testRemoveLeafMethod() {
    SymbolLeaf symbol = new SymbolLeaf(TextComponentType.LETTER, 'a');

    try {
      symbol.remove(symbol);
    } catch (UnsupportedOperationException e) {
      assertEquals("Can't use remove() method. Symbol is the smallest indivisible unit",
          e.getMessage());
    }
  }

  @Test
  void testGetChildMethod() {
    SymbolLeaf symbol = new SymbolLeaf(TextComponentType.LETTER, 'a');

    try {
      symbol.getChild(0);
    } catch (UnsupportedOperationException e) {
      assertEquals("Can't use getChild() method. Symbol is the smallest indivisible unit",
          e.getMessage());
    }
  }

  @Test
  void testGetChildrenMethod() {
    SymbolLeaf symbol = new SymbolLeaf(TextComponentType.LETTER, 'a');

    try {
      symbol.getChildren();
    } catch (UnsupportedOperationException e) {
      assertEquals("Can't use getChildren() method. Symbol is the smallest indivisible unit",
          e.getMessage());
    }
  }
}