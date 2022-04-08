package com.golubovich.textparser.composite;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class TextCompositeTest {

  @Test
  void testGetComponentTypeMethod() {
    TextComponent word = new TextComposite(TextComponentType.WORD);
    assertEquals(TextComponentType.WORD, word.getComponentType());
  }

  @Test
  void testGetStringMethod() {
    TextComponent symbolI = new SymbolLeaf(TextComponentType.LETTER, 'I');
    TextComponent symbolA = new SymbolLeaf(TextComponentType.LETTER, 'a');
    TextComponent symbolM = new SymbolLeaf(TextComponentType.LETTER, 'm');
    TextComponent symbolDot = new SymbolLeaf(TextComponentType.SIGN, '.');

    TextComponent word1 = new TextComposite(TextComponentType.WORD);
    word1.add(symbolI);
    TextComponent word2 = new TextComposite(TextComponentType.WORD);
    word2.add(symbolA);
    word2.add(symbolM);

    TextComponent lexeme1 = new TextComposite(TextComponentType.LEXEME);
    lexeme1.add(word1);
    TextComponent lexeme2 = new TextComposite(TextComponentType.LEXEME);
    lexeme2.add(word2);
    lexeme2.add(symbolDot);

    TextComponent sentence = new TextComposite(TextComponentType.SENTENCE);
    sentence.add(lexeme1);
    sentence.add(lexeme2);

    TextComponent paragraph = new TextComposite(TextComponentType.PARAGRAPH);
    paragraph.add(sentence);

    TextComponent text = new TextComposite(TextComponentType.TEXT);
    text.add(paragraph);

    assertEquals("    I am.", text.getString());
  }

  @Test
  void testAddMethod() {
    TextComponent word = new TextComposite(TextComponentType.WORD);
    TextComponent symbolA = new SymbolLeaf(TextComponentType.LETTER, 'a');
    word.add(symbolA);

    assertNotEquals(0, word.getChildren().size());
    assertEquals(symbolA, word.getChild(0));
  }

  @Test
  void testRemoveMethod() {
    TextComponent word = new TextComposite(TextComponentType.WORD);
    TextComponent symbolA = new SymbolLeaf(TextComponentType.LETTER, 'a');
    word.add(symbolA);
    word.remove(symbolA);

    assertEquals(0, word.getChildren().size());
  }

  @Test
  void testGetChildMethod() {
    TextComponent word = new TextComposite(TextComponentType.WORD);
    TextComponent symbolA = new SymbolLeaf(TextComponentType.LETTER, 'a');
    word.add(symbolA);

    assertEquals(symbolA, word.getChild(0));
  }

  @Test
  void testGetChildrenMethod() {
    TextComponent word = new TextComposite(TextComponentType.WORD);
    TextComponent symbolA = new SymbolLeaf(TextComponentType.LETTER, 'a');
    TextComponent symbolT = new SymbolLeaf(TextComponentType.LETTER, 't');
    word.add(symbolA);
    word.add(symbolT);

    assertEquals(Arrays.asList(symbolA, symbolT), word.getChildren());
  }
}