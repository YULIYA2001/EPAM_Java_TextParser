package com.golubovich.textparser.chain.of.responsibility;

import com.golubovich.textparser.composite.SymbolLeaf;
import com.golubovich.textparser.composite.TextComponent;
import com.golubovich.textparser.composite.TextComponentType;
import com.golubovich.textparser.composite.TextComposite;

public class SpecialParser implements Parser {

  @Override
  public TextComponent parse(String specialData) {

    char[] symbolsList = specialData.toCharArray();
    TextComponent specialComposite = new TextComposite(TextComponentType.SPECIAL);

    for (char symbol : symbolsList) {
      SymbolLeaf symbolLeaf;

      if (Character.isLetter(symbol)) {
        symbolLeaf = new SymbolLeaf(TextComponentType.LETTER, symbol);
      } else {
        symbolLeaf = new SymbolLeaf(TextComponentType.SIGN, symbol);
      }

      specialComposite.add(symbolLeaf);
    }

    return specialComposite;
  }

}
