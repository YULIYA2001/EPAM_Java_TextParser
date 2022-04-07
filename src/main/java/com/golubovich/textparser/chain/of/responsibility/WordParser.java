package com.golubovich.textparser.chain.of.responsibility;

import com.golubovich.textparser.composite.SymbolLeaf;
import com.golubovich.textparser.composite.TextComponent;
import com.golubovich.textparser.composite.TextComponentType;
import com.golubovich.textparser.composite.TextComposite;

public class WordParser implements Parser {

  @Override
  public TextComponent parse(String wordData) {

    char[] lettersList = wordData.toCharArray();
    TextComponent wordComposite = new TextComposite(TextComponentType.WORD);

    for (char letter : lettersList) {

      SymbolLeaf symbolLeaf;
      if (Character.isLetter(letter)) {
        symbolLeaf = new SymbolLeaf(TextComponentType.LETTER, letter);
      } else {
        symbolLeaf = new SymbolLeaf(TextComponentType.SIGN, letter);
      }

      wordComposite.add(symbolLeaf);
    }

    return wordComposite;
  }
}
