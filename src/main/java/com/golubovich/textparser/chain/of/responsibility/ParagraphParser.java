package com.golubovich.textparser.chain.of.responsibility;

import com.golubovich.textparser.composite.TextComponent;
import com.golubovich.textparser.composite.TextComponentType;
import com.golubovich.textparser.composite.TextComposite;

public class ParagraphParser implements Parser {

  private final Parser sentenceParser;
  // (?=...) Ретроспективная проверка (Lookbehind)
  private static final String SENTENCE_REGEX = "(?<=[.?!…])\\s";

  {
    this.sentenceParser = new SentenceParser();
  }

  @Override
  public TextComponent parse(String paragraphData) {

    String[] sentencesList = paragraphData.trim().split(SENTENCE_REGEX);
    TextComponent paragraphComposite = new TextComposite(TextComponentType.PARAGRAPH);

    for (String sentenceStr : sentencesList) {
      TextComponent sentenceComposite = sentenceParser.parse(sentenceStr);
      paragraphComposite.add(sentenceComposite);
    }

    return paragraphComposite;
  }


}
