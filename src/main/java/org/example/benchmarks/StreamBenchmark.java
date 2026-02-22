package org.example.benchmarks;

import org.example.utils.ConsoleColors;
import org.example.utils.PerformanceReporter.BenchmarkResult;
import org.example.utils.PerformanceTimer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Benchmark comparando Stream API vs loops tradicionais
 */
public class StreamBenchmark {
    private static final int SIZE = 1_000_000;
    private final List<BenchmarkResult> results = new ArrayList<>();

    public void runBenchmark() {
        System.out.println("Processando " + SIZE + " elementos...\n");

        // Criar lista de teste
        List<Integer> numbers = IntStream.range(0, SIZE)
                .boxed()
                .collect(Collectors.toList());

        // Traditional for loop
        PerformanceTimer timer = new PerformanceTimer();
        timer.start();
        List<Integer> resultFor = new ArrayList<>();
        for (Integer num : numbers) {
            if (num % 2 == 0) {
                resultFor.add(num * 2);
            }
        }
        timer.stop();

        System.out.println(ConsoleColors.BLUE + "✓ For Loop: " + timer.getFormattedTime() +
                          " (" + resultFor.size() + " elements)" + ConsoleColors.RESET);
        results.add(new BenchmarkResult("For Loop", timer.getElapsedMillis(), timer.getFormattedTime()));

        // Stream API
        timer = new PerformanceTimer();
        timer.start();
        List<Integer> resultStream = numbers.stream()
                .filter(num -> num % 2 == 0)
                .map(num -> num * 2)
                .collect(Collectors.toList());
        timer.stop();

        System.out.println(ConsoleColors.BLUE + "✓ Stream API: " + timer.getFormattedTime() +
                          " (" + resultStream.size() + " elements)" + ConsoleColors.RESET);
        results.add(new BenchmarkResult("Stream API", timer.getElapsedMillis(), timer.getFormattedTime()));

        // Iterator
        timer = new PerformanceTimer();
        timer.start();
        List<Integer> resultIterator = new ArrayList<>();
        var iterator = numbers.iterator();
        while (iterator.hasNext()) {
            Integer num = iterator.next();
            if (num % 2 == 0) {
                resultIterator.add(num * 2);
            }
        }
        timer.stop();

        System.out.println(ConsoleColors.BLUE + "✓ Iterator: " + timer.getFormattedTime() +
                          " (" + resultIterator.size() + " elements)" + ConsoleColors.RESET);
        results.add(new BenchmarkResult("Iterator", timer.getElapsedMillis(), timer.getFormattedTime()));

        // For index
        timer = new PerformanceTimer();
        timer.start();
        List<Integer> resultForIndex = new ArrayList<>();
        for (int i = 0; i < numbers.size(); i++) {
            Integer num = numbers.get(i);
            if (num % 2 == 0) {
                resultForIndex.add(num * 2);
            }
        }
        timer.stop();

        System.out.println(ConsoleColors.BLUE + "✓ For Index: " + timer.getFormattedTime() +
                          " (" + resultForIndex.size() + " elements)" + ConsoleColors.RESET);
        results.add(new BenchmarkResult("For Index", timer.getElapsedMillis(), timer.getFormattedTime()));
    }

    public List<BenchmarkResult> getResults() {
        return results;
    }
}

