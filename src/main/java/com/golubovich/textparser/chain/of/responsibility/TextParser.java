package com.golubovich.textparser.chain.of.responsibility;

import com.golubovich.textparser.composite.TextComponent;
import com.golubovich.textparser.composite.TextComponentType;
import com.golubovich.textparser.composite.TextComposite;

public class TextParser implements Parser {

  private final Parser paragraphParser;
  private static final String PARAGRAPH_REGEX = "\\r\\n\\t|\\r\\n\\s{4}";

  {
    this.paragraphParser = new ParagraphParser();
  }

  @Override
  public TextComponent parse(String textData) {

    String[] paragraphsList = textData.trim().split(PARAGRAPH_REGEX);
    TextComponent textComposite = new TextComposite(TextComponentType.TEXT);

    for (String paragraphStr : paragraphsList) {
      TextComponent paragraphComposite = paragraphParser.parse(paragraphStr);
      textComposite.add(paragraphComposite);
    }

    return textComposite;
  }
}
