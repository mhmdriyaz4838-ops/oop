package miniproject2;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

public class LogAnalyzer {

    private static final ConcurrentHashMap<String, Integer> GLOBAL_COUNTS = new ConcurrentHashMap<>();

    private static final List<String> KEYWORDS = Arrays.asList(
            "ERROR", "WARN", "INFO", "DEBUG"
    );

    public static void main(String[] args) throws Exception {

        if (args.length < 1) {
            System.out.println("Usage: java LogAnalyzer <folderPath>");
            return;
        }

        String folderPath = args[0];
        Path folder = Paths.get(folderPath);

        if (!Files.isDirectory(folder)) {
            System.out.println("Invalid folder path!");
            return;
        }

        List<Path> files = Files.list(folder)
                .filter(f -> f.toString().endsWith(".txt") || f.toString().endsWith(".log"))
                .toList();

        System.out.println("Found files: " + files.size());

        int N = Math.max(2, Runtime.getRuntime().availableProcessors());
        ExecutorService executor = Executors.newFixedThreadPool(N);

        long start = System.currentTimeMillis();

        List<Callable<Void>> tasks = new ArrayList<>();
        for (Path file : files) {
            tasks.add(() -> analyzeFile(file));
        }

        executor.invokeAll(tasks);
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        long end = System.currentTimeMillis();

        System.out.println("\n=== FINAL AGGREGATED COUNTS ===");
        GLOBAL_COUNTS.forEach((k, v) -> System.out.println(k + ": " + v));

        System.out.println("\nTotal execution time: " + (end - start) + " ms");

        writeResultFile(GLOBAL_COUNTS, end - start);

        System.out.println("\nResult file written: result_summary.txt");
    }

    private static Void analyzeFile(Path file) {
        System.out.println("Processing: " + file.getFileName() +
                " on thread " + Thread.currentThread().getName());

        try (Stream<String> lines = Files.lines(file)) {

            Map<String, Integer> localCounts =
                    lines.flatMap(line -> KEYWORDS.stream()
                                    .filter(line::contains))
                            .collect(Collectors.toConcurrentMap(
                                    k -> k,
                                    v -> 1,
                                    Integer::sum
                            ));

            localCounts.forEach((k, v) ->
                    GLOBAL_COUNTS.merge(k, v, Integer::sum));

        } catch (IOException ex) {
            System.err.println("Error reading file: " + file.getFileName());
        }

        return null;
    }

    private static void writeResultFile(Map<String, Integer> counts, long time) {
        try (PrintWriter pw = new PrintWriter("result_summary.txt")) {
            pw.println("=== Aggregated Keyword Counts ===");
            counts.forEach((k, v) -> pw.println(k + ": " + v));

            pw.println("\nExecution Time: " + time + " ms");
        } catch (IOException e) {
            System.err.println("Error writing result file.");
        }
    }
}
