package betworks.utils;

import java.security.InvalidParameterException;
import java.util.Random;
import java.util.stream.IntStream;

public final class NumberUtils {

  /**
   * To generate an array of integers
   *
   * @param streamSize the size of the list of random numbers
   * @return an array of random integers between 0 and MAX_VALUE
   */
  public static int[] getRandomIntegers(final int streamSize) {
    Random r = new Random();
    IntStream instream = r.ints(streamSize, 0, Integer.MAX_VALUE);
    return instream.toArray();
  }

  /**
   * calculating the prefix sum from the counter
   *
   * @param count created by counting the ith digit of all the array elements
   * @return the prefix sum of the counters
   */
  public static int[] getPrefixSums(final int[] count) {
    if (count.length != 10) { throw new InvalidParameterException("message"); }
    for (int i = 0; i < 9; i++) {
      count[i + 1] = count[i] + count[i + 1];
    }
    return count;
  }

  /**
   * Used to check if the array is ordered in ascending order
   *
   * @param array input array to be tested
   * @return returns true if array is sorted, else return false
   */
  public static boolean checkNumbersAscending(final int[] array) {
    for (int i = 0; i < array.length - 1; i++) {
      if (array[i] > array[i + 1]) { return false; }
    }
    return true;
  }

}
