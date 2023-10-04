import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class tassert {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Veuillez entrer dans le terminal: java tassert <path_to_java_file>");
            System.exit(1);
        }

        String filePath = args[0];

        try{
            int tloc = calculateTassert(filePath);
            System.out.println("Nombre d'assert :" + tloc);
        } catch(IOException e) {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
    }

    public static int calculateTassert(String filePath) throws IOException {
        int tassert = 0;

         try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
    
            String line;

            while ((line = br.readLine()) != null){
                line = line.trim();
                if(line.contains("assert")){
                    tassert++;
                }
                else if(line.contains("assertTrue")){
                    tassert++;
                }
                else if(line.contains("assertFalse")) {
                    tassert++;
                }
                else if(line.contains("assertArrayEquals")) {
                    tassert++;
                }
                else if(line.contains("assertEquals")) {
                    tassert++;
                }
                else if(line.contains("assertNotEquals")) {
                    tassert++;
                }
                else if(line.contains("assertNotNull")) {
                    tassert++;
                }
                else if(line.contains("assertNotSame")) {
                    tassert++;
                }
                else if(line.contains("assertNull")) {
                    tassert++;
                }
                else if(line.contains("assertSame")) {
                    tassert++;
                }
                else if(line.contains("assertThat")) {
                    tassert++;
                }
                else if(line.contains("assertThrows")) {
                    tassert++;
                }
                else if(line.contains("fail")) {
                    tassert++;
                }
            }
        }
        return tassert;
    }
}
