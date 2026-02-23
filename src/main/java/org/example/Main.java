package org.example;

import org.example.benchmarks.*;
import org.example.utils.ConsoleColors;
import org.example.utils.PerformanceReporter;

/**
 * Java Performance Demonstration Project
 * Demonstrates different aspects of Java performance through practical benchmarks
 */
public class Main {
    public static void main(String[] args) {
        printHeader();

        PerformanceReporter reporter = new PerformanceReporter();

        // 1. Collections Comparison
        System.out.println(ConsoleColors.CYAN + "\n╔════════════════════════════════════════════╗");
        System.out.println("║   1. COLLECTIONS PERFORMANCE               ║");
        System.out.println("╚════════════════════════════════════════════╝" + ConsoleColors.RESET);
        CollectionBenchmark collectionBenchmark = new CollectionBenchmark();
        collectionBenchmark.runBenchmark();
        reporter.addResult("Collections", collectionBenchmark.getResults());

        // 2. String vs StringBuilder
        System.out.println(ConsoleColors.CYAN + "\n╔════════════════════════════════════════════╗");
        System.out.println("║   2. STRING VS STRINGBUILDER               ║");
        System.out.println("╚════════════════════════════════════════════╝" + ConsoleColors.RESET);
        StringBenchmark stringBenchmark = new StringBenchmark();
        stringBenchmark.runBenchmark();
        reporter.addResult("String Operations", stringBenchmark.getResults());

        // 3. Stream vs Loop
        System.out.println(ConsoleColors.CYAN + "\n╔════════════════════════════════════════════╗");
        System.out.println("║   3. STREAM API VS TRADITIONAL LOOPS       ║");
        System.out.println("╚════════════════════════════════════════════╝" + ConsoleColors.RESET);
        StreamBenchmark streamBenchmark = new StreamBenchmark();
        streamBenchmark.runBenchmark();
        reporter.addResult("Stream vs Loop", streamBenchmark.getResults());

        // 4. Parallel vs Sequential
        System.out.println(ConsoleColors.CYAN + "\n╔════════════════════════════════════════════╗");
        System.out.println("║   4. PARALLEL VS SEQUENTIAL STREAMS        ║");
        System.out.println("╚════════════════════════════════════════════╝" + ConsoleColors.RESET);
        ParallelBenchmark parallelBenchmark = new ParallelBenchmark();
        parallelBenchmark.runBenchmark();
        reporter.addResult("Parallel Processing", parallelBenchmark.getResults());

        // 5. Cache Performance
        System.out.println(ConsoleColors.CYAN + "\n╔════════════════════════════════════════════╗");
        System.out.println("║   5. CACHE IMPACT                          ║");
        System.out.println("╚════════════════════════════════════════════╝" + ConsoleColors.RESET);
        CacheBenchmark cacheBenchmark = new CacheBenchmark();
        cacheBenchmark.runBenchmark();
        reporter.addResult("Cache Performance", cacheBenchmark.getResults());

        // Final Report
        System.out.println(ConsoleColors.YELLOW + "\n╔════════════════════════════════════════════╗");
        System.out.println("║        FINAL PERFORMANCE REPORT            ║");
        System.out.println("╚════════════════════════════════════════════╝" + ConsoleColors.RESET);
        reporter.printSummary();

        printFooter();
    }

    private static void printHeader() {
        System.out.println(ConsoleColors.GREEN_BOLD);
        System.out.println("╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║                                                           ║");
        System.out.println("║     JAVA PERFORMANCE DEMONSTRATION PROJECT               ║");
        System.out.println("║     Performance Analysis in Java 21                       ║");
        System.out.println("║     by Michelle Ramos Henriques                           ║");
        System.out.println("║                                                           ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝");
        System.out.println(ConsoleColors.RESET);
        System.out.println("Starting benchmarks...\n");
    }

    private static void printFooter() {
        System.out.println(ConsoleColors.GREEN_BOLD);
        System.out.println("\n╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║         BENCHMARKS COMPLETED SUCCESSFULLY!                ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝");
        System.out.println(ConsoleColors.RESET);
    }
}