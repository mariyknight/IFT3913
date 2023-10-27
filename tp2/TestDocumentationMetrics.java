import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TestDocumentationMetrics {

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: java TestDocumentationMetrics <path_to_test_directory>");
            System.exit(1);
        }

        String testDirPath = args[0];
        List<TestClassMetrics> metricsList = new ArrayList<>();

        // Retrieve metrics for all test files
        Files.walk(Paths.get(testDirPath))
                .filter(Files::isRegularFile)
                .filter(p -> p.toString().endsWith("Test.java"))
                .forEach(p -> {
                    try {
                        int tlocValue = tloc.calculateTloc(p.toString());
                        int clocValue = calculateCloc(p);
                        metricsList.add(new TestClassMetrics(p, tlocValue, clocValue));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        // Compute average DC (Comment Density)
        double averageDC = metricsList.stream().filter(m -> m.tloc != 0).mapToDouble(m -> (double) m.cloc / m.tloc).average().orElse(Double.NaN);
        double average_percent = averageDC * 100; // Convert average ratio to percentage
        System.out.printf("Average Test Comment Density (M7): %.2f%%\n", average_percent);


    }

    // Calculate number of comment lines (CLOC) in a file
    public static int calculateCloc(Path path) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
            String line;
            int commentLineCount = 0;
            boolean ignoreSection = false;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                // Check for start of section to ignore.
                if (line.equals("/* ===========================================================")) {
                    ignoreSection = true;
                }

                // Check for end of section to ignore.
                if (line.equals("*/")) {
                    ignoreSection = false;
                    continue;
                }

                // Do not count lines in the ignored section.
                if (!ignoreSection) {
                    if (line.startsWith("//") || line.startsWith("/*") || line.startsWith("*")) {
                        commentLineCount++;
                    }
                }
            }
            return commentLineCount;
        }
    }

    private static class TestClassMetrics {
        private final Path path;
        private final int tloc;
        private final int cloc;

        public TestClassMetrics(Path path, int tloc, int cloc) {
            this.path = path;
            this.tloc = tloc;
            this.cloc = cloc;
        }
    }
}
