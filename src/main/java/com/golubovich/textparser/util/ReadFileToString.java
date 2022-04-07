package com.golubovich.textparser.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadFileToString {

  public static String readFileToString(String filePath) throws IOException {

    return new String(Files.readAllBytes(Paths.get(filePath)));
  }

}
