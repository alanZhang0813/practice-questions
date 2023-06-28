package cs3500.pa01.processor;

import cs3500.pa01.custom.Question;
import java.util.ArrayList;

/**
 * Class to process a markdown file
 */
public class StudyGuideProcessor implements Processor {
  private ArrayList<Question> listOfQuestions = new ArrayList<>();

  /**
   * Method for creating the new string with hashtags and no brackets
   *
   * @param content Provided content of the original files
   * @return String
   */
  @Override
  public String process(String content) {
    String[] contentAsLines = content.split("\n"); //splits each line into a new item
    StringBuilder result = new StringBuilder();
    int rowNum = 0;

    for (String s : contentAsLines) {
      if (s.startsWith("#")) {
        if (rowNum != 0) {
          result.append("\n");
        }
        result.append(s);
        result.append("\n");
      }

      while (s.contains("[[") && s.contains("]]")) {
        int openBracketIndex = s.indexOf("[[") + "[[".length();
        int closeBracketIndex = s.indexOf("]]");
        if (!s.contains(":::")) {
          result.append("- ");
          result.append(s.substring(openBracketIndex, closeBracketIndex));
          result.append("\n");
        } else {
          int promptEnd = s.indexOf(":::");
          int answerStart = s.indexOf(":::") + ":::".length();
          String prompt = s.substring(openBracketIndex, promptEnd);
          String answer = s.substring(answerStart, closeBracketIndex);
          int index = listOfQuestions.size() + 1;
          listOfQuestions.add(new Question(prompt, answer, index));
        }

        s = s.substring(closeBracketIndex + 1);
      }
      rowNum++;
    }

    return result.toString();
  }

  public ArrayList<Question> getListOfQuestions() {
    return listOfQuestions;
  }

  public void setListOfQuestions(ArrayList<Question> listOfQuestions) {
    this.listOfQuestions = listOfQuestions;
  }
}