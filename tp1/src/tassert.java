import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class tassert {

    public static void main(String[] args) {
        //Vérifie si le nombre d'arguments est inférieur à 1.
        if (args.length < 1) {
            System.out.println("Veuillez entrer dans le terminal: java tassert <path_to_java_file>");
            System.exit(1);
        }

        //Obtention du chemin d'accès au fichier à partir des arguments de la ligne de commande
        String filePath = args[0];

        try{
            int tloc = calculateTassert(filePath);
            System.out.println("Nombre d'assert :" + tloc);
        }
        //Gère l'exception si une erreur se produit dans l'opération de lecture du fichier.
        catch(IOException e) {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
    }

    //Méthode pour calculer le nombre de 'assert' dans le fichier Java.
    public static int calculateTassert(String filePath) throws IOException {
        //Initialisation du compteur de 'assert'
        int tassert = 0;

         //Création d'un objet FileReader enveloppé dans un BufferedReader
         try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;

            //Lecture de chaque ligne du fichier Java
            while ((line = br.readLine()) != null){
                //Suppression de l'espace en tête et en queue de la ligne
                line = line.trim();
                //Vérification si 'assert' est présent dans la ligne
                if(line.contains("assert")){
                    tassert++;
                }
                //Checking for each type of assert keywords and incrementing the counter if it is present
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
        //Renvoie le nombre total d'affirmations trouvées dans le fichier
        return tassert;
    }
}
