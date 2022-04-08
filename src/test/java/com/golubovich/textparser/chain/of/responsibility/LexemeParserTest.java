package com.golubovich.textparser.chain.of.responsibility;

import static org.junit.jupiter.api.Assertions.*;

import com.golubovich.textparser.composite.TextComponent;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class LexemeParserTest {

  @Test
  void parse() {

    List<String> lexemes = Arrays.asList(new String[]{"hi,", "\"hi", "\"hi,", "\"hi\".", "hi\".", "a.toString()"});
    Parser lexemeParser = new LexemeParser();
    TextComponent lexemeComposite;

    for (String lexeme : lexemes) {
      lexemeComposite = lexemeParser.parse(lexeme);
      assertEquals(lexeme, lexemeComposite.getString());
    }
  }
}