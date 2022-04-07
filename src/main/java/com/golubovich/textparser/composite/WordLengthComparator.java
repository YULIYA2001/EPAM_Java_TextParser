package com.golubovich.textparser.composite;

//import java.util.ArrayList;
//import java.util.Comparator;
//
//public class WordLengthComparator implements Comparator<TextComponent> {
//
//  @Override
//  public int compare(TextComponent lexeme1, TextComponent lexeme2) {
//
//    TextComponent word1 = null;
//    try {
//      word1 = ((TextComposite)lexeme1).clone();
//    } catch (CloneNotSupportedException e) {
//      e.printStackTrace();
//    }
//    //new TextComposite(TextComponentType.WORD, new ArrayList<>(lexeme1.getChildren()));
//
//    if (TextComponentType.SIGN.equals(word1.getChildren().get(0).getComponentType())) {
//      word1.getChildren().remove(0);
//    }
//
//    if (TextComponentType.SIGN.equals(
//
//        wo  word1.getChildren().get(word1.getChildren().size()-1).getComponentType())
//    ) {rd1.getChildren().remove(word1.getChildren().size()-1);
//    }
//
//    for (int i = 0; i < word1.getChildren().size(); i++) {
//      if (TextComponentType.SIGN.equals(word1.getChild(i).getComponentType())) {
//        word1.remove(word1.getChild(i));
//      }
//    }
//
//    TextComponent word2 =
//        new TextComposite(TextComponentType.WORD, new ArrayList<>(lexeme2.getChildren()));
//
//    if (TextComponentType.SIGN.equals(word2.getChildren().get(0).getComponentType())) {
//      word2.getChildren().remove(0);
//    }
//
//    if (TextComponentType.SIGN.equals(
//        word2.getChildren().get(word2.getChildren().size()-1).getComponentType())
//    ) {
//      word2.getChildren().remove(word2.getChildren().size()-1);
//    }
//
//    for (int i = 0; i < word2.getChildren().size(); i++) {
//      if (TextComponentType.SIGN.equals(word2.getChild(i).getComponentType())) {
//        word2.remove(word2.getChild(i));
//      }
//    }
//
//    System.out.println(lexeme1.getString());
//    System.out.println(lexeme2.getString());
//    System.out.println(word1.getString());
//    System.out.println(word2.getString());
//    System.out.println(Integer.compare(word1.getChildren().size(), word2.getChildren().size()));
//
//
//    return Integer.compare(word1.getChildren().size(), word2.getChildren().size());
//  }
//}
//
////    lexeme1.getChildren().stream()
////        .filter(symbol -> TextComponentType.SIGN.equals(symbol.getComponentType()))
////        .peek(sign ->
////            lexeme1.getChildren().indexOf(sign) == 0  lexeme1.getChildren().remove(0)) ||
////                      lexeme1.getChildren().indexOf(sign) == lexeme1.getChildren().size() ? );
