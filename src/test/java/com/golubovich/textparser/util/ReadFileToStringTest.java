package com.golubovich.textparser.util;

import static com.golubovich.textparser.util.Constants.FILE_PATH;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.junit.jupiter.api.Test;

class ReadFileToStringTest {

  @Test
  void readFileToString() {
    String text = null;

    try {
      text = ReadFileToString.readFileToString(FILE_PATH);
    } catch (IOException e) {
      assertNotEquals("", e.getMessage());
    }

    assertNotNull(text);
    assertNotEquals("", text);
  }
}