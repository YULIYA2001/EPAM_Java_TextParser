package com.golubovich.textparser.text.processing;

import com.golubovich.textparser.composite.TextComponent;
import com.golubovich.textparser.composite.TextComponentType;
import com.golubovich.textparser.util.Pair;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TextHandlerImpl implements TextHandler {


  @Override
  public List<TextComponent> sortParagraphsBySentencesCount(TextComponent text) {

    return text.getChildren().stream()
        .sorted(Comparator.comparingInt(paragraph -> paragraph.getChildren().size()))
        .toList();
  }

  @Override
  public List<TextComponent> findSentencesWithLongestWord(TextComponent text) {
    // Find max-length WORD, count it LENGTH. Find sentences with words of length LENGTH.
    // There can be more then one word of length LENGTH. So first find length, then sentences.

    int maxWordLength = text.getChildren().stream()                   //paragraphs
        .flatMap(paragraph -> paragraph.getChildren().stream())       //sentences
        .flatMap(sentence -> sentence.getChildren().stream())         //lexemes
        .flatMap(lexeme -> lexeme.getChildren().stream())             //words/symbols/special
        .filter(word -> word.getComponentType().equals(TextComponentType.WORD))   //word
        .max(Comparator.comparingInt                                  //one of max length words
            (word -> (int) word.getChildren().stream()                //symbols:letters,signs
                .filter(
                    symbol -> symbol.getComponentType().equals(TextComponentType.LETTER)) //letters
                .count()  //symbolsCount
            )
        )
        // word -> count of letters
        .map(word -> (int) word.getChildren().stream()
            .filter(symbol -> symbol.getComponentType().equals(TextComponentType.LETTER))
            .count()
        ).orElse(0);

//    System.out.println("\nlength of longest word(s) - " + maxWordLength + "\n");

    return text.getChildren().stream()                                //paragraphs
        .flatMap(paragraph -> paragraph.getChildren().stream())       //sentences
        .filter(sentence -> sentence.getChildren().stream()           //lexemes
            .flatMap(lexeme -> lexeme.getChildren().stream())         //words/symbols/special
            .filter(word -> word.getComponentType()
                .equals(TextComponentType.WORD))                      //list words in sent
            .anyMatch(
                word -> (int) word.getChildren().stream()             //symbols:letters,signs
                    .filter(symbol -> symbol.getComponentType()
                        .equals(TextComponentType.LETTER))            //letters
                    .count() == maxWordLength
            )
        ).toList();
  }

  @Override
  public List<TextComponent> deleteSentencesWithLessThanCountWords(TextComponent text, int count) {

    List<TextComponent> needDeleteSentences =
        text.getChildren().stream()                                       //paragraphs
            .flatMap(paragraph -> paragraph.getChildren().stream())       //sentences
            .filter(sentence -> sentence.getChildren().stream()           //lexemes
                .flatMap(lexeme -> lexeme.getChildren().stream())         //words/signs/specials
                .filter(word -> word.getComponentType().equals(TextComponentType.WORD))
                .count() < count)
            .toList();

    needDeleteSentences
        .forEach(sentence -> text.getChildren()
            .forEach(paragraph -> paragraph.remove(sentence)));

//    System.out.println(text.getString());
//    for (TextComponent textComponent : needDeleteSentences)
//      System.out.println(textComponent.getString());
//    System.out.println(text.getString());

    return needDeleteSentences;
  }

  @Override
  public Map<String, Long> findSameWordsIgnoreRegister(TextComponent text) {
    Map<String, Long> sameWords = text.getChildren().stream()                    //paragraphs
        .flatMap(paragraph -> paragraph.getChildren().stream())                 //sentences
        .flatMap(sentence -> sentence.getChildren().stream())                   //lexemes
        .flatMap(lexeme -> lexeme.getChildren().stream())                       //word/sign/special
        .filter(word -> word.getComponentType().equals(TextComponentType.WORD)) //word
        // map <word, occurrencesNumber>
        .collect(
            Collectors.groupingBy(word -> word.getString().toLowerCase(), Collectors.counting()));

    sameWords.values().removeAll(Collections.singleton(1L));

    return sameWords;
  }

  @Override
  public Pair<Integer, Integer> countSentencesVowelsAndConsonants(TextComponent sentence) {
    final String vowels = "[^aeiouyауоиэы]";

    String letters = sentence.getChildren().stream()       //lexemes
        .flatMap(lexeme -> lexeme.getChildren().stream())
        .filter(
            word -> word.getComponentType().equals(TextComponentType.WORD) ||
                word.getComponentType().equals(TextComponentType.SPECIAL)
        )   //words & special
        .flatMap(word -> word.getChildren().stream())     //symbols
        .filter(symbol -> symbol.getComponentType().equals(TextComponentType.LETTER))   //letter
        .map(TextComponent::getString)
        .collect(Collectors.joining("", "", ""));

    int vowelsCount = letters.toLowerCase().replaceAll(vowels, "").length();
    int consonantsCount = letters.length() - vowelsCount;

    return new Pair<>(vowelsCount, consonantsCount);
  }

}
