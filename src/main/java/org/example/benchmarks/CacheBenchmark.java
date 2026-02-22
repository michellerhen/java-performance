package org.example.benchmarks;

import org.example.utils.ConsoleColors;
import org.example.utils.PerformanceReporter.BenchmarkResult;
import org.example.utils.PerformanceTimer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Benchmark demonstrating the impact of using cache
 */
public class CacheBenchmark {
    private static final int CALCULATIONS = 100_000;
    private final List<BenchmarkResult> results = new ArrayList<>();

    public void runBenchmark() {
        System.out.println("Calculating Fibonacci " + CALCULATIONS + " times...\n");

        // Without cache - direct calculation
        PerformanceTimer timer = new PerformanceTimer();
        timer.start();
        long sumWithoutCache = 0;
        for (int i = 0; i < CALCULATIONS; i++) {
            sumWithoutCache += fibonacciWithoutCache(20);
        }
        timer.stop();

        System.out.println(ConsoleColors.BLUE + "✓ Without Cache: " + timer.getFormattedTime() +
                          " (result: " + sumWithoutCache + ")" + ConsoleColors.RESET);
        results.add(new BenchmarkResult("Without Cache", timer.getElapsedMillis(), timer.getFormattedTime()));

        // With in-memory cache
        timer = new PerformanceTimer();
        timer.start();
        Map<Integer, Long> cache = new HashMap<>();
        long sumWithCache = 0;
        for (int i = 0; i < CALCULATIONS; i++) {
            sumWithCache += fibonacciWithCache(20, cache);
        }
        timer.stop();

        System.out.println(ConsoleColors.BLUE + "✓ With Cache: " + timer.getFormattedTime() +
                          " (result: " + sumWithCache + ")" + ConsoleColors.RESET);
        results.add(new BenchmarkResult("With Cache", timer.getElapsedMillis(), timer.getFormattedTime()));

        // Array lookup vs calculation
        timer = new PerformanceTimer();
        timer.start();
        long[] precomputed = new long[21];
        for (int i = 0; i <= 20; i++) {
            precomputed[i] = fibonacciWithoutCache(i);
        }
        long sumPrecomputed = 0;
        for (int i = 0; i < CALCULATIONS; i++) {
            sumPrecomputed += precomputed[20];
        }
        timer.stop();

        System.out.println(ConsoleColors.BLUE + "✓ Pre-computed (Array): " + timer.getFormattedTime() +
                          " (result: " + sumPrecomputed + ")" + ConsoleColors.RESET);
        results.add(new BenchmarkResult("Pre-computed", timer.getElapsedMillis(), timer.getFormattedTime()));

        System.out.println(ConsoleColors.YELLOW + "\n💡 Tip: Cache can dramatically improve performance!" + ConsoleColors.RESET);
    }

    /**
     * Recursive Fibonacci without cache (inefficient)
     */
    private long fibonacciWithoutCache(int n) {
        if (n <= 1) return n;
        return fibonacciWithoutCache(n - 1) + fibonacciWithoutCache(n - 2);
    }

    /**
     * Fibonacci with memoization (cache)
     */
    private long fibonacciWithCache(int n, Map<Integer, Long> cache) {
        if (n <= 1) return n;

        if (cache.containsKey(n)) {
            return cache.get(n);
        }

        long result = fibonacciWithCache(n - 1, cache) + fibonacciWithCache(n - 2, cache);
        cache.put(n, result);
        return result;
    }

    public List<BenchmarkResult> getResults() {
        return results;
    }
}

