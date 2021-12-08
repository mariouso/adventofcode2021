package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Day03 {

  public static final String DEFAULT_LOCATION = "day03";

  public static void main(String[] args) throws IOException {
    final String filename = args.length >= 1 ? args[0] : ClassLoader.getSystemClassLoader().getResource(DEFAULT_LOCATION).getPath();
    executeCommandFile(filename);

  }

  private static void executeCommandFile(String filename) throws IOException {
    try (final BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(filename)))) {
      final Map<Integer, Long> frequencyTableOnes = new HashMap<>();
      final Map<Integer, Long> frequencyTableZeros = new HashMap<>();
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        char[] medition = line.toCharArray();
        for (int pos = medition.length - 1; pos >= 0; pos--) {
          final int frequencyKey = medition.length - (pos + 1);
          if (medition[pos] == '1') {
            final Long currentAccumulatedValue = frequencyTableOnes.getOrDefault(frequencyKey, 0L);
            frequencyTableOnes.put(frequencyKey, currentAccumulatedValue + 1);
          } else {
            final Long currentAccumulatedValue = frequencyTableZeros.getOrDefault(frequencyKey, 0L);
            frequencyTableZeros.put(frequencyKey, currentAccumulatedValue + 1);
          }
        }
      }
      final long gamma = calcGammaRate(frequencyTableOnes, frequencyTableZeros);
      final long epsilon = calcEpsilonRate(frequencyTableOnes, frequencyTableZeros);
      System.out.println(String.format("Result : %d", gamma * epsilon));
    }
  }

  private static long calcGammaRate(Map<Integer, Long> frequencyTableOnes, Map<Integer, Long> frequencyTableZeros) {
    long gammaRate = 0;
    for (int pos = 0; pos < frequencyTableOnes.size(); pos++) {
      int mult = 0;
      if (frequencyTableOnes.getOrDefault(pos, 0L) > frequencyTableZeros.getOrDefault(pos, 0L)) {
        mult = 1;
      }
      gammaRate += Math.pow(2 , pos) * mult;
    }
    return gammaRate;
  }

  private static long calcEpsilonRate(Map<Integer, Long> frequencyTableOnes, Map<Integer, Long> frequencyTableZeros) {
    long epsilonRate = 0;
    for (int pos = 0; pos < frequencyTableOnes.size(); pos++) {
      int mult = 0;
      if (frequencyTableOnes.getOrDefault(pos, 0L) < frequencyTableZeros.getOrDefault(pos, 0L)) {
        mult = 1;
      }
      epsilonRate += Math.pow(2 , pos) * mult;
    }
    return epsilonRate;
  }
}
