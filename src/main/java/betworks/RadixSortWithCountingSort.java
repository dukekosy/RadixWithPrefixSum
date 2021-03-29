package betworks;

import betworks.utils.NumberUtils;

public final class RadixSortWithCountingSort {

  private int[] unsorted;

  /**
   * Constructor to sort integers
   *
   * @param unsorted an unsorted array of integers
   */
  RadixSortWithCountingSort(final int[] unsorted) {
    this.unsorted = unsorted;
  }

  /**
   * Running radix sort with count sort using a prefix sum
   *
   * @return return the sorted integers
   */
  int[] radixSort() {

    for (int i = 0; i < String.valueOf(Integer.MAX_VALUE).length(); i++) {
      int[] count = new int[10];
      int power = (int) Math.pow(10, i);
      for (int j = 0; j < unsorted.length; j++) {
        count[(unsorted[j] / (power)) % 10]++;
      }
      count = NumberUtils.getPrefixSums(count);
      unsorted = rebuildArraywithPrefixSum(count, unsorted, power);
    }
    return unsorted;
  }

  /**
   * Reordering the elements of the unsorted array using the prefix sum
   *
   * @param prefixSum The current prefix sum
   * @param array The current unsorted array
   * @param power Used to shift a number x steps to the right. the The pointer to the get the modulus to the base of 10
   * @return the reorder array using the prefix sum
   */
  int[] rebuildArraywithPrefixSum(final int[] prefixSum, final int array[], final int power) {
    int[] result = new int[array.length];
    for (int i = array.length - 1; i >= 0; i--) {
      int index = --prefixSum[(array[i] / (power)) % 10];
      result[index] = array[i];
    }
    return result;//chk that nothing is zero
  }

}
