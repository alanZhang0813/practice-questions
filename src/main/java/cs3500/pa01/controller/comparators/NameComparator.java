package cs3500.pa01.controller.comparators;

import cs3500.pa01.custom.MyFile;
import java.util.Comparator;

/**
 * Class for testing the NameComparator class
 */
public class NameComparator implements Comparator<MyFile> {
  @Override
  public int compare(MyFile f1, MyFile f2) {
    return f1.getFile().compareTo(f2.getFile());
  }
}
