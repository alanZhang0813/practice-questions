package cs3500.pa01.reader;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa01.custom.MyFile;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for StudyGuideReader
 */
public class StudyGuideReaderTest {
  ArrayList<MyFile> listOfFiles;
  StudyGuideReader sgr;

  @BeforeEach
  void setup() {
    listOfFiles = new ArrayList<>();
    sgr = new StudyGuideReader(listOfFiles);
  }

  @Test
  void testGetListOfFiles() {
    MyFile file1 = new MyFile();
    MyFile file2 = new MyFile();
    listOfFiles.add(file1);
    listOfFiles.add(file2);
    sgr.setListOfFiles(listOfFiles);
    assertEquals(2, sgr.getListOfFiles().size());
  }
}
