import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
//marche mais reste commentaires + response.txt, il pr seuil = 10 -> tropcomp retourn 3 results ; pr 1 et 5 ->retourne 0 result.
public class tropcomp {
    public static void main(String[] args) throws Exception {
        if (args.length < 2 || args.length > 4) {
            System.out.println("Veuillez entrer dans le terminal: java tropcomp [-o <path_output.csv>] <path_to_main_project> <threshold>");
            System.exit(1);
        }

        String sortie = null;
        String cheminProjet;
        double seuil;

        if (args[0].equals("-o")) {
            sortie = args[1];
            cheminProjet = args[2];
            seuil = Double.parseDouble(args[3]);
        } else {
            cheminProjet = args[0];
            seuil = Double.parseDouble(args[1]);
        }

       try (PrintStream output = (sortie != null) ? new PrintStream(new FileOutputStream(sortie)) : System.out) {
            List<ClassMetrics> metricsList = getMetricsFromDirectory(Paths.get(cheminProjet));
            int tlocSeuil = getSeuilTloc(metricsList, seuil);
            double tcmpSeuil = getSeuilTcmp(metricsList, seuil);

            metricsList = metricsList.stream()
                                     .filter(m -> m.tloc >= tlocSeuil && m.tcmp >= tcmpSeuil)
                                     .collect(Collectors.toList());
            for (ClassMetrics metrics : metricsList) {
                output.printf("%s, %d, %.2f%n", metrics.relativePath, metrics.tloc, metrics.tcmp);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    static List<ClassMetrics> getMetricsFromDirectory(Path projectPath) throws IOException {
        List<ClassMetrics> metricsList = new ArrayList<>();
        
        Files.walk(projectPath)
             .filter(Files::isRegularFile)
             .filter(p -> p.toString().endsWith(".java"))
             .forEach(p -> {
                try {
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

    private static double getSeuilTcmp(List<ClassMetrics> metricsList, double threshold) {
        double[] tcmpValues = new double[metricsList.size()];
        for (int i = 0; i < metricsList.size(); i++) {
            tcmpValues[i] = metricsList.get(i).tcmp;
        }
        Arrays.sort(tcmpValues);
        int index = (int) (metricsList.size() * (1 - threshold / 100.0));
        return (index < tcmpValues.length) ? tcmpValues[index] : 0.0;
    }
    
    private static int getSeuilTloc(List<ClassMetrics> metricsList, double threshold) {
        int[] tlocValues = new int[metricsList.size()];
        for (int i = 0; i < metricsList.size(); i++) {
            tlocValues[i] = metricsList.get(i).tloc;
        }
        Arrays.sort(tlocValues);
        int index = (int) (metricsList.size() * (1 - threshold / 100.0));
        return (index < tlocValues.length) ? tlocValues[index] : 0;
    }
    
    
    
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
