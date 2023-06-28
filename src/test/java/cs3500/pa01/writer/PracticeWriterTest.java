package cs3500.pa01.writer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa01.custom.Question;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for PracticeWriter
 */
public class PracticeWriterTest {
  private StringBuilder sbInput;
  private StringBuilder sbOutput;
  private PracticeWriter pw;
  private ArrayList<Question> listOfQuestions;
  private PrintStream output;

  @BeforeEach
  void setup() {
    sbInput = new StringBuilder();
    sbOutput = new StringBuilder();
    pw = new PracticeWriter(sbInput);
    listOfQuestions = new ArrayList<>();
    output = new PrintStream(System.out);
  }

  @Test
  void testWrite() {
    Question q1 = new Question("A question", " an answer", 1);
    Question q2 = new Question("", "", 2);
    Question q3 = new Question("", "", 3);

    q1.setDifficulty(Question.Difficulty.HARD);
    q2.setDifficulty(Question.Difficulty.EASY);
    q3.setDifficulty(Question.Difficulty.EASY);

    listOfQuestions.add(q1);
    listOfQuestions.add(q2);
    listOfQuestions.add(q3);


    sbInput.append("1\n2\n3\n4\n-1");
    String[] inputs = sbInput.toString().split("\n");
    //sample input

    for (String input : inputs) {
      InputStream inputStream = new ByteArrayInputStream(input.getBytes());
      System.setIn(inputStream);
      pw = new PracticeWriter(sbOutput);
      pw.setListOfQuestions(listOfQuestions);
      try {
        pw.write("content", "location");
      } catch (NullPointerException e) {
        assertEquals("Cannot invoke \"String.equals(Object)\" because \"userInput\" is null",
            e.getMessage());
      }
    }

    assertEquals("A question\n" +
            "Enter an option: 1 (Easy) 2 (Hard) 3 (Show Answer) -1 (Exit)\n" +
            "\n" +
            "Enter an option: 1 (Easy) 2 (Hard) 3 (Show Answer) -1 (Exit)\n",
        pw.getOutput().toString());
  }

  @Test
  void testGetQuestions() {
    Question q = new Question("", "", 1);
    listOfQuestions.add(q);
    pw.setListOfQuestions(listOfQuestions);
    assertEquals(1, pw.getListOfQuestions().size());
  }
}
