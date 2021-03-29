package betworks;

import betworks.utils.FileHandler;
import betworks.utils.NumberUtils;
import java.io.IOException;
/*

Need to test for even more that 10 million, assuming that's the required scale.
I'm assuming all positive integers.

Looking at research on parallel sorting algorithem, it slows down after around 4 or 8 processors with 16 being too slow.
Refer to this research paper
Performance Evaluation of Parallel Sorting Algorithms on
IMAN1 Supercomputer, International journal of advance science and Technology
https://www.researchgate.net/publication/310529308_Performance_Evaluation_of_Parallel_Sorting_Algorithms_on_IMAN1_Supercomputer

It takes 280 bytes for an array of size 10.
So we're looking at a file size of around, 28Mb.
I'm not worried about memory here but replicating that data in a for loop might end up in an issue for scale.
Note as arrays are used instead of linked lists, 1 billion numbers is a tested limitation.

We don't want to loop through the whole array multiple times as it's very large.
we want to split it up using something like merge sort or we want to iteration only a few times.

Looking at bubble sort, merge sort, quick sort and a number of other methods I'm seeing more than x*x or nlog(n) time complexity.

My research has found a mix of radix sort with count sort using a prefix sum will help achieve linear time O(n).

Did make sorting generic to handle double etc as this would affect performance for the type checks.
https://stackoverflow.com/questions/6224368/does-using-generics-in-java-affect-performance#:~:text=Yes%2C%20it%20does%20affect%20performance,the%20compiler%20creates%20type%2Dcasts.&text=In%20Java%20generics%20only%20ensure%20type%20safety%20at%20compile%20time.

Looking at techniques for reading a file. The java 8 technique using File api is slower than the good old BufferdReader.readline.
Reasearch is found in this link.
https://stackoverflow.com/questions/19486077/java-fastest-way-to-read-through-text-file-with-2-million-lines
This was solved in Java 9 but we're restricted to Java 8.

*/

public final class RunMe {

  private final int streamSize;
  private FileHandler fileHandler;

  RunMe(final int streamSize) {
    this.streamSize = streamSize;
  }

  public static void main(String[] args) {
    RunMe runMe = new RunMe(1000000);
    runMe.sort();
  }

  void sort() {

    fileHandler = new FileHandler(System.getProperty("user.dir") + "\\unsorted.txt", NumberUtils.getRandomIntegers(streamSize));

    long startTime = System.nanoTime();
    try {
      fileHandler.writeFileBuffered();
    } catch (IOException e) {
      e.printStackTrace();
    }
    long endTime = System.nanoTime();
    long durationMS = (endTime - startTime) / 1000000;
    System.out.println("write unsorted " + durationMS);

    startTime = System.nanoTime();
    int[] array = fileHandler.readFile();
    endTime = System.nanoTime();
    durationMS = (endTime - startTime) / 1000000;
    System.out.println("read " + durationMS);

    startTime = System.nanoTime();
    array = new RadixSortWithCountingSort(array).radixSort();
    System.out.println("correctly sorted? " + NumberUtils.checkNumbersAscending(array));
    endTime = System.nanoTime();
    durationMS = (endTime - startTime) / 1000000;
    System.out.println("sort " + durationMS);

    FileHandler fileHandler2 = new FileHandler(System.getProperty("user.dir") + "\\sorted.txt", array);
    startTime = System.nanoTime();
    try {
      fileHandler2.writeFileBuffered();
    } catch (IOException e) {
      e.printStackTrace();
    }
    endTime = System.nanoTime();
    durationMS = (endTime - startTime) / 1000000;
    System.out.println("write sorted " + durationMS);
  }

}
