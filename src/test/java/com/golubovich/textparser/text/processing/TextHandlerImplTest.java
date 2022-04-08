package com.golubovich.textparser.text.processing;

import static org.junit.jupiter.api.Assertions.*;

import com.golubovich.textparser.composite.SymbolLeaf;
import com.golubovich.textparser.composite.TextComponent;
import com.golubovich.textparser.composite.TextComponentType;
import com.golubovich.textparser.composite.TextComposite;
import com.golubovich.textparser.util.Pair;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class TextHandlerImplTest {

  private final TextComponent word1;

  private final TextComponent sentence1;
  private final TextComponent sentence2;

  private final TextComponent paragraph1;
  private final TextComponent paragraph2;

  private TextComponent text;

  private final TextHandler textHandler;

  {
    TextComponent symbolI = new SymbolLeaf(TextComponentType.LETTER, 'I');
    TextComponent symbolA = new SymbolLeaf(TextComponentType.LETTER, 'a');
    TextComponent symbolM = new SymbolLeaf(TextComponentType.LETTER, 'm');
    TextComponent symbolDot = new SymbolLeaf(TextComponentType.SIGN, '.');
    TextComponent symbolQuestion = new SymbolLeaf(TextComponentType.SIGN, '?');

    word1 = new TextComposite(TextComponentType.WORD);
    word1.add(symbolI);
    TextComponent word2 = new TextComposite(TextComponentType.WORD);
    word2.add(symbolA);
    word2.add(symbolM);

    TextComponent lexeme1 = new TextComposite(TextComponentType.LEXEME);
    lexeme1.add(word1);
    TextComponent lexeme2 = new TextComposite(TextComponentType.LEXEME);
    lexeme2.add(word2);
    lexeme2.add(symbolDot);
    TextComponent lexeme3 = new TextComposite(TextComponentType.LEXEME);
    lexeme3.add(word1);
    lexeme3.add(symbolQuestion);

    sentence1 = new TextComposite(TextComponentType.SENTENCE);
    sentence1.add(lexeme1);
    sentence1.add(lexeme2);
    sentence2 = new TextComposite(TextComponentType.SENTENCE);
    sentence2.add(lexeme3);

    paragraph1 = new TextComposite(TextComponentType.PARAGRAPH);
    paragraph1.add(sentence1);
    paragraph1.add(sentence2);
    paragraph2 = new TextComposite(TextComponentType.PARAGRAPH);
    paragraph2.add(sentence2);

    text = new TextComposite(TextComponentType.TEXT);
    text.add(paragraph1);
    text.add(paragraph2);

    textHandler = new TextHandlerImpl();
  }

  @Test
  void sortParagraphsBySentencesCount() {
    List<TextComponent> sortedParagraphs = textHandler.sortParagraphsBySentencesCount(text);

    assertEquals(paragraph2.getString(), sortedParagraphs.get(0).getString());
    assertEquals(paragraph1.getString(), sortedParagraphs.get(1).getString());
  }

  @Test
  void findSentencesWithLongestWord() {
    List<TextComponent> sentencesWithLongestWords = textHandler.findSentencesWithLongestWord(text);

    assertEquals(1, sentencesWithLongestWords.size());
    assertEquals(sentence1.getString(), sentencesWithLongestWords.get(0).getString());
  }

  @Test
  void deleteSentencesWithLessThanCountWords() {
    List<TextComponent> deletedSentences = textHandler.deleteSentencesWithLessThanCountWords(
        text, 2);

    assertEquals(2, deletedSentences.size());
    assertEquals(sentence2.getString(), deletedSentences.get(0).getString());

    text = new TextComposite(TextComponentType.TEXT);
    text.add(paragraph1);
    text.add(paragraph2);
  }

  @Test
  void findSameWordsIgnoreRegister() {
    Map<String, Long> sameWords = textHandler.findSameWordsIgnoreRegister(text);

    assertEquals(1, sameWords.entrySet().size());
    assertEquals(3L, sameWords.get(word1.getString().toLowerCase()));
  }

  @Test
  void countSentencesVowelsAndConsonants() {
    Pair<Integer, Integer> vowelsAndConsonants = textHandler
        .countSentencesVowelsAndConsonants(text.getChild(0).getChild(0));

    assertEquals(2, vowelsAndConsonants.getFirst());
    assertEquals(1, vowelsAndConsonants.getSecond());
  }
}