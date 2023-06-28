package cs3500.pa01.processor;

import cs3500.pa01.custom.Question;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Class that updates information
 */
public class PracticeProcessor implements Processor {
  private ArrayList<Question> questions;

  @Override
  public String process(String content) {
    String separationSymbol = "?*?";
    int separator = content.indexOf(separationSymbol);
    String filePath = content.substring(0, separator);
    String questionsString = content.substring(separator + separationSymbol.length());
    int numQuestions = Integer.parseInt(questionsString.trim());

    questions = this.generateRandomQuestions(filePath, numQuestions);
    return filePath + numQuestions;
  }

  /**
   * Class that gets a list of random questions
   *
   * @param filePath String filePath
   * @param numQuestions int numQuestions
   * @return questions Question questions
   */
  public ArrayList<Question> generateRandomQuestions(String filePath, int numQuestions) {
    ArrayList<Question> questions = new ArrayList<>();

    String fileContents;
    try {
      fileContents = Files.readString(Path.of(filePath));
    } catch (IOException e) {
      throw new IllegalArgumentException(e.getMessage());
    }

    String[] asLines = fileContents.split("\n");
    ArrayList<String> asLinesArrayList = new ArrayList<>();
    for (String s : asLines) {
      asLinesArrayList.add(s);
    }

    Collections.shuffle(asLinesArrayList);

    for (String randString : asLinesArrayList) {
      int colons = randString.indexOf(":::");
      int delineation = randString.indexOf(" / ");
      String prompt = randString.substring(0, colons);
      String answer = randString.substring(colons + ":::".length(), delineation);
      Question q = new Question(prompt, answer, questions.size() + 1);
      if (randString.substring(delineation).contains("HARD")) {
        q.setDifficulty(Question.Difficulty.HARD);
      }
      questions.add(q);
    }

    while (numQuestions < questions.size()) {
      int randInt = (int) (Math.random() * (asLinesArrayList.size() - 1));
      questions.remove(randInt);
      asLinesArrayList.remove(randInt);
    }

    return questions;
  }

  public ArrayList<Question> getQuestions() {
    return questions;
  }

  public void setQuestions(ArrayList<Question> questions) {
    this.questions = questions;
  }
}
