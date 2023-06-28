package cs3500.pa01.controller;

import cs3500.pa01.controller.comparators.CreateComparator;
import cs3500.pa01.controller.comparators.ModifyComparator;
import cs3500.pa01.controller.comparators.NameComparator;
import cs3500.pa01.custom.MyFile;
import cs3500.pa01.custom.Question;
import cs3500.pa01.custom.SpacedRepetition;
import cs3500.pa01.processor.StudyGuideProcessor;
import cs3500.pa01.reader.StudyGuideReader;
import cs3500.pa01.visitor.MdFileVisitor;
import cs3500.pa01.writer.StudyGuideWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 */
public class StudyGuideController implements Controller {
  private final String origin;
  private final String orderingFlag;
  private final String destination;

  /**
   *
   */
  public StudyGuideController(String origin, String orderingFlag, String destination) {
    this.origin = origin;
    this.orderingFlag = orderingFlag;
    this.destination = destination;
  }

  @Override
  public void run() throws IOException {
    ArrayList<MyFile> listOfFiles = new ArrayList<>();
    try {
      listOfFiles = this.produceList();
      this.sort(listOfFiles, orderingFlag);
    } catch (IOException e) {
      e.printStackTrace();
    }

    StudyGuideReader reader = new StudyGuideReader(listOfFiles);
    String filesContent = reader.read();

    StudyGuideProcessor processor = new StudyGuideProcessor();
    String studyGuideContent = processor.process(filesContent);

    ArrayList<Question> listOfQuestions = processor.getListOfQuestions();
    for (Question q : listOfQuestions) {
      q.setDifficulty(Question.Difficulty.HARD);
    }
    SpacedRepetition questionsSr = new SpacedRepetition(listOfQuestions);
    String questionsContent = questionsSr.toString();

    StudyGuideWriter writer = new StudyGuideWriter(destination);
    writer.write(studyGuideContent, "Study Guide.md");
    writer.write(questionsContent, "Questions.sr");
  }

  /**
   *
   * @return A List of all the MyFiles needed
   * @throws IOException if the file is invalid
   */
  public ArrayList<MyFile> produceList() throws IOException {
    MdFileVisitor mdfv = new MdFileVisitor();
    Path p = Path.of(origin);
    Files.walkFileTree(p, mdfv);

    return mdfv.getList();
  }

  /**
   *
   * @param orderingFlag String
   * @throws IOException If the provided string does not fit
   */
  public void sort(ArrayList<MyFile> listOfFiles, String orderingFlag) throws IOException {
    if (orderingFlag.equals("filename")) {
      Collections.sort(listOfFiles, new NameComparator());
    } else if (orderingFlag.equals("created")) {
      Collections.sort(listOfFiles, new CreateComparator());
    } else if (orderingFlag.equals("modified")) {
      Collections.sort(listOfFiles, new ModifyComparator());
    } else {
      throw new IOException(
          "Ordering flag string must be: filename, created, or modified");
    }
  }
}
