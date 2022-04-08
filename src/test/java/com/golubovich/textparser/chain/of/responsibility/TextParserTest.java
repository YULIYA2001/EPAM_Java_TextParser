package com.golubovich.textparser.chain.of.responsibility;

import static org.junit.jupiter.api.Assertions.*;

import com.golubovich.textparser.composite.TextComponent;
import org.junit.jupiter.api.Test;

class TextParserTest {

  @Test
  void testParseTextMethod() {
    String text = "    I am. I?\r\n    I?";
    Parser textParser = new TextParser();
    TextComponent textComposite = textParser.parse(text);

    assertEquals(text, textComposite.getString());
  }
}