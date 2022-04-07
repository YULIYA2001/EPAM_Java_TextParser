package com.golubovich.textparser;

import static com.golubovich.textparser.util.Constants.FILE_PATH;

import com.golubovich.textparser.chain.of.responsibility.Parser;
import com.golubovich.textparser.chain.of.responsibility.TextParser;
import com.golubovich.textparser.composite.TextComponent;
import com.golubovich.textparser.text.processing.TextHandler;
import com.golubovich.textparser.text.processing.TextHandlerImpl;
import com.golubovich.textparser.util.Pair;
import com.golubovich.textparser.util.ReadFileToString;
import java.io.IOException;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

  private static final Logger logger = LogManager.getLogger(Main.class);

  public static void main(String[] args) {

    String text = "";
    try {
      text = ReadFileToString.readFileToString(FILE_PATH);
    } catch (IOException e) {
      logger.error(e.getMessage());
    }

    System.out.println("Text:\n-------\n" + text + "\n");

    Parser textParser = new TextParser();
    TextComponent textComposite = textParser.parse(text);

    String str = textComposite.getString();
    System.out.println("Restored:\n----------\n" + str);

    TextHandler textHandler = new TextHandlerImpl();

    System.out
        .println("\nParagraphs sorted by sentences count:\n--------------------------------------");
    for (TextComponent paragraph : textHandler.sortParagraphsBySentencesCount(textComposite)) {
      System.out.println(paragraph.getString());
    }

    System.out.println("\nSentences with longest word(s):\n--------------------------------------");
    for (TextComponent textComponent : textHandler.findSentencesWithLongestWord(textComposite)) {
      System.out.println(textComponent.getString());
    }

    int minCountOfWordsInSentence = 3;
    System.out.println("\nSentences with words count less " +
        minCountOfWordsInSentence + ":\n----------------------------------------");
    for (TextComponent sentence : textHandler.deleteSentencesWithLessThanCountWords(
        textComposite, minCountOfWordsInSentence)
    ) {
      System.out.println(sentence.getString());
    }
    System.out.println("\nModified text: ");
    System.out.println(textComposite.getString());

    System.out.println("\nCount of same words in text:\n-----------------------------------");
    Map<String, Long> sameWords = textHandler.findSameWordsIgnoreRegister(textComposite);
    sameWords.entrySet().stream()
        .sorted(Map.Entry.comparingByValue())
        .forEach(element -> System.out.println(element.getValue() + " - " + element.getKey()));

    System.out.println("\nCount vowels & consonants in sentences:\n------------------------------");
    Pair<Integer, Integer> vowelsAndConsonants = textHandler
        .countSentencesVowelsAndConsonants(textComposite.getChild(0).getChild(0));
    System.out.println(
        "Vowels: " + vowelsAndConsonants.getFirst() +
            "  Consonants: " + vowelsAndConsonants.getSecond());
  }
}
