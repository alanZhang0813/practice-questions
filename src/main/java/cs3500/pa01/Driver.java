package cs3500.pa01;

import cs3500.pa01.controller.PracticeController;
import cs3500.pa01.controller.StudyGuideController;
import cs3500.pa01.processor.PracticeProcessor;
import java.io.IOException;

/**
 * This is the main driver of this project.
 */
class Driver {
  public static void main(String[] args) {
    if (args.length == 3) {
      String origin = args[0];
      String orderingFlag = args[1];
      String destination = args[2];

      StudyGuideController controller = new StudyGuideController(origin, orderingFlag, destination);
      try {
        controller.run();
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else if (args.length == 0) {
      System.out.println("Welcome to a new study session!");
      PracticeProcessor processor = new PracticeProcessor();
      PracticeController practiceController = new PracticeController(System.in, processor);
      practiceController.run();
    } else {
      throw new IllegalArgumentException("Invalid number of args");
    }
  }
}
