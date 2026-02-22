package org.example.utils;

import java.util.*;

/**
 * Class for generating performance reports
 */
public class PerformanceReporter {
    private final Map<String, List<BenchmarkResult>> results = new LinkedHashMap<>();

    public void addResult(String category, List<BenchmarkResult> categoryResults) {
        results.put(category, categoryResults);
    }

    public void printSummary() {
        System.out.println();

        for (Map.Entry<String, List<BenchmarkResult>> entry : results.entrySet()) {
            System.out.println(ConsoleColors.YELLOW_BOLD + "\n📊 " + entry.getKey() + ConsoleColors.RESET);

            List<BenchmarkResult> sortedResults = new ArrayList<>(entry.getValue());
            sortedResults.sort(Comparator.comparingDouble(BenchmarkResult::getExecutionTimeMs));

            if (!sortedResults.isEmpty()) {
                BenchmarkResult fastest = sortedResults.get(0);

                for (BenchmarkResult result : sortedResults) {
                    String color = result == fastest ? ConsoleColors.GREEN : ConsoleColors.WHITE;
                    double speedup = result.getExecutionTimeMs() / fastest.getExecutionTimeMs();
                    String speedupInfo = result == fastest ?
                        " ⚡ FASTEST" :
                        String.format(" (%.2fx slower)", speedup);

                    System.out.printf("%s  %-30s: %10s%s%s%n",
                        color,
                        result.getName(),
                        result.getFormattedTime(),
                        speedupInfo,
                        ConsoleColors.RESET);
                }
            }
        }
    }

    public static class BenchmarkResult {
        private final String name;
        private final double executionTimeMs;
        private final String formattedTime;

        public BenchmarkResult(String name, double executionTimeMs, String formattedTime) {
            this.name = name;
            this.executionTimeMs = executionTimeMs;
            this.formattedTime = formattedTime;
        }

        public String getName() {
            return name;
        }

        public double getExecutionTimeMs() {
            return executionTimeMs;
        }

        public String getFormattedTime() {
            return formattedTime;
        }
    }
}

