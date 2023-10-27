import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class TestComplexityMetrics {

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: java TestComplexityMetrics <path_to_test_directory>");
            System.exit(1);
        }

        String testDirPath = args[0];
        List<TestClassMetrics> metricsList = new ArrayList<>();

        // Retrieve M5 and M6 metrics for all test files
        Files.walk(Paths.get(testDirPath))
                .filter(Files::isRegularFile)
                .filter(p -> p.toString().endsWith("Test.java"))
                .forEach(p -> {
                    try {
                        int tlocValue = tloc.calculateTloc(p.toString());
                        int ccValue = calculateCyclomaticComplexity(p);
                        metricsList.add(new TestClassMetrics(p, tlocValue, ccValue));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        // Compute average Cyclomatic Complexity (M5) and Lines of Code (M6)
        double averageM5 = metricsList.stream().mapToInt(m -> m.cc).average().orElse(Double.NaN);
        double averageM6 = metricsList.stream().mapToInt(m -> m.tloc).average().orElse(Double.NaN);
        System.out.printf("Average Test Cyclomatic Complexity (M5): %.2f\n", averageM5);
        System.out.printf("Average Test Lines Of Code (M6): %.2f\n", averageM6);
    }

    // Calculate Cyclomatic Complexity (CC) for a file
    public static int calculateCyclomaticComplexity(Path path) throws IOException {
        Pattern pattern = Pattern.compile("\\b(if|for|while|switch|case|catch|\\&\\&|\\|\\|)\\b");

        int ccCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (pattern.matcher(line).find()) {
                    ccCount++;
                }
            }
        }
        return ccCount;
    }

    private static class TestClassMetrics {
        private final Path path;
        private final int tloc;
        private final int cc;

        public TestClassMetrics(Path path, int tloc, int cc) {
            this.path = path;
            this.tloc = tloc;
            this.cc = cc;
        }
    }
}
