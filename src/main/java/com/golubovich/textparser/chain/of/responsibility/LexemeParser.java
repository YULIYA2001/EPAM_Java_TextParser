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

    if (lexemeData.matches(WORD_REGEX)) {
      addWordToLexemeComposite(lexemeComposite, lexemeData);
    } else {

      if (lexemeData.matches(PUNCT_REGEX + WORD_REGEX)) {
        addPunctToLexemeComposite(lexemeComposite, lexemeData.charAt(0));
        addWordToLexemeComposite(lexemeComposite, lexemeData.substring(1));
      } else if (lexemeData.matches(WORD_REGEX + PUNCT_REGEX)) {
        addWordToLexemeComposite(lexemeComposite, lexemeData.substring(0, lexemeData.length() - 1));
        addPunctToLexemeComposite(lexemeComposite, lexemeData.charAt(lexemeData.length() - 1));
      } else if (lexemeData.matches(PUNCT_REGEX + WORD_REGEX + PUNCT_REGEX)) {
        addPunctToLexemeComposite(lexemeComposite, lexemeData.charAt(0));
        addWordToLexemeComposite(lexemeComposite, lexemeData.substring(1, lexemeData.length() - 1));
        addPunctToLexemeComposite(lexemeComposite, lexemeData.charAt(lexemeData.length() - 1));
      } else if (lexemeData.matches(PUNCT_REGEX + WORD_REGEX + END_PUNCT_REGEX)) {
        addPunctToLexemeComposite(lexemeComposite, lexemeData.charAt(0));
        addWordAndPuncts(lexemeComposite, lexemeData, 1, findEndOfWord(lexemeData));
      } else if (lexemeData.matches(WORD_REGEX + END_PUNCT_REGEX)) {
        addWordAndPuncts(lexemeComposite, lexemeData, 0, findEndOfWord(lexemeData));
      } else {
        // parse:  nick123   some.toString()
        TextComponent specialComposite = specialParser.parse(lexemeData);
        lexemeComposite.add(specialComposite);
      }
    }

    return lexemeComposite;
  }

  private void addWordAndPuncts(TextComponent lexemeComposite, String lexemeData,
      int startOfWord, int endOfWord) {

    addWordToLexemeComposite(lexemeComposite, lexemeData.substring(startOfWord, endOfWord));

    for (int i = endOfWord; i < lexemeData.length(); i++) {
      addPunctToLexemeComposite(lexemeComposite, lexemeData.charAt(i));
    }
  }

  private int findEndOfWord(String lexemeString) {

    Pattern pattern = Pattern.compile(END_PUNCT_REGEX);
    Matcher matcher = pattern.matcher(lexemeString);
    int endOfWord = 0;

    if (matcher.find()) {
      endOfWord = matcher.start();
    }

    return endOfWord;
  }

  private void addPunctToLexemeComposite(TextComponent lexemeComposite, char punctChar) {
    TextComponent symbolLeaf = new SymbolLeaf(TextComponentType.SIGN, punctChar);
    lexemeComposite.add(symbolLeaf);
  }

  private void addWordToLexemeComposite(TextComponent lexemeComposite, String wordString) {
    TextComponent wordComposite = wordParser.parse(wordString);
    lexemeComposite.add(wordComposite);
  }
}
