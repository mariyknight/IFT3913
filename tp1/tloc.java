import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class tloc {
    public static void main(String[] args) {
        if(args.length < 1){
            System.out.println("Veuillez entrer dans le terminal: java tloc <path_to_java_file>");
            System.exit(1);
        }

        String filePath = args[0];

        try{
            int tloc = calculateTloc(filePath);
            System.out.println("Nombre de Ligne :" + tloc);
        } catch(IOException e) {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
    }

    public static int calculateTloc(String filePath) throws IOException {
        int tloc = 0;
        boolean comment = false;
        boolean javaDocComment = false;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            
            String line;

            while ((line = br.readLine()) != null) {

                line=line.trim();
                 
                if (comment || javaDocComment) {
                    if (line.contains("*/")) {
                        comment = false;
                        javaDocComment = false;
                        line = line.substring(line.indexOf("*/") + 2);
                    }
                    else {
                        continue;
                    }
                    
                }

                if (line.startsWith("/**")) {
                    javaDocComment = true;
                    if (line.contains("*/")) {
                        javaDocComment = false;
                        line = line.substring(line.indexOf("*/") + 2);
                    }
                    else {
                        continue;
                    }
                }
                
                if (line.startsWith("/*")) {
                    comment = true;
                    if (line.contains("*/")) {
                        comment = false;
                        line = line.substring(line.indexOf("*/") + 2);
                    }
                    else {
                        continue;
                    }
                }

                if (line.isEmpty() || line.startsWith("//")) {
                    continue;
                }

                tloc++;
            }
        }
        return tloc;
    }
}
