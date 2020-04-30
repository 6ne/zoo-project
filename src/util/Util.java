package util;

import java.util.Collections;
import java.util.Optional;
import java.util.Scanner;

public class Util {
  private static final Scanner scanner = new Scanner(System.in);

  public static int scanNumber() {
    int result;

    try {
      result = scanner.nextInt();
    } catch (Exception e) {
      result = Integer.MIN_VALUE;
    } finally {
      scanner.nextLine();
    }

    return result;
  }
  public static String scanString() {
    String result;

    try {
      result = scanner.nextLine();
    } catch (Exception e) {
      result = "";
    }

    return result;
  }

  public static void clearScreen() {
    System.out.println(String.join("", Collections.nCopies(25, "\n")));
  }

  public static void pressEnter() {
    scanner.nextLine();
  }

  public static boolean isEmpty(String w) {
    return Optional.ofNullable(w)
      .map(String::trim)
      .map(String::isEmpty)
      .orElse(true);
  }
}
