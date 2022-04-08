package com.golubovich.textparser.chain.of.responsibility;

import static org.junit.jupiter.api.Assertions.*;

import com.golubovich.textparser.composite.TextComponent;
import org.junit.jupiter.api.Test;

class WordParserTest {

  @Test
  void testParseWordMethod() {
    String word = "hi-hi";
    Parser textParser = new WordParser();
    TextComponent wordComposite = textParser.parse(word);

    assertEquals(word, wordComposite.getString());
  }
}