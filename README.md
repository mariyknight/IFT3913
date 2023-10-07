# IFT3913

Wei Jia Huang (matricule:20217477)
Taha Zakariya (matricule:20188875)

Le lien vers notre repositoire: https://github.com/mariyknight/IFT3913

Il faut commencer par se diriger vers le fichier .\IFT3913\tp1\src et par la suite <br>
compiler: javac tropcomp.java , javac tassert.java, javac tls.java, javac tloc.java

-tloc

run java: java tloc <path_to_file> -- Vous pouvez rouler tloc directement dans le terminal à partir de ce commande <br>
ex. java tloc jfreechart/src/test/java/org/jfree/chart/title/TitleTest.java 

run jar: java -jar tloc.jar <path_to_file> <br>
ex. java -jar tloc.jar jfreechart/src/test/java/org/jfree/chart/title/TitleTest.java

-tassert

run java: java tassert <path_to_file> <br>
ex. java tloc jfreechart/src/test/java/org/jfree/chart/title/TitleTest.java

run jar: java -jar tassert.jar <path_to_file> <br>
ex. java -jar tloc.jar jfreechart/src/test/java/org/jfree/chart/title/TitleTest.java

-tls

run java: java tls -o <chemin-à-la-sortie.csv> <chemin-de-l'entrée> <br>
ex. java tls  jfreechart/src/test/java/org/jfree/chart/title -- Vous pouvez avoir les résultats directement dans le terminal sans générer un fichier csv <br>
ex. java tls  -o C:\Users\huang\OneDrive\Desktop\resultat.csv jfreechart/src/test/java/org/jfree/chart/title -- Sinon générer un fichier csv pour stocker les données <br>

run jar: java -jar tls.jar <chemin-de-l'entrée>
ex. java - jar tls.jar  jfreechart/src/test/java/org/jfree/chart/title

-tropcomp

run java : java tropcomp -o <chemin-à-la-sortie.csv> <chemin-de-l'entrée> <seuil> <seuil> <br>
ex. java tropcomp -o C:\Users\huang\OneDrive\Desktop\seuil.csv  ./tp1/src/jfreechart/src/test 10

run jar : java -jar tropcomp.jar <chemin-de-l'entrée> <seuil> <br>
ex. java -jar tropcomp.jar ./tp1/src/jfreechart/src/test 10

Pour avoir les résultats de etude-jfreechart, il faut générer 3 fichiers aux noms: output1.csv , output5.csv et output10.csv

ex. java -jar ./jars/tropcomp.jar -o etude-jfreechart/output1.csv ../jfreechart/src/test 1 <br>
ex. java -jar ./jars/tropcomp.jar -o etude-jfreechart/output5.csv ../jfreechart/src/test 5 <br>
ex. java -jar ./jars/tropcomp.jar -o etude-jfreechart/output10.csv ../jfreechart/src/test 10 <br>

