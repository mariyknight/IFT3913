import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class tloc {
    public static void main(String[] args) {
        if(args.length < 1){
            System.out.println("");
            return;
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

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            boolean comment = false;

            String line;

            while ((line = br.readLine()) != null) {

                line=line.trim();
                 
                if (!comment && line.startsWith("/*")) {
                    comment = true;
                }

                if (comment && line.endsWith("*/")) {
                    comment = false;
                    continue;
                }
                
                if (comment) {
                    continue;
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
