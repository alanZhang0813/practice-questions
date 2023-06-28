package cs3500.pa01.custom;

/**
 * Test to represent one question
 */
public class Question {
  /**
   * Enumeration to limit the difficulty options of the fire
   */
  public enum Difficulty {
    EASY, HARD
  }

  private String prompt;
  private String answer;
  private int index;
  private Difficulty difficulty;

  /**
   * Class to represent a Question
   *
   * @param prompt the question part, ending in ?
   * @param answer the answer part
   * @param index the number of question this question is
   */
  public Question(String prompt, String answer, int index) {
    this.prompt = prompt;
    this.answer = answer;
    this.index = index;
  }

  /**
   * Custom toString for formatting
   *
   * @return String representation of a Question
   */
  public String toString() {
    String result = "";
    result += this.getPrompt().trim() + ":::";
    result += this.getAnswer().trim() + " / " + this.getDifficulty().toString() + "\n";
    return result;
  }

  public String getPrompt() {
    return prompt;
  }

  public void setPrompt(String prompt) {
    this.prompt = prompt;
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  public Difficulty getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(Difficulty difficulty) {
    this.difficulty = difficulty;
  }

  public boolean isHard() {
    return this.getDifficulty().equals(Difficulty.HARD);
  }

  public boolean isEasy() {
    return this.getDifficulty().equals(Difficulty.EASY);
  }
}
