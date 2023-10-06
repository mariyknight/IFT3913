import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class tloc {
    public static void main(String[] args) {
        // Vérification si l'argument du nom de fichier est passé
        if(args.length < 1){
            // Affiche le message d'informations sur comment passer le fichier en argument
            System.out.println("Veuillez entrer dans le terminal: java tloc <path_to_java_file>");
            // Termine l'execution du programme
            System.exit(1);
        }

        // Récupération du chemin du fichier passé en argument
        String filePath = args[0];

        try{
            // Calcule et affiche le nombre total de lignes de code
            int tloc = calculateTloc(filePath);
            System.out.println("Nombre de Ligne :" + tloc);
        } catch(IOException e) {
            // Affiche l'erreur en cas de problème de lecture du fichier 
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
    }

    // Méthode pour calculer le nombre total de lignes de code dans un fichier
    public static int calculateTloc(String filePath) throws IOException {
        // Initialisation du compteur de ligne de code
        int tloc = 0;
        // Définition des variables booléennes pour identifier les commentaires
        boolean comment = false;
        boolean javaDocComment = false;

        // Création d'un objet reader pour lire le contenu du fichier
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;

            // Parcour de chaque ligne du fichier
            while ((line = br.readLine()) != null) {

                // Elimine les espaces en début et en fin de ligne
                line=line.trim();

                // Si la ligne est un commentaire
                if (comment || javaDocComment) {
// Vérifie si la ligne contient la fin d'un commentaire
                    if (line.contains("/")) {
                        // Met fin au commentaire
                        comment = false;
                        javaDocComment = false;
                        // Enlève le commentaire de la ligne
                        line = line.substring(line.indexOf("/") + 2);
                    }
                    // Le cas conraire, ignore la ligne
                    else {
                        continue;
                    }
                }

                // Si la ligne est un commentaire de type JavaDoc
                if (line.startsWith("/*")) {
                    javaDocComment = true;
                    if (line.contains("/")) {
                        // Met fin au commentaire si la ligne contient la fin du commentaire
                        javaDocComment = false;
                        // Enlève le commentaire de la ligne
                        line = line.substring(line.indexOf("/") + 2);
                    }
                    // Le cas conraire, ignore la ligne
                    else {
                        continue;
                    }
                }

                // Si ligne est un commentaire
                if (line.startsWith("/")) {
                    comment = true;
                    if (line.contains("/")) {
                        // Met fin au commentaire si la ligne contient la fin du commentaire
                        comment = false;
                        // Enlève le commentaire de la ligne
                        line = line.substring(line.indexOf("/") + 2);
                    }
                    // Le cas conraire, ignore la ligne
                    else {
                        continue;
                    }
                }
                // S'il s'agit d'une ligne vide ou d'un commentaire sur une seule ligne, on l'ignore
if (line.isEmpty() || line.startsWith("//")) {
                    continue;
                }

                // On incrémente le nombre de ligne de code
                tloc++;
            }
        }
        // Retourne le nombre total de lignes de code
        return tloc;
    }
}
