package betworks;

import betworks.utils.NumberUtils;
import java.util.Arrays;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RadixSortWithCountingSortTest {

  public static final int[] UNSORTED = new int[]{277, 806, 681, 462, 787, 163, 284, 166, 905, 518, 263, 395, 988, 307, 779, 721};
  public static final int[] SORTED = new int[]{163, 166, 263, 277, 284, 307, 395, 462, 518, 681, 721, 779, 787, 806, 905, 988};
  public static final int[] FIRST_SORT_ITERATION = new int[]{681, 721, 462, 163, 263, 284, 905, 395, 806, 166, 277, 787, 307, 518, 988, 779};
  public static final int[] TEST_PREFIX = new int[]{0, 2, 1, 2, 1, 2, 2, 3, 2, 1};
  public static final int STREAM_SIZE = 1000000;

  private RadixSortWithCountingSort radixSortWithCountingSort;

  @Before
  public void setup() {
    radixSortWithCountingSort = new RadixSortWithCountingSort(UNSORTED);
  }

  @After
  public void tearDown() {

  }

  @Test
  public void rebuildArraywithPrefixSum_successful() {
    int[] count = NumberUtils.getPrefixSums(TEST_PREFIX);
    Assert.assertArrayEquals(FIRST_SORT_ITERATION, radixSortWithCountingSort.rebuildArraywithPrefixSum(count, UNSORTED, 1));
  }

  @Test
  public void radixSort_successful() {
    Assert.assertArrayEquals(SORTED, radixSortWithCountingSort.radixSort());
  }

  @Test
  public void radixSort_testfor1M_successful() {
    int[] array = Arrays.stream(NumberUtils.getRandomIntegers(STREAM_SIZE)).toArray();
    radixSortWithCountingSort = new RadixSortWithCountingSort(array);
    Assert.assertTrue(NumberUtils.checkNumbersAscending(radixSortWithCountingSort.radixSort()));
  }

}