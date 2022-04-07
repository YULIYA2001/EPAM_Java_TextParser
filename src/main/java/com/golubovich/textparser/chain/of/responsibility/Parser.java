package com.golubovich.textparser.chain.of.responsibility;

import com.golubovich.textparser.composite.TextComponent;

public interface Parser {

  TextComponent parse(String textData);
}
