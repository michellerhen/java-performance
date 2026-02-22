package org.example.benchmarks;

import org.example.utils.ConsoleColors;
import org.example.utils.PerformanceReporter.BenchmarkResult;
import org.example.utils.PerformanceTimer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Benchmark comparando Parallel Streams vs Sequential Streams
 */
public class ParallelBenchmark {
    private static final int SIZE = 10_000_000;
    private final List<BenchmarkResult> results = new ArrayList<>();

    public void runBenchmark() {
        System.out.println("Processando " + SIZE + " elementos com operações complexas...\n");

        // Criar lista de teste
        List<Integer> numbers = IntStream.range(0, SIZE)
                .boxed()
                .collect(Collectors.toList());

        // Sequential Stream
        PerformanceTimer timer = new PerformanceTimer();
        timer.start();
        long sumSequential = numbers.stream()
                .filter(num -> num % 2 == 0)
                .map(num -> expensiveOperation(num))
                .mapToLong(Integer::longValue)
                .sum();
        timer.stop();

        System.out.println(ConsoleColors.BLUE + "✓ Sequential Stream: " + timer.getFormattedTime() +
                          " (soma: " + sumSequential + ")" + ConsoleColors.RESET);
        results.add(new BenchmarkResult("Sequential Stream", timer.getElapsedMillis(), timer.getFormattedTime()));

        // Parallel Stream
        timer = new PerformanceTimer();
        timer.start();
        long sumParallel = numbers.parallelStream()
                .filter(num -> num % 2 == 0)
                .map(num -> expensiveOperation(num))
                .mapToLong(Integer::longValue)
                .sum();
        timer.stop();

        System.out.println(ConsoleColors.BLUE + "✓ Parallel Stream: " + timer.getFormattedTime() +
                          " (soma: " + sumParallel + ")" + ConsoleColors.RESET);
        results.add(new BenchmarkResult("Parallel Stream", timer.getElapsedMillis(), timer.getFormattedTime()));

        // Sequential com operação simples
        timer = new PerformanceTimer();
        timer.start();
        long sumSimpleSequential = numbers.stream()
                .filter(num -> num % 2 == 0)
                .mapToLong(Integer::longValue)
                .sum();
        timer.stop();

        System.out.println(ConsoleColors.BLUE + "✓ Sequential (simple operation): " + timer.getFormattedTime() +
                          " (sum: " + sumSimpleSequential + ")" + ConsoleColors.RESET);
        results.add(new BenchmarkResult("Sequential (simple)", timer.getElapsedMillis(), timer.getFormattedTime()));

        // Parallel with simple operation
        timer = new PerformanceTimer();
        timer.start();
        long sumSimpleParallel = numbers.parallelStream()
                .filter(num -> num % 2 == 0)
                .mapToLong(Integer::longValue)
                .sum();
        timer.stop();

        System.out.println(ConsoleColors.BLUE + "✓ Parallel (simple operation): " + timer.getFormattedTime() +
                          " (sum: " + sumSimpleParallel + ")" + ConsoleColors.RESET);
        results.add(new BenchmarkResult("Parallel (simple)", timer.getElapsedMillis(), timer.getFormattedTime()));

        System.out.println(ConsoleColors.YELLOW + "\n💡 Tip: Parallel streams are more efficient for complex operations!" + ConsoleColors.RESET);
    }

    /**
     * Expensive operation to demonstrate parallelism benefits
     */
    private int expensiveOperation(int num) {
        double result = num;
        for (int i = 0; i < 10; i++) {
            result = Math.sqrt(result + 1);
        }
        return (int) result;
    }

    public List<BenchmarkResult> getResults() {
        return results;
    }
}

