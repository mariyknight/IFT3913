IFT3913 QUALITÉ DE LOGICIEL ET MÉTRIQUES – AUTOMNE 2023 – TRAVAIL PRATIQUE 3
TRAVAIL À RÉALISER
Ce travail a pour objectif d’étudier un échantillon de données fourni dans le fichier jfreechart-test-stats.csv. Cet échantillon donne les valeurs de métriques collectées par les classes du dossier test du JFreechart:
• TLOC : Nombre de lignes de code non-vides qui ne sont pas de commentaires
• WMC : Weighted Methods per Class
• TASSERT: nombre d’ assertions
Les tâches à effectuer :
T1. (30%) Visualisez chacune des métriques de l’échantillon en créant les boites à moustaches. Calculez les informations pertinentes selon les définitions du cours (diapositives 6,7) et décrivez les distributions.
T2. (30%) Étudiez les corrélations entre TLOC et TASSERT, WMC et TASSERT. Visualisez les données, calculez les droits de régression, et les coefficients de corrélation qui ont du sens.
T3. (40%) Nous voulons évaluer l’hypothèse : « les classes qui contiennent plus de 20 assertions sont plus complexes que celles contiennent moins de 20 assertions ». Décrivez la conception d’un quasi-expérience qui vous permettra de le faire. Ensuite, évaluez l’hypothèse, discutez les résultats et décrivez vos conclusions.
Rappelez-vous que selon les diapositives du cours, les étapes à suivre sont : choix d’étude, énoncé des hypothèses, définition des variables, interprétation et généralisation des résultats, discussion des menaces à la validité.
PRÉCISIONS GLOBALES
1. Travail à remettre le vendredi 17 novembre 23h59 via StudiUM. Aucun retard ne sera accepté.
2. Le livrable le plus important est le rapport, en format PDF. Assurez-vous de communiquer clairement en français, la
qualité de votre rapport est très importante. Un mauvais rapport pourrait causer une déduction très significative.
3. Travaillez en groupes de 2. Aucune soumission individuelle ne sera acceptée. Soumettez un ZIP nommé comme suit :
prenom1_nom1_prenom2_nom2.zip
4. Le membre de l’équipe qui ne soumet pas le fichier Zip principal de la soumission doit soumettre un zip (même nom) avec le fichier readme.txt, qui devrait contenir les noms de l'équipe.
5. Les rapports doivent compter au maximum 4 pages, y compris toutes les figures et références. Rapports de plus de 4 pages vont être éliminés d’office (par défaut).
6. Votre fichier ZIP doit aussi contenir un readme.txt avec liens vers tous ressources nécessaires (y compris votre repositoire git).
7. Comme en TP1 et TP2, vous devez utiliser un repositoire git pour stocker tout code, script, etc (idéalement, le même repositoire que TP1, TP2). Vous pouvez utiliser n’importe quel service gratuit comme Github, Bitbucket, et autres (quelques-uns vous permettent de créer des comptes académiques avec votre courriel @umontreal.ca). Utilisez le repositoire pour collaborer avec votre coéquipier.
8. Vous pouvez utiliser n’importe quel logiciel qui appuie les opérations statistiques requises (Excel, R, Octave, SPSS, Python, Java, ...). Cependant, nous n'offrons pas de conseils sur l'utilisation des logiciels statistiques.
9. Vous devez mettre dans votre repositoire git tout script, ou fichier de configuration que vous avez utilisé. En fait, votre repositoire doit contenir assez des détails pour nous convaincre que vous avez vraiment effectué le travail vous-même.
10. Nous allons examiner l’historique de votre repositoire pour nous assurer que tous les deux coéquipiers ont travaillé sur le TP et que votre code n’est pas plagié. Un historique de commit plausible devrait contenir de nombreux petits commit, chacun avec un message de commit approprié. Faire juste quelques commit massives proche à la date limite pourrait
entraîner une déduction considérable.
            
