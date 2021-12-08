package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Day01 {

  public static final String DEFAULT_LOCATION = "day01";

  public static void main(String[] args) throws IOException {
    final String filename = args.length >= 1 ? args[0] : ClassLoader.getSystemClassLoader().getResource(DEFAULT_LOCATION).getPath();
    partA(filename);
    final int slidingWindowsLength = args.length >= 2 ? Integer.parseInt(args[1]) : 3;
    partB(filename, slidingWindowsLength);

  }

  private static void partA(String filename) throws IOException {
    try (final BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(filename)))){
      long previous = Long.MAX_VALUE;
      long count = 0;
      String line;
      while ((line = bufferedReader.readLine()) != null){
       long current = Long.parseLong(line);
       if (previous < current){
         count++;
       }
       previous = current;
     }
      System.out.println(String.format("Part A: %d", count));
    }
  }

  private static void partB(final String filename, final int slidingWindowsLength) throws IOException {
    try (final BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(filename)))){
      long previousSlideWindows = 0;
      long count = 0;
      String line;
      final Queue<Long> q = new LinkedList<>();
      while ((line = bufferedReader.readLine()) != null){
        if (q.size() < slidingWindowsLength){
          final long previous = Long.parseLong(line);
          previousSlideWindows += previous;
          q.add(previous);
        } else {
          final long current = Long.parseLong(line);
          final long currentSlideWindows = previousSlideWindows - q.remove() + Long.parseLong(line);
          if (previousSlideWindows < currentSlideWindows){
            count++;
          }
          previousSlideWindows = currentSlideWindows;
          q.add(current);
        }
      }
      System.out.println(String.format("Part B: %d", count));
    }
  }
}
