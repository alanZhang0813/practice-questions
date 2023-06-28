package cs3500.pa01.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import cs3500.pa01.custom.MyFile;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for StudyGuideController
 */
public class StudyGuideControllerTest {
  StudyGuideController sgc;
  ArrayList<MyFile> listOfFiles;
  MyFile mf1;
  MyFile mf2;
  MyFile mf3;
  String orderingFlag;

  @BeforeEach
  void setup() {
    orderingFlag = "";
    sgc = new StudyGuideController("src/test/resources/{inputs}", orderingFlag,
        "src/test/resources/{inputs}");
    listOfFiles = new ArrayList<>();
    mf1 = new MyFile();
    mf2 = new MyFile();
    mf3 = new MyFile();
  }

  @Test
  void testSortName() {
    mf1.setFile(Paths.get("abc"));
    mf2.setFile(Paths.get("bcd"));
    mf3.setFile(Paths.get("cde"));
    listOfFiles.add(mf1);
    listOfFiles.add(mf2);
    listOfFiles.add(mf3);

    orderingFlag = "filename";
    assertEquals("abc",
        listOfFiles.get(0).getFile().toString());
    try {
      sgc.sort(listOfFiles, "filename");
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertEquals("abc",
        listOfFiles.get(0).getFile().toString());
  }

  @Test
  void testSortCreated() {
    final CharSequence text1 = "2007-12-03T10:15:30.00Z";
    final CharSequence text2 = "2008-12-03T10:15:30.00Z";
    final CharSequence text3 = "2009-12-03T10:15:30.00Z";
    mf1.setDateCreated(FileTime.from(Instant.parse(text1)));
    mf2.setDateCreated(FileTime.from(Instant.parse(text2)));
    mf3.setDateCreated(FileTime.from(Instant.parse(text3)));
    listOfFiles.add(mf1);
    listOfFiles.add(mf2);
    listOfFiles.add(mf3);

    orderingFlag = "created";
    assertEquals("2007-12-03T10:15:30Z",
        listOfFiles.get(0).getDateCreated().toString());
    try {
      sgc.sort(listOfFiles, "created");
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertEquals("2007-12-03T10:15:30Z",
        listOfFiles.get(0).getDateCreated().toString());
  }

  @Test
  void testSortModified() {
    final CharSequence text1 = "2007-12-03T10:15:30.00Z";
    final CharSequence text2 = "2008-12-03T10:15:30.00Z";
    final CharSequence text3 = "2009-12-03T10:15:30.00Z";
    mf1.setDateModified(FileTime.from(Instant.parse(text1)));
    mf2.setDateModified(FileTime.from(Instant.parse(text2)));
    mf3.setDateModified(FileTime.from(Instant.parse(text3)));
    listOfFiles.add(mf1);
    listOfFiles.add(mf2);
    listOfFiles.add(mf3);

    orderingFlag = "modified";
    assertEquals("2007-12-03T10:15:30Z",
        listOfFiles.get(0).getDateModified().toString());
    try {
      sgc.sort(listOfFiles, "modified");
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertEquals("2007-12-03T10:15:30Z",
        listOfFiles.get(0).getDateModified().toString());
  }

  @Test
  void testSortThrow() {
    mf1.setFile(Paths.get("abc"));
    mf2.setFile(Paths.get("bcd"));
    mf3.setFile(Paths.get("cde"));
    listOfFiles.add(mf1);
    listOfFiles.add(mf2);
    listOfFiles.add(mf3);

    sgc = new StudyGuideController("src/test/resources/{inputs}", "else",
        "src/test/resources/{inputs}");
    assertEquals("abc",
        listOfFiles.get(0).getFile().toString());
    try {
      sgc.sort(listOfFiles, "else");
      fail();
    } catch (IOException e) {
      assertEquals("Ordering flag string must be: filename, created, or modified",
          e.getMessage());
    }
  }
}
