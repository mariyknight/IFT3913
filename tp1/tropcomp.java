import java.io.*;
import java.nio.file.*;
import java.util.*;
//----not working yet need % / seuil calculation and process project paths... 
public class tropcomp {public static void main(String[] args) throws Exception {
        if (args.length < 2 || args.length > 4) {
            System.out.println("Veuillez entrer dans le terminal: java tropcomp [-o <path_output.csv>] <path_to_main_project> <threshold>");
            System.exit(1);
        }
        
        String outputPath = null;
        String inputPath;
        double threshold = 0;

        if (args[0].equals("-o")) {
            outputPath = args[1];
            inputPath = args[2];
            threshold = Double.parseDouble(args[3]);
        } else {
            inputPath = args[0];
            threshold = Double.parseDouble(args[1]);
        }
        
        try{
            List<ClassMetrics> metricsList = getMetricsFromDirectory(inputPath);
            metricsList = metricsList.stream()
                                      .filter(m -> isComplexe(m, threshold))
                                      .collect(Collectors.toList());

            try (PrintStream output = (outputPath != null) ? new PrintStream(new FileOutputStream(outputPath)) : System.out) {
                metricsList.stream().forEach(m -> 
                    output.printf("%s, %s, %s, %d, %d, %.2f%n", m.relativePath, m.packageName, m.className, m.tloc, m.tassert, m.tcmp)
                );
                output.close();
            }       
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // collect class metrics of all java files in the directory and its subdirectories
    static List<ClassMetrics> getMetricsFromDirectory(String inputPath) {
        
        // Return metrics in a list
        return new ArrayList<>();
    }

    // check if a class is suspected to be complex (based on its metrics)
    static boolean maybeComplex(ClassMetrics metrics, double threshold) {
        // heuristic...
        // return true if both TLOC and TCMP are in the top `threshold` percent of all classes
        return false;
    }

    static class ClassMetrics {
        String relativePath;
        String packageName;
        String className;
        int tloc;
        int tassert;
        double tcmp;
    }
}
