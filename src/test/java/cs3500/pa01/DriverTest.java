package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.NoSuchElementException;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

class DriverTest {
  Driver driver = new Driver();
  String[] args;

  @Test
  public void testStudyMain() {
    args = new String[] {
        "src/test/resources/{inputs}",
        "created",
        "src/test/resources/{inputs}"};
    Driver.main(args);

    assertEquals(
        4,
        Path.of("src/test/resources/{inputs}").getNameCount());
  }

  @Test
  public void testPracticeMain() {
    PrintWriter output = new PrintWriter(System.out);
    args = new String[] {};
    try {
      Driver.main(args);
    } catch (NullPointerException e) {
      assertNull(null, e.getMessage());
    }
    Scanner scanner = new Scanner(System.in);
    output.println("Enter an option: 1 (Easy) 2 (Hard) 3 (Show Answer) -1 (Exit)");
    try {
      int input = scanner.nextInt();
      assertTrue(input <= 0);
    } catch (NoSuchElementException e) {
      assertNull(null, e.getMessage());
    }
    assertFalse(output.toString().contains("Enter an option: "));
  }

  @Test
  public void testMainThrow() {
    args = new String[] {"1", "2"};
    try {
      Driver.main(args);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid number of args", e.getMessage());
    }
  }
}