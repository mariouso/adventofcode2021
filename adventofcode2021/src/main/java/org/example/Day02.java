package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Day02 {

  public static final String DEFAULT_LOCATION = "day02";

  interface Submarine {
    void forward(long amount);
    void down(long amount);
    void up(long amount);
    long getResult();
  }

  static class SubmarinePartA implements Submarine {
    private long x = 0;
    private long y = 0;

    public void forward(final long amount) {
      x += amount;
    }

    public void down(final long amount) {
      y += amount;
    }

    public void up(final long amount) {
      y -= amount;
    }

    public long getResult() {
      return x * y;
    }
  }

  static class SubmarinePartB implements Submarine{
    private long x = 0;
    private long y = 0;
    private long aim = 0;

    public void forward(final long amount) {
      x += amount;
      y += (aim  * amount);
    }

    public void down(final long amount) {
      aim += amount;
    }

    public void up(final long amount) {
      aim -= amount;
    }

    public long getResult() {
      return x * y;
    }
  }

  public static void main(String[] args) throws IOException {
    final String filename = args.length >= 1 ? args[0] : ClassLoader.getSystemClassLoader().getResource(DEFAULT_LOCATION).getPath();
    executeCommandFile(filename, new SubmarinePartA());
    executeCommandFile(filename, new SubmarinePartB());

  }

  private static void executeCommandFile(String filename, final Submarine submarine) throws IOException {
    try (final BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(filename)))) {
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        final String[] split = line.split(" ");
        final long amount = Long.parseLong(split[1]);
        switch (split[0]) {
          case "forward":
            submarine.forward(amount);
            break;
          case "down":
            submarine.down(amount);
            break;
          case "up":
            submarine.up(amount);
            break;
          default:
            break;
        }
      }
      System.out.println(String.format("Result : %d", submarine.getResult()));
    }
  }
}
