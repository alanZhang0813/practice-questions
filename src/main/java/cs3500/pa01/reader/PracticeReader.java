package cs3500.pa01.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Class for reading the console
 */
public class PracticeReader implements Reader {
  private String filePath;
  private final InputStream inputStream;
  private final Appendable outputStream;

  public PracticeReader(InputStream inputStream, Appendable outputStream) {
    this.inputStream = inputStream;
    this.outputStream = outputStream;
  }

  @Override
  public String read() {
    String contents = "";
    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
    String numQuestions;

    try {
      filePath = this.enterFilePath(reader);
      numQuestions = this.enterNumQuestions(reader);
    } catch (IOException e) {
      return e.getMessage();
    }

    contents += filePath;
    contents += "?*?";
    contents += numQuestions;

    return contents;
  }

  private String enterFilePath(BufferedReader reader) throws IOException {
    String filePath;

    filePath = reader.readLine();
    Files.readString(Path.of(filePath));

    if (isEscapeSequence(filePath)) {
      return "-1";
    } else {
      outputStream.append("Enter a valid file path to an .sr file:\n");
    }

    return filePath;
  }

  private String enterNumQuestions(BufferedReader reader) throws IOException {
    String numQuestions = reader.readLine();

    if (isEscapeSequence(filePath)) {
      return "-1";
    } else {
      outputStream.append("How many questions would you like to practice?\n");
    }

    return numQuestions;
  }

  private boolean isEscapeSequence(String content) {
    return content.equals("-1");
  }

  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }
}