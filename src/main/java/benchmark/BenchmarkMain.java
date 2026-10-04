package benchmark;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import metrics.Metrics;
import structures.DynamicArray;
import structures.MyLinkedList;
import java.util.Random;
import java.util.Arrays;

public class BenchmarkMain {
    private static final int[] SIZES = {100, 1_000, 10_000, 100_000};
    private static final int ACCESS_CALLS = 10_000;

    private static int[] generateData(int n) {
        int[] values = new int[n];
        Random random = new Random(42);

        for (int i = 0; i < n; i++) {
            values[i] = random.nextInt(1_000_000);
        }

        return values;
    }

    private static int[] generateIndices(int n) {
        int[] indices = new int[ACCESS_CALLS];
        Random random = new Random(42);

        for (int i = 0; i < ACCESS_CALLS; i++) {
            indices[i] = random.nextInt(n);
        }

        return indices;
    }

    private static volatile long blackhole;

    private static Trial runArrayOnce(int[] values, int[] indices) {
        Metrics metrics = new Metrics();
        DynamicArray array = new DynamicArray(metrics);

        for (int value : values) {
            array.add(value);
        }

        metrics.reset();

        long checksum = 0;
        long start = System.nanoTime();

        for (int index : indices) {
            checksum += array.get(index);
        }

        long elapsed = System.nanoTime() - start;
        blackhole = checksum;

        return new Trial(elapsed, metrics);
    }

    private static Trial runListOnce(int[] values, int[] indices) {
        Metrics metrics = new Metrics();
        MyLinkedList list = new MyLinkedList(metrics);

        for (int value : values) {
            list.add(value);
        }

        metrics.reset();

        long checksum = 0;
        long start = System.nanoTime();

        for (int index : indices) {
            checksum += list.get(index);
        }

        long elapsed = System.nanoTime() - start;
        blackhole = checksum;

        return new Trial(elapsed, metrics);
    }

    private static class Trial {
        long timeNanos;
        long steps;
        long moves;
        long comparisons;

        Trial(long timeNanos, Metrics metrics) {
            this.timeNanos = timeNanos;
            this.steps = metrics.steps;
            this.moves = metrics.moves;
            this.comparisons = metrics.comparisons;
        }
    }

    private static Trial benchmarkArray(int[] values, int[] indices) {
        runArrayOnce(values, indices);

        long[] times = new long[5];
        Trial lastTrial = null;

        for (int i = 0; i < times.length; i++) {
            lastTrial = runArrayOnce(values, indices);
            times[i] = lastTrial.timeNanos;
        }

        Arrays.sort(times);
        lastTrial.timeNanos = times[2];
        return lastTrial;
    }

    private static Trial benchmarkList(int[] values, int[] indices) {
        runListOnce(values, indices);

        long[] times = new long[5];
        Trial lastTrial = null;

        for (int i = 0; i < times.length; i++) {
            lastTrial = runListOnce(values, indices);
            times[i] = lastTrial.timeNanos;
        }

        Arrays.sort(times);
        lastTrial.timeNanos = times[2];
        return lastTrial;
    }

    public static void main(String[] args) throws IOException {
        Path output = Path.of("results", "results.csv");
        Files.createDirectories(output.getParent());

        try (BufferedWriter writer = Files.newBufferedWriter(output)) {
            writer.write("workload,variant,structure,n,time_ms,steps,moves,comparisons");
            writer.newLine();

            for (int n : SIZES) {
                int[] values = generateData(n);
                int[] indices = generateIndices(n);

                Trial arrayTrial = benchmarkArray(values, indices);
                writeRow(writer, "DynamicArray", n, arrayTrial);

                Trial listTrial = benchmarkList(values, indices);
                writeRow(writer, "MyLinkedList", n, listTrial);
            }
        }

        System.out.println("W1 results saved to results/results.csv");
    }

    private static void writeRow(
            BufferedWriter writer,
            String structure,
            int n,
            Trial trial
    ) throws IOException {
        String row = String.format(
                Locale.US,
                "W1,-,%s,%d,%.3f,%d,%d,%d",
                structure,
                n,
                trial.timeNanos / 1_000_000.0,
                trial.steps,
                trial.moves,
                trial.comparisons
        );

        writer.write(row);
        writer.newLine();
        System.out.println(row);
    }
}