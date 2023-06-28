package cs3500.pa01.reader;

import cs3500.pa01.custom.MyFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Class that reads the files
 */
public class StudyGuideReader implements Reader {
  private ArrayList<MyFile> listOfFiles;

  public StudyGuideReader(ArrayList<MyFile> listOfFiles) {
    this.listOfFiles = listOfFiles;
  }

  @Override
  public String read() {
    StringBuilder sb = new StringBuilder();
    for (MyFile mf : listOfFiles) {
      Path path = Path.of(mf.getFile().toString());
      try {
        String contents = Files.readString(path);
        for (char c : contents.toCharArray()) {
          sb.append(c);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }

      sb.append("\n");
      sb.append("\n");
    }

    return sb.toString();
  }

  public ArrayList<MyFile> getListOfFiles() {
    return listOfFiles;
  }

  public void setListOfFiles(ArrayList<MyFile> listOfFiles) {
    this.listOfFiles = listOfFiles;
  }
}

