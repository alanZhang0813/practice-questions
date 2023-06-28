package cs3500.pa01.writer;

import java.io.IOException;

/**
 * Taking the final result,
 */
public interface Writer {
  void write(String content, String location) throws IOException;
}
