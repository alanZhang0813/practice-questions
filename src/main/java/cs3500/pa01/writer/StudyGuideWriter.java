package cs3500.pa01.writer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Test class for StudyGuide
 */
public class StudyGuideWriter implements Writer {
  private final String destination;

  public StudyGuideWriter(String destination) {
    this.destination = destination;
  }

  @Override
  public void write(String content, String location) throws IOException {
    //block of code for outputting the result in the designated folder
    File dest = new File(destination);
    File result = new File(dest, location);
    FileWriter fw = new FileWriter(result);
    fw.write(content);
    fw.close();

  }
}
