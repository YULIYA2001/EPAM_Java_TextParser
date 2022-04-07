package com.golubovich.textparser.composite;

public enum TextComponentType {
  TEXT("\r\n    "),
  PARAGRAPH(""),
  SENTENCE(" "),
  LEXEME(""),
  WORD(""),
  SPECIAL(""),  // nick, expression, ...
  SIGN(""),
  LETTER("");


  private String end;

  TextComponentType(String end) {
    this.end = end;
  }

  public String getEnd() {
    return end;
  }
}
