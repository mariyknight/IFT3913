# IFT3913

Wei Jia Huang (matricule:20217477)
Taha Zakariya (matricule:20188875)

Le lien vers notre repositoire: https://github.com/mariyknight/IFT3913

compiler: javac tropcomp.java , javac tassert.java, javac tls.java, javac tloc.java

-tloc

run java: java tloc <path_to_file>

run jar:

-tassert

run java: java tassert <path_to_file>

run jar:

-tls

run java: java tls -o <chemin-à-la-sortie.csv> <chemin-de-l'entrée>

run jar:

-tropcomp

run java : java tropcomp -o <chemin-à-la-sortie.csv> <chemin-de-l'entrée> <seuil> <seuil>

run jar : java -jar tropcomp.jar <chemin-de-l'entrée> <seuil>

Pour avoir les résultats de etude-jfreechart, il faut générer 3 fichiers aux noms: output1.csv , output5.csv et output10.csv

java -jar ./jars/tropcomp.jar -o etude-jfreechart/output1.csv ../jfreechart/src/test 1

java -jar ./jars/tropcomp.jar -o etude-jfreechart/output5.csv ../jfreechart/src/test 5

java -jar ./jars/tropcomp.jar -o etude-jfreechart/output10.csv ../jfreechart/src/test 10

