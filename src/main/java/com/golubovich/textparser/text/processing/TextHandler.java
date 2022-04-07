package com.golubovich.textparser.text.processing;

import com.golubovich.textparser.composite.TextComponent;
import com.golubovich.textparser.util.Pair;
import java.util.List;
import java.util.Map;

public interface TextHandler {
  List<TextComponent> sortParagraphsBySentencesCount(TextComponent text);
  List<TextComponent> findSentencesWithLongestWord(TextComponent text);
  List<TextComponent> deleteSentencesWithLessThanCountWords(TextComponent text, int count);
  Map<String, Long> findSameWordsIgnoreRegister(TextComponent text);
  // first - vowels, second - constants
  Pair<Integer, Integer> countSentencesVowelsAndConsonants(TextComponent sentence);
}