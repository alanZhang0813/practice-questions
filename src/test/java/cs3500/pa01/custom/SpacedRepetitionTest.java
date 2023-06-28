package cs3500.pa01.custom;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class to represent one .sr file
 */
public class SpacedRepetitionTest {
  private ArrayList<Question> listOfQuestions;
  private SpacedRepetition sr;

  @BeforeEach
  void setup() {
    listOfQuestions = new ArrayList<>();
    sr = new SpacedRepetition(listOfQuestions);
  }

  @Test
  void testToString() {
    Question q1 = new Question("Question", "Answer", 1);
    q1.setDifficulty(Question.Difficulty.EASY);
    listOfQuestions.add(q1);
    String result = "Question:::Answer / EASY\n";
    assertEquals(result, sr.toString());
  }
}
