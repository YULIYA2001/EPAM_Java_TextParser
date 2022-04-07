package com.golubovich.textparser.chain.of.responsibility;

import com.golubovich.textparser.composite.TextComponent;
import com.golubovich.textparser.composite.TextComponentType;
import com.golubovich.textparser.composite.TextComposite;

public class SentenceParser implements Parser {

  private final Parser lexemeParser;
  private static final String LEXEME_REGEX = "\\s";

  {
    this.lexemeParser = new LexemeParser();
  }

  @Override
  public TextComponent parse(String sentenceData) {

    String[] lexemeList = sentenceData.trim().split(LEXEME_REGEX);
    TextComponent sentenceComposite = new TextComposite(TextComponentType.SENTENCE);

    for (String lexemeStr : lexemeList) {
      TextComponent lexemeComposite = lexemeParser.parse(lexemeStr);
      sentenceComposite.add(lexemeComposite);
    }

    return sentenceComposite;
  }
}
