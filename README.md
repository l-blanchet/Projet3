# projet3

Ce Projet est réalisé dans le cadre de la formation OpenClassrooms "Développeur D'application Java"  
Ce projet consiste en deux jeux : le plus ou moins et le Mastermind  
Ces deux jeux ont tout deux trois mode de jeu  
Mode Challenger : L'utilisateur doit trouver une combinaison sélectionné par l'ordinateur  
Mode Défenseur : L'ordinateur essaye de deviner la combinaison sélectionné par l'utilisateur  
Mode Duel : l'utilisateur et l'ordinateur doivent essayer de deviner la combinaison de l'autre en premier  


LANCER L'APPLICATION  
 Pour lancer l'application vous devez l'ouvrir de préference dans IntelliJ   
 En haut à gauche cliquer sur "File" puis "New" ensuite sélectionner "Project from existing resources"   
 Sélectionner dans votre ordinateur le fichier contenant ce Projet 3  
 Ensuite il faudra aller build le projet sous Java 8 et ensuite lancer l'application 
 
 MODE DEVELOPPEUR  
 Pour lancer en mode développeur il y a deux manières  
 1. changer le booléen en "true " dans le fichier config.properties  
 2. rajouter dans les arguments du build "dev" 
 
 FICHIER DE CONFIGURATION  
 Dans le fichier de configuration il y a plusieurs entrées :  
  1. lenght : la longueur qui sera utilisé pour les nombres à deviner et à faire deviner
  2. DevMod : Pour utiliser le mode développeur, True pour l'utiliser et False pour le désactiver  
  3. nbEssais : le nombre d'essais qu'auront l'utilisateur et l'ordinteur
  4. nbCouleurs : Utilisé dans le mastermind pour détermenir combien de chiffre il y aura (à 10 les chiffres sélentionnés seront de 0 à 9 alors qu'à 4 les chiffres séectionnés seront de 0 à 3)  
   