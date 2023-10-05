import java.io.*;
import java.nio.file.*;
import java.util.*;
//pr les calcul utiliser percentile...
//metric : String path;
//-----calcul pour sueil etc
//        int tloc;
//        int tassert;
//        double tcmp;
public class tropcomp {
    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("Veuillez entrer dans le terminal: java tropcomp [-o <path_output.csv>] <path_to_test_directory> <seuil>");
            System.exit(1);
        }

        String outputPath = null;
        String inputPath;
        double seuil;

        if (args[0].equals("-o")) {
            outputPath = args[1];
            inputPath = args[2];
            seuil = Double.parseDouble(args[3]);
        } else {
            inputPath = args[0];
            seuil = Double.parseDouble(args[1]);
        }
        
        // Liste pour stocker les details des classes de test
        List<Map<String, Object>> testClasses = new ArrayList<>();
        
        // Traiter le répertoire et calculer les mesures
        testClasses = processDirectory(Paths.get(inputPath));
        
        // Filtrer les classes de test en fonction du seuil fourni 
        List<Map<String, Object>> topThresholdClasses = getTopThresholdClasses(testClasses, seuil);
        
        // Sortir les détails des classes dans le seuil supérieur
        try (PrintStream output = (outputPath != null) ? new PrintStream(new FileOutputStream(outputPath)) : System.out) {
            topThresholdClasses.forEach(testClass -> output.println(testClass.toString()));
        }
    }

    public static List<Map<String, Object>> processDirectory(String filePath) {
        // traite le répertoire,
        // calcule les mesures TLOC, TASSERT et TCMP et renvoie 
       
    }

    public static List<Map<String, Object>> getTopThresholdClasses(List<Map<String, Object>> testClasses, double seuil) {
        // filtre les classes de test en fonction du seuil fourni. 
    }
}
