package cs3500.pa01.controller;

import cs3500.pa01.custom.Question;
import cs3500.pa01.processor.PracticeProcessor;
import cs3500.pa01.reader.PracticeReader;
import cs3500.pa01.writer.PracticeWriter;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Objects;

/**
 * This class is the controller for when the argument list is empty
 */
public class PracticeController implements Controller {
  private final InputStream inputStream;
  private final PracticeProcessor processor;

  /**
   *
   * @param processor Processor
   */
  public PracticeController(InputStream inputStream, PracticeProcessor processor) {
    this.inputStream = Objects.requireNonNull(inputStream);
    this.processor = Objects.requireNonNull(processor);
  }

  @Override
  public void run() {
    PracticeReader practiceReader = new PracticeReader(inputStream, new PrintStream(System.out));
    PracticeWriter practiceWriter = new PracticeWriter(new PrintStream(System.out));

    String consoleInput = practiceReader.read();
    String output = processor.process(consoleInput);
    ArrayList<Question> listOfQuestions = processor.getQuestions();
    practiceWriter.setListOfQuestions(listOfQuestions);

    practiceWriter.write(output, practiceReader.getFilePath());
  }
}
