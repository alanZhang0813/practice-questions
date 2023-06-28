package cs3500.pa01.processor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa01.custom.Question;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for PracticeProcessor
 */
public class PracticeProcessorTest {
  PracticeProcessor pp;
  ArrayList<Question> listOfQuestions;

  @BeforeEach
  void setup() {
    pp = new PracticeProcessor();
    listOfQuestions = new ArrayList<>();
  }

  @Test
  void testGenerateRandomQuestions() {
    listOfQuestions = pp.generateRandomQuestions(
        "src/test/resources/{inputs}/Questions.sr", 4);
    assertEquals(4, listOfQuestions.size());
  }

  @Test
  void testGenerateThrow() {

  }

  @Test
  void testProcess() {
    String content = "src/test/resources/{inputs}/Questions.sr?*? 10";
    String result = pp.process(content);
    assertEquals("src/test/resources/{inputs}/Questions.sr10", result);
  }

  @Test
  void testGetQuestions() {
    pp.setQuestions(new ArrayList<>());
    assertEquals(0, pp.getQuestions().size());
  }
}
