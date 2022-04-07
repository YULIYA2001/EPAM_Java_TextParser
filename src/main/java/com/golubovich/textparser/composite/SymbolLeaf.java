package com.golubovich.textparser.composite;

import java.util.List;

public class SymbolLeaf implements TextComponent {

  private TextComponentType componentType;
  private char symbol;

  public SymbolLeaf() {
  }

  public SymbolLeaf(TextComponentType type) {
    this.componentType = type;
  }

  public SymbolLeaf(TextComponentType type, char symbol) {
    this.componentType = type;
    this.symbol = symbol;
  }

  @Override
  public TextComponentType getComponentType() {
    return componentType;
  }

  @Override
  public String getString() {
    return String.valueOf(symbol);
  }

  @Override
  public void add(TextComponent textComponent) {
    throw new UnsupportedOperationException(
        "Can't use add() method. Symbol is the smallest indivisible unit"
    );
  }

  @Override
  public void remove(TextComponent textComponent) {
    throw new UnsupportedOperationException(
        "Can't use remove() method. Symbol is the smallest indivisible unit"
    );
  }

  @Override
  public TextComponent getChild(int index) {
    throw new UnsupportedOperationException(
        "Can't use getChild() method. Symbol is the smallest indivisible unit"
    );
  }

  @Override
  public List<TextComponent> getChildren() {
    throw new UnsupportedOperationException(
        "Can't use getChildren() method. Symbol is the smallest indivisible unit"
    );
  }
}
