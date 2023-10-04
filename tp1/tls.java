import java.io.*;
import java.nio.file.*;
import java.util.List;

public class tls {
    public static void main(String[] args) throws Exception {
        if (args.length < 1 || args.length > 3) {
            System.out.println("Veuillez entrer dans le terminal: java tls [-o <path_output.csv>] <path_to_test_directory>");
            System.exit(1);
        }

        String outputPath = null;
        String inputPath;

        if (args[0].equals("-o")) {
            outputPath = args[1];
            inputPath = args[2];
        } else {
            inputPath = args[0];
        }

        try (PrintStream output = (outputPath != null) ? new PrintStream(new FileOutputStream(outputPath)) : System.out) {
            processDirectory(Paths.get(inputPath), output);
        }
    }

    public static void processDirectory(Path startPath, PrintStream output) throws Exception {
        Files.walk(startPath)
                .filter(Files::isRegularFile)
                .filter(p -> p.toString().endsWith(".java"))
                .forEach(p -> processJavaFile(startPath, p, output));
    }

    public static void processJavaFile(Path startPath, Path path, PrintStream output) {
        try {
            List<String> lines = Files.readAllLines(path);
            String packageName = "";
            boolean insideCommentBlock = false;
    
            int tloc = 0;
            int tassert = 0;
    
            for (String line : lines) {
                String trimmedLine = line.trim();
    
                if (trimmedLine.startsWith("/*") && !trimmedLine.endsWith("*/")) {
                    insideCommentBlock = true;
                }
    
                if (!insideCommentBlock && 
                    !trimmedLine.isEmpty() && 
                    !trimmedLine.startsWith("//") && 
                    !trimmedLine.startsWith("/*") && 
                    !trimmedLine.endsWith("*/")) {
                    tloc++;
                }
    
                if (trimmedLine.contains("assert")) {
                    tassert++;
                }
    
                if (trimmedLine.endsWith("*/")) {
                    insideCommentBlock = false;
                }
    
                if (trimmedLine.startsWith("package")) {
                    packageName = trimmedLine.replace("package", "").replace(";", "").trim();
                }
            }
    
            String relativePath = "./" + startPath.relativize(path).toString();
            String className = path.getFileName().toString().replace(".java", "");
            double tcmp = (tassert == 0) ? 0 : (double) tloc / tassert;
    
            output.printf("%s, %s, %s, %d, %d, %.2f%n",
                    relativePath, packageName, className, tloc, tassert, tcmp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}