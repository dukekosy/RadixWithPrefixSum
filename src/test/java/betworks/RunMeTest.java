package betworks;

import betworks.utils.FileHandler;
import betworks.utils.NumberUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class RunMeTest {

  @BeforeEach
  void setUp() {
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  public void sort_1M_successful() {
    int streamSize = 1000000;
    RunMe runMe = new RunMe(streamSize);
    runMe.sort();

    FileHandler fileHandler = new FileHandler(System.getProperty("user.dir") + "\\sorted.txt", new int[streamSize]);
    Assert.assertTrue(NumberUtils.checkNumbersAscending(fileHandler.readFile()));
  }

  //@Test
  public void sort_10M_successful() {
    int streamSize = 10000000;
    RunMe runMe = new RunMe(streamSize);
    runMe.sort();

    FileHandler fileHandler = new FileHandler(System.getProperty("user.dir") + "\\sorted.txt", new int[streamSize]);
    Assert.assertTrue(NumberUtils.checkNumbersAscending(fileHandler.readFile()));
  }

  //@Test
  public void sort_100M_successful() {
    int streamSize = 100000000;
    RunMe runMe = new RunMe(streamSize);
    runMe.sort();

    FileHandler fileHandler = new FileHandler(System.getProperty("user.dir") + "\\sorted.txt", new int[streamSize]);
    Assert.assertTrue(NumberUtils.checkNumbersAscending(fileHandler.readFile()));
  }

  //@Test will produce and 1GB file and an OutOfMemory Error as you can't create arrays of size 1 Billion
  public void sort_1B_successful() {
    int streamSize = 1000000000;
    RunMe runMe = new RunMe(streamSize);
    runMe.sort();

    FileHandler fileHandler = new FileHandler(System.getProperty("user.dir") + "\\sorted.txt", new int[streamSize]);
    Assert.assertTrue(NumberUtils.checkNumbersAscending(fileHandler.readFile()));
  }

}