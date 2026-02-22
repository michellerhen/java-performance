package org.example.utils;

/**
 * Class for measuring operation execution time
 */
public class PerformanceTimer {
    private long startTime;
    private long endTime;

    public void start() {
        startTime = System.nanoTime();
    }

    public void stop() {
        endTime = System.nanoTime();
    }

    public long getElapsedNanos() {
        return endTime - startTime;
    }

    public double getElapsedMillis() {
        return (endTime - startTime) / 1_000_000.0;
    }

    public double getElapsedSeconds() {
        return (endTime - startTime) / 1_000_000_000.0;
    }

    public String getFormattedTime() {
        long nanos = getElapsedNanos();

        if (nanos < 1_000) {
            return nanos + " ns";
        } else if (nanos < 1_000_000) {
            return String.format("%.2f μs", nanos / 1_000.0);
        } else if (nanos < 1_000_000_000) {
            return String.format("%.2f ms", nanos / 1_000_000.0);
        } else {
            return String.format("%.2f s", nanos / 1_000_000_000.0);
        }
    }
}

