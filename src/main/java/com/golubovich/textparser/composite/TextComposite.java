package com.golubovich.textparser.composite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TextComposite implements TextComponent {

  private final TextComponentType componentType;
  private final List<TextComponent> components;

  {
    this.components = new ArrayList<>();
  }

  public TextComposite(TextComponentType componentType) {
    this.componentType = componentType;
  }

  @Override
  public TextComponentType getComponentType() {
    return componentType;
  }


  @Override
  public String getString() {

    StringBuilder stringBuilder = new StringBuilder();

    if (!components.isEmpty()) {

      if (componentType.equals(TextComponentType.TEXT)) {
        stringBuilder.append("    ");
      }

      String endOfComponents = componentType.getEnd();
      for (TextComponent component : components) {
        stringBuilder.append(component.getString()).append(endOfComponents);
      }

      if (componentType.equals(TextComponentType.PARAGRAPH)) {
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
      }
    }
    return stringBuilder.toString();
  }

  @Override
  public void add(TextComponent textComponent) {
    components.add(textComponent);
  }

  @Override
  public void remove(TextComponent textComponent) {
    components.remove(textComponent);
  }

  @Override
  public TextComponent getChild(int index) {
    return components.get(index);
  }

  @Override
  public List<TextComponent> getChildren() {
    return Collections.unmodifiableList(components);
  }
}
