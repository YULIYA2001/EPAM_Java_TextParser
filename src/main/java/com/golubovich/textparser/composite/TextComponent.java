package com.golubovich.textparser.composite;

import java.util.List;

public interface TextComponent {

  String getString();

  void add(TextComponent textComponent);

  void remove(TextComponent textComponent);

  TextComponent getChild(int index);

  List<TextComponent> getChildren();

  TextComponentType getComponentType();
}
