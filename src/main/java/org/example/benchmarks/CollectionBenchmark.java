package org.example.benchmarks;

import org.example.utils.ConsoleColors;
import org.example.utils.PerformanceReporter.BenchmarkResult;
import org.example.utils.PerformanceTimer;

import java.util.*;

/**
 * Benchmark comparing performance of different Collections
 */
public class CollectionBenchmark {
    private static final int OPERATIONS = 100_000;
    private final List<BenchmarkResult> results = new ArrayList<>();

    public void runBenchmark() {
        System.out.println("Testing " + OPERATIONS + " insertion and search operations...\n");

        // ArrayList
        PerformanceTimer timer = new PerformanceTimer();
        timer.start();
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < OPERATIONS; i++) {
            arrayList.add(i);
        }
        // Buscar alguns elementos
        for (int i = 0; i < 1000; i++) {
            arrayList.contains(i * 50);
        }
        timer.stop();

        System.out.println(ConsoleColors.BLUE + "✓ ArrayList: " + timer.getFormattedTime() + ConsoleColors.RESET);
        results.add(new BenchmarkResult("ArrayList", timer.getElapsedMillis(), timer.getFormattedTime()));

        // LinkedList
        timer = new PerformanceTimer();
        timer.start();
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < OPERATIONS; i++) {
            linkedList.add(i);
        }
        // Buscar alguns elementos
        for (int i = 0; i < 1000; i++) {
            linkedList.contains(i * 50);
        }
        timer.stop();

        System.out.println(ConsoleColors.BLUE + "✓ LinkedList: " + timer.getFormattedTime() + ConsoleColors.RESET);
        results.add(new BenchmarkResult("LinkedList", timer.getElapsedMillis(), timer.getFormattedTime()));

        // HashSet
        timer = new PerformanceTimer();
        timer.start();
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < OPERATIONS; i++) {
            hashSet.add(i);
        }
        // Buscar alguns elementos
        for (int i = 0; i < 1000; i++) {
            hashSet.contains(i * 50);
        }
        timer.stop();

        System.out.println(ConsoleColors.BLUE + "✓ HashSet: " + timer.getFormattedTime() + ConsoleColors.RESET);
        results.add(new BenchmarkResult("HashSet", timer.getElapsedMillis(), timer.getFormattedTime()));

        // TreeSet
        timer = new PerformanceTimer();
        timer.start();
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < OPERATIONS; i++) {
            treeSet.add(i);
        }
        // Buscar alguns elementos
        for (int i = 0; i < 1000; i++) {
            treeSet.contains(i * 50);
        }
        timer.stop();

        System.out.println(ConsoleColors.BLUE + "✓ TreeSet: " + timer.getFormattedTime() + ConsoleColors.RESET);
        results.add(new BenchmarkResult("TreeSet", timer.getElapsedMillis(), timer.getFormattedTime()));
    }

    public List<BenchmarkResult> getResults() {
        return results;
    }
}

