package cs3500.pa01.custom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for the Question class
 */
public class QuestionTest {
  private Question q1;
  private Question q2;

  @BeforeEach
  void setup() {
    q1 = new Question("", "", -1);
    q2 = new Question("", "", -1);
  }

  @Test
  void testGetDifficulty() {
    q1.setDifficulty(Question.Difficulty.HARD);
    assertEquals(Question.Difficulty.HARD, q1.getDifficulty());
  }

  @Test
  void testGetPrompt() {
    q1.setPrompt("What day is today?");
    assertEquals("What day is today?", q1.getPrompt());
  }

  @Test
  void testGetAnswer() {
    q1.setAnswer("Monday");
    assertEquals("Monday", q1.getAnswer());
  }

  @Test
  void testGetIndex() {
    q1.setIndex(5);
    assertEquals(5, q1.getIndex());
  }

  @Test
  void testIsDifficulty() {
    q1.setDifficulty(Question.Difficulty.HARD);
    q2.setDifficulty(Question.Difficulty.EASY);

    assertTrue(q1.isHard());
    assertTrue(q2.isEasy());
  }
}
