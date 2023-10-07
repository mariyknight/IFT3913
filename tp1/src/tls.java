import java.io.*;
import java.nio.file.*;
import java.util.List;

public class tls {
    public static void main(String[] args) throws Exception {
        // Vérifier le nombre de paramètres d'entrée
        if (args.length < 1 || args.length > 3) {
            // Message d'erreur pour l'utilisateur
            System.out.println("Veuillez entrer dans le terminal: java tls [-o <path_output.csv>] <path_to_test_directory>");
            System.exit(1);  // Quitter le programme avec le code d'erreur 1
        }

        String outputPath = null;
        String inputPath;

        // Si le premier argument est "-o", alors il y a une sortie optionnelle
        if (args[0].equals("-o")) {
            outputPath = args[1];  // Chemin de sortie
            inputPath = args[2];  // Chemin d'entrée
        } else {
            inputPath = args[0];  // Sinon, seul le chemin d'entrée est fourni
        }

        // Si outputPath est défini, utilisez-le pour les sorties, sinon utilisez la sortie standard
        try (PrintStream output = (outputPath != null) ? new PrintStream(new FileOutputStream(outputPath)) : System.out) {
            processDirectory(Paths.get(inputPath), output);  // Processus du répertoire
        }
    }

    // Méthode pour traiter un répertoire, filtrer seulement les fichiers .java
    public static void processDirectory(Path startPath, PrintStream output) throws Exception {
        Files.walk(startPath)
                .filter(Files::isRegularFile)
                .filter(p -> p.toString().endsWith(".java"))
                .forEach(p -> processJavaFile(startPath, p, output));
    }

    // Méthode pour traiter un fichier Java
    public static void processJavaFile(Path startPath, Path path, PrintStream output) {
        try {
            List<String> lines = Files.readAllLines(path);
            String packageName = "";
            boolean insideCommentBlock = false;
    
            int tloc = 0;  // Total Lines Of Code
            int tassert = 0;  // Total assertions

            // Balayer toutes les lignes
            for (String line : lines) {
                String trimmedLine = line.trim();  // Ligne coupée
    
                // Communauté des commentaires
                if (trimmedLine.startsWith("/*") && !trimmedLine.endsWith("*/")) {
                    insideCommentBlock = true;
                }
    
                // Vérifier les lignes (TLOC)
                if (!insideCommentBlock && 
                    !trimmedLine.isEmpty() && 
                    !trimmedLine.startsWith("//") && 
                    !trimmedLine.startsWith("/*") && 
                    !trimmedLine.endsWith("*/")) {
                    tloc++;
                }
    
                // Vérifier les assertions (TASSERT)
                if (trimmedLine.contains("assert")) {
                    tassert++;
                }
    
                // Fin des commentaires 
                if (trimmedLine.endsWith("*/")) {
                    insideCommentBlock = false;
                }
    
                // Nom du package
                if (trimmedLine.startsWith("package")) {
                    packageName = trimmedLine.replace("package", "").replace(";", "").trim();
                }
            }
    
            String relativePath = "./" + startPath.relativize(path).toString();  // Chemin relatif
            String className = path.getFileName().toString().replace(".java", "");  // Nom de classe
            double tcmp = (tassert == 0) ? 0 : (double) tloc / tassert;  // CMPT (complexity metric)
    
            // Format de sortie
            output.printf("%s, %s, %s, %d, %d, %.2f%n",
                    relativePath, packageName, className, tloc, tassert, tcmp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
