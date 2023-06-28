package cs3500.pa01.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa01.processor.PracticeProcessor;
import cs3500.pa01.reader.PracticeReader;
import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.Test;

/**
 * Test class for PracticeController
 */
public class PracticeControllerTest {
  PracticeProcessor pp;
  PracticeReader pr;
  PracticeController pc;
  StringBuilder sbInput;
  StringBuilder sbOutput;

  @Test
  void testRun() {
    sbInput = new StringBuilder();
    sbOutput = new StringBuilder();
    pr = new PracticeReader(new ByteArrayInputStream(sbInput.toString().getBytes()), sbOutput);
    sbInput.append("src/test/resources/{inputs}/Questions.sr\n5\n1");

    pp = new PracticeProcessor();
    pc = new PracticeController(new ByteArrayInputStream(sbInput.toString().getBytes()), pp);
    try {
      pc.run();
    } catch (NullPointerException e) {
      assertEquals("Cannot invoke \"String.equals(Object)\" because \"userInput\" is null",
          e.getMessage());
    }
  }
}
