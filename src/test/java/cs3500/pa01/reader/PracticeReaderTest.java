package cs3500.pa01.reader;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for PracticeReader
 */
public class PracticeReaderTest {
  StringBuilder sbInput;
  StringBuilder sbOutput;
  PracticeReader pr;

  @BeforeEach
  void setup() {
    sbInput = new StringBuilder();
    sbOutput = new StringBuilder();
    pr = new PracticeReader(new ByteArrayInputStream(sbInput.toString().getBytes()), sbOutput);
  }

  @Test
  void testRead() {
    //sample input
    sbInput.append("src/test/resources/{inputs}/Questions.sr\n5");
    pr = new PracticeReader(new ByteArrayInputStream(sbInput.toString().getBytes()), sbOutput);

    try {
      sbOutput.append(pr.read());
    } catch (NullPointerException e) {
      assertEquals("Cannot invoke \"String.equals(Object)\" because \"content\" is null",
          e.getMessage());
    }

    assertEquals("Enter a valid file path to an .sr file:\n"
        + "How many questions would you like to practice?\n"
        + "src/test/resources/{inputs}/Questions.sr?*?5", sbOutput.toString());
  }

  @Test
  void testReadThrows() {
    sbInput.append("Invalid");
    sbInput.append("\n");
    sbInput.append("NotAnInteger");
    pr = new PracticeReader(new ByteArrayInputStream(sbInput.toString().getBytes()), sbOutput);
    try {
      sbOutput.append(pr.read());
    } catch (NumberFormatException e) {
      assertEquals("Cannot invoke \"String.equals(Object)\" because \"content\" is null",
          e.getMessage());
    }
  }

  @Test
  void testGetFilePath() {
    pr.setFilePath("src/test/resources/{inputs}/Questions.sr");
    assertEquals("src/test/resources/{inputs}/Questions.sr", pr.getFilePath());
  }
}
