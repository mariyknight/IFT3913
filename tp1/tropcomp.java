import java.io.*;
import java.nio.file.*;
import java.util.List;

public class tls {
    public static void main(String[] args) throws Exception {
        if (args.length < 1 || args.length > 3) {
            System.out.println("Usage: java Tls [-o <path_output.csv>] <path_to_test_directory>");
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

    
}
