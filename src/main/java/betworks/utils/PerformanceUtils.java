package betworks.utils;

public class PerformanceUtils {

  public static long start() {
    return System.nanoTime();
  }

  public static long stop(long startTime){
    long endTime = System.nanoTime();
    return (endTime - startTime) / 1000000;
  }

}
