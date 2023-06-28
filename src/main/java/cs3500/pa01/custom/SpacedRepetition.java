package cs3500.pa01.custom;

import java.util.ArrayList;

/**
 * Class to represent a SpacedRepetition file's contents
 */
public class SpacedRepetition {
  private final ArrayList<Question> listOfQuestions;

  public SpacedRepetition(ArrayList<Question> listOfQuestions) {
    this.listOfQuestions = listOfQuestions;
  }

  @Override
  public String toString() {
    String result = "";
    for (Question q : listOfQuestions) {
      result = result.concat(q.toString());
    }

    return result;
  }
}