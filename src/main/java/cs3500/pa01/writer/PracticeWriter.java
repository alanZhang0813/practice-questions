package cs3500.pa01.writer;

import cs3500.pa01.custom.Question;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Class to represent writing out the practice questions
 */
public class PracticeWriter implements Writer {
  private ArrayList<Question> listOfQuestions;
  private final Appendable output;

  public PracticeWriter(Appendable output) {
    this.output = output;
  }

  @Override
  public void write(String content, String location) {
    int answered = 0;
    int easyToHard = 0;
    int hardToEasy = 0;
    int leftOverHard = 0;
    int leftOverEasy = 0;

    for (Question question : listOfQuestions) {
      try {
        output.append(question.getPrompt());
        output.append("\n");
        output.append("Enter an option: 1 (Easy) 2 (Hard) 3 (Show Answer) -1 (Exit)");
        output.append("\n");
        String userInput = getUserInput();

        if (userInput.equals("1")) {
          if (question.isHard()) {
            question.setDifficulty(Question.Difficulty.EASY);
            hardToEasy++;
            answered++;
          }
        } else if (userInput.equals("2")) {
          if (question.isEasy()) {
            question.setDifficulty(Question.Difficulty.HARD);
            easyToHard++;
            answered++;
          }
        } else if (userInput.equals("3")) {
          output.append("The answer was: " + question.getAnswer());
          output.append("\n");
          if (question.isEasy()) {
            question.setDifficulty(Question.Difficulty.HARD);
            easyToHard++;
          }
        } else if (userInput.equals("-1")) {
          break;
        } else {
          output.append("Please only enter one of the options");
          output.append("\n");
          userInput = getUserInput();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    for (Question q : listOfQuestions) {
      if (q.isHard()) {
        leftOverHard++;
      }

      if (q.isEasy()) {
        leftOverEasy++;
      }
    }

    try {
      output.append("After your practice session, you:");
      output.append("\n");
      output.append("Answered " + answered + " questions");
      output.append("\n");
      output.append("Changed " + easyToHard + " easy questions to hard");
      output.append("\n");
      output.append("Changed " + hardToEasy + " hard questions to easy");
      output.append("\n");
      output.append("There are " + leftOverHard + " hard questions left");
      output.append("\n");
      output.append("There are " + leftOverEasy + " easy questions left");
      output.append("\n");
    } catch (IOException e) {
      e.printStackTrace();
    }

    System.exit(0);
  }

  private String getUserInput() {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String userInput = "";
    try {
      userInput = reader.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return userInput;
  }

  public ArrayList<Question> getListOfQuestions() {
    return listOfQuestions;
  }

  public void setListOfQuestions(ArrayList<Question> listOfQuestions) {
    this.listOfQuestions = listOfQuestions;
  }

  public Appendable getOutput() {
    return output;
  }
}
