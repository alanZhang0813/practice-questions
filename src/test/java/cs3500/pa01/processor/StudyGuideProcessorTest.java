package cs3500.pa01.processor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import cs3500.pa01.custom.Question;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for StudyGuideProcessor
 */
public class StudyGuideProcessorTest {
  StudyGuideProcessor sgp;

  @BeforeEach
  void setup() {
    sgp = new StudyGuideProcessor();
  }

  @Test
  void testProcess() {
    String fileContent = "";
    try {
      fileContent = Files.readString(Path.of("src/test/resources/{inputs}/Questions.md"));
    } catch (IOException e) {
      fail();
    }

    String result = sgp.process(fileContent);
    ArrayList<Question> listOfQuestions = sgp.getListOfQuestions();

    assertEquals(8, listOfQuestions.size());
  }

  @Test
  void testGetListOfQuestions() {
    Question q1 = new Question("1+1 =?", "2", -1);
    ArrayList<Question> questions = new ArrayList<>();
    questions.add(q1);
    sgp.setListOfQuestions(questions);
    assertEquals(1, sgp.getListOfQuestions().size());
  }
}
