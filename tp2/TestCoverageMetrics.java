import java.io.*;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class TestCoverageMetrics {

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: java TestCoverageMetrics <path_to_root_directory>");
            System.exit(1);
        }

        String rootDirPath = args[0];
        Map<String, AtomicInteger> sourceLinesMap = new HashMap<>();
        Map<String, AtomicInteger> testLinesMap = new HashMap<>();

        // Walk through all files in the root directory
        Files.walk(Paths.get(rootDirPath))
                .filter(Files::isRegularFile)
                .filter(p -> p.toString().endsWith(".java"))
                .filter(p -> !isInterface(p))  // Only process the file if it's not an interface
                .forEach(p -> {
                    try {
                        String fileName = p.getFileName().toString();
                        String className = fileName.endsWith("Test.java") ? fileName.substring(0, fileName.length() - 9) : fileName.substring(0, fileName.length() - 5);
                        int tlocValue = tloc.calculateTloc(p.toString());
                        if (fileName.endsWith("Test.java")) {
                            // It's a test file
                            testLinesMap.computeIfAbsent(className, k -> new AtomicInteger(0)).addAndGet(tlocValue);
                        } else {
                            // It's a source file
                            sourceLinesMap.computeIfAbsent(className, k -> new AtomicInteger(0)).addAndGet(tlocValue);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        int count = 0;
        double sum = 0.0;
        // Calculate the M1 metric: sourceLines/testLines for each class and print the result
        for (String className : sourceLinesMap.keySet()) {
            int sourceLines = sourceLinesMap.get(className).get();
            AtomicInteger testLinesAtomic = testLinesMap.get(className);
            int testLines = (testLinesAtomic != null) ? testLinesAtomic.get() : 0;
            double ratio = (double) testLines / (sourceLines != 0 ? sourceLines : 1);
            double ratio_percent = ratio * 100; // Convert ratio to percentage
            System.out.printf("Code size to test size ratio for %s: %.2f%%\n", className, ratio_percent);
            sum += ratio;
            count++;
        }

        // Calculate and print the average ratio
        double average = sum / (count != 0 ? count : 1);
        double average_percent = average * 100; // Convert average ratio to percentage
        System.out.printf("Average code size to test size ratio (M1): %.2f%%\n", average_percent);
    }

    // To check if the provided file is an interface, by reading the first few lines
    private static boolean isInterface(Path path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().startsWith("public interface") || line.trim().startsWith("interface")) {
                    return true;
                }
                if (line.trim().startsWith("public class") || line.trim().startsWith("class") || line.trim().startsWith("@")) {
                    return false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}