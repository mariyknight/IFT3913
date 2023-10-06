import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class tropcomp {
    public static void main(String[] args) throws Exception {
        // Vérifie le nombre d'arguments donnés lors de l'exécution
        if (args.length < 2 || args.length > 4) {
            System.out.println("Veuillez entrer dans le terminal: java tropcomp [-o <path_output.csv>] <path_to_main_project> <threshold>");
            System.exit(1);
        }

        // Déclaration des variables
        String sortie = null;
        String cheminProjet;
        double seuil;

        // Assignation des variables par rapport aux arguments donnés 
        // dans le terminal lors de l'exécution du programme
        if (args[0].equals("-o")) {
            sortie = args[1];
            cheminProjet = args[2];
            seuil = Double.parseDouble(args[3]);
        } else {
            cheminProjet = args[0];
            seuil = Double.parseDouble(args[1]);
        }

        // Traitement des différentes classes Java du projet
        try (PrintStream output = (sortie != null) ? new PrintStream(new FileOutputStream(sortie)) : System.out) {
            List<ClassMetrics> metricsList = getMetricsFromDirectory(Paths.get(cheminProjet));
            int tlocSeuil = getSeuilTloc(metricsList, seuil);
            double tcmpSeuil = getSeuilTcmp(metricsList, seuil);

            // Filtre les classes en fonction des valeurs calculées
            metricsList = metricsList.stream()
                    .filter(m -> m.tloc >= tlocSeuil && m.tcmp >= tcmpSeuil)
                    .collect(Collectors.toList());
            
            // Parcoure les classes filtrées pour les traiter individuellement
            for (ClassMetrics metrics : metricsList) {
                tls.processJavaFile(Paths.get(cheminProjet), Paths.get(metrics.relativePath), output);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    // Méthode qui récupère les métriques (tloc, tassert, tcmp) 
    // de toutes les classes Java d'un projet
    static List<ClassMetrics> getMetricsFromDirectory(Path projectPath) throws IOException {
        List<ClassMetrics> metricsList = new ArrayList<>();
        
        // Parcoure tous les fichiers Java du projet
        Files.walk(projectPath)
                .filter(Files::isRegularFile)
                .filter(p -> p.toString().endsWith(".java"))
                .forEach(p -> {
                    try {
                        // Calcule les valeurs tloc, tassert et tcmp pour le fichier actuel
                        int tlocValue = tloc.calculateTloc(p.toString());
                        int tassertValue = tassert.calculateTassert(p.toString());
                        double tcmp = tassertValue != 0 ? (double) tlocValue / tassertValue : 0;
                        metricsList.add(new ClassMetrics(p.toString(), tlocValue, tassertValue, tcmp));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        return metricsList;
    }

    // Calculer le seuil tcmp
    private static double getSeuilTcmp(List<ClassMetrics> metricsList, double threshold) {
        double[] tcmpValues = new double[metricsList.size()];
        for (int i = 0; i < metricsList.size(); i++) {
            tcmpValues[i] = metricsList.get(i).tcmp;
        }
        Arrays.sort(tcmpValues);
        int index = (int) (metricsList.size() * (1 - threshold / 100.0));
        return (index < tcmpValues.length) ? tcmpValues[index] : 0.0;
    }
    //Calculer le seuil tloc
    private static int getSeuilTloc(List<ClassMetrics> metricsList, double threshold) {
        int[] tlocValues = new int[metricsList.size()];
        for (int i = 0; i < metricsList.size(); i++) {
            tlocValues[i] = metricsList.get(i).tloc;
        }
        Arrays.sort(tlocValues);
        int index = (int) (metricsList.size() * (1 - threshold / 100.0));
        return (index < tlocValues.length) ? tlocValues[index] : 0;
    }

    // Classe interne pour gérer les métriques d'une classe
    static class ClassMetrics {
        String relativePath;
        int tloc;
        int tassert;
        double tcmp;

        public ClassMetrics(String relativePath, int tloc, int tassert, double tcmp) {
            this.relativePath = relativePath;
            this.tloc = tloc;
            this.tassert = tassert;
            this.tcmp = tcmp;
        }
    }
}
