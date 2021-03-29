package betworks;

import betworks.utils.FileHandler;
import betworks.utils.NumberUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class RunMeTest {

  public static final int STREAM_SIZE = 1000000;
  public static final int STREAM_SIZE_10M = 10000000;
  public static final int STREAM_SIZE_100M = 100000000;
  public static final int STREAM_SIZE_1B = 1000000000;
  public static final String FILENAME = System.getProperty("user.dir") + "\\sorted.txt";

  @BeforeEach
  void setUp() {
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  public void sort_1M_successful() {
    RunMe runMe = new RunMe(STREAM_SIZE);
    runMe.sort();

    FileHandler fileHandler = new FileHandler(FILENAME, new int[STREAM_SIZE]);
    Assert.assertTrue(NumberUtils.checkNumbersAscending(fileHandler.readFile()));
  }

  //@Test
  public void sort_10M_successful() {
    RunMe runMe = new RunMe(STREAM_SIZE_10M);
    runMe.sort();

    FileHandler fileHandler = new FileHandler(FILENAME, new int[STREAM_SIZE_10M]);
    Assert.assertTrue(NumberUtils.checkNumbersAscending(fileHandler.readFile()));
  }

  //@Test
  public void sort_100M_successful() {
    RunMe runMe = new RunMe(STREAM_SIZE_100M);
    runMe.sort();

    FileHandler fileHandler = new FileHandler(FILENAME, new int[STREAM_SIZE_100M]);
    Assert.assertTrue(NumberUtils.checkNumbersAscending(fileHandler.readFile()));
  }

  //@Test will produce and 1GB file and an OutOfMemory Error as you can't create arrays of size 1 Billion
  public void sort_1B_successful() {
    RunMe runMe = new RunMe(STREAM_SIZE_1B);
    runMe.sort();

    FileHandler fileHandler = new FileHandler(FILENAME, new int[STREAM_SIZE_1B]);
    Assert.assertTrue(NumberUtils.checkNumbersAscending(fileHandler.readFile()));
  }

}