package cs3500.pa01.writer;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for StudyGuideWriter
 */
public class StudyGuideWriterTest {
  StudyGuideWriter sgw;

  @BeforeEach
  void setup() {
    sgw = new StudyGuideWriter("");
  }

  @Test
  void testWriteThrow() {
    try {
      sgw.write("Some content", "invalid");
    } catch (IOException e) {
      assertTrue(e.getMessage().contains("/invalid"));
    }
  }
}
