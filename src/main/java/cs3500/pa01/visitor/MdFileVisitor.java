package cs3500.pa01.visitor;

import cs3500.pa01.custom.MyFile;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

/**
 * Class that visits all the files in the path
 */
public class MdFileVisitor implements FileVisitor<Path> {
  final ArrayList<MyFile> listOfFiles = new ArrayList<>();
  private String message = "";

  public ArrayList<MyFile> getList() {
    return listOfFiles;
  }

  public String getMessage() {
    return message;
  }

  @Override
  public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
    return FileVisitResult.CONTINUE;
  }

  @Override
  public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
    String fileName = file.getFileName().toString();
    if (fileName.endsWith(".md")) {
      MyFile mf = new MyFile();
      mf.setFile(file);
      mf.setDateCreated(attrs.creationTime());
      mf.setDateModified(attrs.lastModifiedTime());
      this.listOfFiles.add(mf);
    }

    return FileVisitResult.CONTINUE;
  }

  @Override
  public FileVisitResult visitFileFailed(Path file, IOException exc) {
    exceptionHandler();
    return FileVisitResult.CONTINUE;
  }

  @Override
  public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
    return FileVisitResult.CONTINUE;
  }

  void exceptionHandler() {
    message = "Search failed";
  }
}