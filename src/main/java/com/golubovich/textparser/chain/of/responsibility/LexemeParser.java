package com.golubovich.textparser.chain.of.responsibility;

import com.golubovich.textparser.composite.SymbolLeaf;
import com.golubovich.textparser.composite.TextComponent;
import com.golubovich.textparser.composite.TextComponentType;
import com.golubovich.textparser.composite.TextComposite;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser implements Parser {

  private static final String WORD_REGEX = "[A-ZА-Яa-zа-я]+((-|')[A-ZА-Яa-zа-я]+)*";
  private static final String PUNCT_REGEX = "[\\p{Punct}…]";
  private static final String END_PUNCT_REGEX = "[\"')][,;.!?…]+";
  private final Parser wordParser;
  private final Parser specialParser;

  {
    this.wordParser = new WordParser();
    this.specialParser = new SpecialParser();
  }

  @Override
  public TextComponent parse(String lexemeData) {

    TextComponent lexemeComposite = new TextComposite(TextComponentType.LEXEME);
    TextComponent symbolLeaf;
    TextComponent wordComposite;

    if (lexemeData.matches(WORD_REGEX)) {
      wordComposite = wordParser.parse(lexemeData);
      lexemeComposite.add(wordComposite);
//      System.out.println("word: " + lexemeData);

    } else {
//      String firstSymbol = lexemeData.substring(0, 1);
//      String lastSymbol = lexemeData.substring(lexemeData.length() - 1);
      Pattern pattern = Pattern.compile(END_PUNCT_REGEX);
      Matcher matcher = pattern.matcher(lexemeData);

      if (lexemeData.matches(PUNCT_REGEX + WORD_REGEX)) {
//        System.out.println("first: " + lexemeData);
//        System.out.println(firstSymbol);

        symbolLeaf = new SymbolLeaf(TextComponentType.SIGN, lexemeData.charAt(0));
        lexemeComposite.add(symbolLeaf);
        wordComposite = wordParser.parse(lexemeData.substring(1));
        lexemeComposite.add(wordComposite);

      } else if (lexemeData.matches(WORD_REGEX + PUNCT_REGEX)) {
//        System.out.println("last: " + lexemeData);
//        System.out.println(lastSymbol);

        wordComposite = wordParser.parse(lexemeData.substring(0, lexemeData.length() - 1));
        lexemeComposite.add(wordComposite);
        symbolLeaf = new SymbolLeaf(TextComponentType.SIGN,
            lexemeData.charAt(lexemeData.length() - 1));
        lexemeComposite.add(symbolLeaf);

      } else if (lexemeData.matches(PUNCT_REGEX + WORD_REGEX + PUNCT_REGEX)) {
//        System.out.println("first&last: " + lexemeData);
//        System.out.println(firstSymbol + "  " + lastSymbol);

        symbolLeaf = new SymbolLeaf(TextComponentType.SIGN, lexemeData.charAt(0));
        lexemeComposite.add(symbolLeaf);
        wordComposite = wordParser.parse(lexemeData.substring(1, lexemeData.length() - 1));
        lexemeComposite.add(wordComposite);
        symbolLeaf = new SymbolLeaf(TextComponentType.SIGN,
            lexemeData.charAt(lexemeData.length() - 1));
        lexemeComposite.add(symbolLeaf);

      } else if (lexemeData.matches(PUNCT_REGEX + WORD_REGEX + END_PUNCT_REGEX)) {
//        System.out.println("first&last2: " + lexemeData);
//        System.out.println("first: " + firstSymbol);
//        System.out.println("last2: " + matcher.find());

        int endOfWord = 0;
        if (matcher.find()) {
          endOfWord = matcher.start();
//          System.out.println("last: " + lexemeData.substring(endOfWord));
        }

        symbolLeaf = new SymbolLeaf(TextComponentType.SIGN, lexemeData.charAt(0));
        lexemeComposite.add(symbolLeaf);
        wordComposite = wordParser.parse(lexemeData.substring(1, endOfWord));
        lexemeComposite.add(wordComposite);
        for (int i = endOfWord; i < lexemeData.length(); i++) {
          symbolLeaf = new SymbolLeaf(TextComponentType.SIGN, lexemeData.charAt(i));
          lexemeComposite.add(symbolLeaf);
        }

      } else if (lexemeData.matches(WORD_REGEX + END_PUNCT_REGEX)) {
//        System.out.println("last2: " + lexemeData);

        int endOfWord = 0;
        if (matcher.find()) {
          endOfWord = matcher.start();
//          System.out.println("last: " + lexemeData.substring(matcher.start()));
        }

        wordComposite = wordParser.parse(lexemeData.substring(0, endOfWord));
        lexemeComposite.add(wordComposite);
        for (int i = endOfWord; i < lexemeData.length(); i++) {
          symbolLeaf = new SymbolLeaf(TextComponentType.SIGN, lexemeData.charAt(i));
          lexemeComposite.add(symbolLeaf);
        }
      } else {
//        System.out.println("Special: " + lexemeData);

        // parse:  nick123   some.toString()
        TextComponent specialComposite = specialParser.parse(lexemeData);
        lexemeComposite.add(specialComposite);
      }
    }

    return lexemeComposite;
  }

}
