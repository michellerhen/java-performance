package org.example.benchmarks;

import org.example.utils.ConsoleColors;
import org.example.utils.PerformanceReporter.BenchmarkResult;
import org.example.utils.PerformanceTimer;

import java.util.ArrayList;
import java.util.List;

/**
 * Benchmark comparing String concatenation vs StringBuilder
 */
public class StringBenchmark {
    private static final int ITERATIONS = 50_000;
    private final List<BenchmarkResult> results = new ArrayList<>();

    public void runBenchmark() {
        System.out.println("Concatenating strings " + ITERATIONS + " times...\n");

        // String concatenation (+=)
        PerformanceTimer timer = new PerformanceTimer();
        timer.start();
        String result = "";
        for (int i = 0; i < ITERATIONS; i++) {
            result += "a";
        }
        timer.stop();

        System.out.println(ConsoleColors.BLUE + "✓ String com +=: " + timer.getFormattedTime() + ConsoleColors.RESET);
        results.add(new BenchmarkResult("String (+=)", timer.getElapsedMillis(), timer.getFormattedTime()));

        // StringBuilder
        timer = new PerformanceTimer();
        timer.start();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ITERATIONS; i++) {
            sb.append("a");
        }
        String sbResult = sb.toString();
        timer.stop();

        System.out.println(ConsoleColors.BLUE + "✓ StringBuilder: " + timer.getFormattedTime() + ConsoleColors.RESET);
        results.add(new BenchmarkResult("StringBuilder", timer.getElapsedMillis(), timer.getFormattedTime()));

        // StringBuffer (thread-safe)
        timer = new PerformanceTimer();
        timer.start();
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < ITERATIONS; i++) {
            sbf.append("a");
        }
        String sbfResult = sbf.toString();
        timer.stop();

        System.out.println(ConsoleColors.BLUE + "✓ StringBuffer: " + timer.getFormattedTime() + ConsoleColors.RESET);
        results.add(new BenchmarkResult("StringBuffer", timer.getElapsedMillis(), timer.getFormattedTime()));

        // String.concat()
        timer = new PerformanceTimer();
        timer.start();
        String concatResult = "";
        for (int i = 0; i < ITERATIONS; i++) {
            concatResult = concatResult.concat("a");
        }
        timer.stop();

        System.out.println(ConsoleColors.BLUE + "✓ String.concat(): " + timer.getFormattedTime() + ConsoleColors.RESET);
        results.add(new BenchmarkResult("String.concat()", timer.getElapsedMillis(), timer.getFormattedTime()));
    }

    public List<BenchmarkResult> getResults() {
        return results;
    }
}

