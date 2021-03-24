package dk.cphbusiness.mal.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileUtility {

  /**
   * Read all words from a file in lower case.
   * Filter away characters in a delimiter regex pattern.
   *
   * <code>
   * String[] words =
   *     FileUtility.toStringArray("PathToTheFile.txt", "[^A-Za-z']+");
   *     "It's" "good" "to" "learn" "math"! "Please" "follow" "these" "rules": ... !!"
   * </code>
   *
   * @param path The path to the file to read
   * @param delimiterPattern regex pattern of delimiters
   * @return an array of words from the file
   * @throws IOException if file does not exist
   */
  public static String[] toStringArray(
      String path,
      String delimiterPattern
      ) throws IOException {
    return Files.lines(Paths.get(path))
        .flatMap(line -> Stream.of(line.split(delimiterPattern)))
        .filter(word -> !word.isEmpty())
        .map(word -> word.toLowerCase())
        .toArray(String[]::new);
    }

  }
