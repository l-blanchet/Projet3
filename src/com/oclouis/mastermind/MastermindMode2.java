package com.oclouis.mastermind;

import com.oclouis.Config;
import com.oclouis.Game;
import com.oclouis.Result;
import org.apache.log4j.Logger;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

import static java.lang.Math.pow;

/**
 * Classe utilisé pour le mastermind avec l'ordiateur qui doit deviner la proposition de l'utilisateur
 */

public class MastermindMode2 extends Game implements com.oclouis.Mode2 {

    private final boolean mode3;
    private final HashSet solutionPossible = new HashSet();
    private String nbMystere;
    private String nbPropose;
    private final Logger logger = Logger.getLogger(MastermindMode2.class);
    private final int lenght;
    private final int nbCouleur;
    private int compteur ;
    private int bienPlaces;
    private int bienEtMalPlaces;
    private final int nbEssais;

    /**
     * méthode permattant de récuperer les valeurs présentes dans le fichier de configuration
     * @param mode3 permet de savoir si on se trouve en mode hybride
     * @param config permet de recuperer les valeur du config.properties sans avoir à instancier la classe pour chaque classe l'utilisant
     */
    public MastermindMode2(boolean mode3, Config config) {
        this.mode3 = mode3;
        compteur = 0;
        Config configuration = config;
        nbCouleur = configuration.getNbCouleur();
        lenght = configuration.getLength();
        nbEssais = configuration.getNbEssai();
    }

    /**
     * "Centre de contrôle de la classe, 'est elle qui appelle les méthodes durant le jeu
     * @return permet de retourner si le joueur veux arreter de jouer et retourner au menu
     */
    @Override
    public Result main() {
        String[] propositionDecoupe = null;
        Result jouerEncore;
        do {
            if (compteur == 0) {
                selectionNbMystere();
            }
            proposition();
            verification(nbPropose, propositionDecoupe);
            jouerEncore = victoireVerif();
            if (bienEtMalPlaces > 0) {
                eliminationProposition();
            }
            if (mode3) {
                return jouerEncore;
            }


        } while (jouerEncore == Result.REJOUER || jouerEncore == Result.RELANCER);
        return Result.REJOUER;
    }

    /**
     * Méthode permettant à l'utilisateur de sélectionner son nombre mystère
     */
    private void selectionNbMystere() {
        long valeurMax = (long) (pow(10, lenght) - 1);
        long valeurMin = (long) (pow(10, lenght - 1));
        int nbMystereRentre;
        do {
            logger.info("sélection du nombre mystère");
            System.out.println("vous avez sélectionné le mode 2 veuillez proposer un nombre compris entre " + valeurMin + " et " + valeurMax);
            Scanner sc = new Scanner(System.in);
            nbMystereRentre = 0;
            try {
                nbMystereRentre = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("veillez à ne rentrer que des chiffres !");
            }
        } while (valeurMin >= nbMystereRentre && nbMystereRentre > valeurMax);
        nbMystere = String.valueOf(nbMystereRentre);
        generateurDeHash();
    }

    /**
     * Cette méthode permet de créer la hash set en fonction du nombres de possibilités des chiffres et de la longueur de la combinaison
     * il peut donc génerer en base N toutes les hash set
     * à savoir que ma JVM n'étant pas capable de génerer une hash set quand la longueur est supérieure à 8 j'ai installé une limitation
     * qui fait que pour les longueurs supérieures à 8 je ne génère qu'un million de chiffre
     */
    private void generateurDeHash() {


        if (lenght <= 7) {
            for (int i = (int) pow(nbCouleur, (lenght - 1)); i < (pow(nbCouleur, lenght) - 1); i++) {

                String convert = Integer.toString(i, nbCouleur);
                solutionPossible.add(convert);
            }
            int longueurHash;
            longueurHash = solutionPossible.size();
            logger.debug("longueur de la hash set" + longueurHash);
        }
        if (lenght >= 8) {
            int min = (int) pow(nbCouleur, (lenght - 1));
            int max = (int) (min + pow(nbCouleur, 7));
            for (int i = min; i < max; i++) {

                String convert = Integer.toString(i, nbCouleur);
                solutionPossible.add(convert);
            }
            int LongueurHash;
            LongueurHash = solutionPossible.size();
            logger.debug("longueur de la hash set" + LongueurHash);
        }

    }

    /**
     * Cette méthode permet de sélectionner le nombre aléatoirement dans la hash set
     */
    private void proposition() {

        int Min = 1;
        int Max = solutionPossible.size();
        int nbAleatoire = Min + (int) (Math.random() * ((Max - Min) + 1));

        Iterator<String> iterateur = solutionPossible.iterator();
        for (int i = 0; i <= nbAleatoire; i++) {
            boolean check = iterateur.hasNext();
            if (check) {
                nbPropose = iterateur.next();
            } else {
                for (int z = 1; z < pow(10, lenght) - 1; z++) {
                    if (solutionPossible.contains(z)) nbPropose = String.valueOf(z);
                }
            }

        }

        logger.debug("nb Proposé" + nbPropose);
        logger.debug("nb Mystere" + nbMystere);
    }

    /**
     * Méthode demandant à l'utilisateur de rentrer le nombre de bien placés et de mal placs suite à la proposition de l'ordinateur
     */
    protected void verification(String proposition, String[] propositionDecoupe) {
        System.out.println("le nombre proposé par l'ordinateur est : " + nbPropose);
        System.out.println("Pour rappel le nombre que vous avez proposé est : " + nbMystere);
        Scanner scbp = new Scanner(System.in);
        Scanner scmp = new Scanner(System.in);
        do {
            System.out.println("veuillez entrez le nombre de chiffres bien placés");
            bienPlaces = 0;
            try {
                bienPlaces = scbp.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("veillez à ne rentrer que des chiffres !");
            }
        } while (bienPlaces < 0 || bienPlaces > lenght);

        int malPlaces;
        do {
            System.out.println("veuillez entrez le nombre de chiffres mal placés");
            malPlaces = 0;
            try {
                malPlaces = scmp.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("veillez à ne rentrer que des chiffres !");
            }
        } while (malPlaces < 0 || malPlaces > lenght);
        bienEtMalPlaces = bienPlaces + malPlaces;
    }

    /**
     * Méthode vérifiant les conditions de victoire et de défaite
     * @return retourne la sélection de l'utilisateur suite à l'affichage du menu de fin de jeu
     */
    private Result victoireVerif() {
        compteur = compteur + 1;
        if (bienPlaces == lenght) {
            System.out.println("Vous avez perdu en " + compteur + " coups ");
            compteur = 0;
            bienEtMalPlaces = 0;
            Result jouerEncore;
            jouerEncore = rejouer();
            return jouerEncore;

        }
        if (compteur == nbEssais && !mode3) {
            System.out.println("Vous avez gagné");
            compteur = 0;
            bienEtMalPlaces = 0;
            Result jouerEncore;
            jouerEncore = rejouer();
            return jouerEncore;
        }
        return Result.REJOUER;
    }

    /**
     * Cette Méthode est l'algorithme principale de cette classe
     * dans un premier temps elle vérifie le nombre de chiffres bien placés dans la proposition
     * Puis elle revérifie le nombre de bien placé mais pour les enlever pour creer une nouvelle String[]
     * avec cette nouvelle String[] elle vérifie le nombre de mal placés
     * ensuite elle additionne le nombre de bien placés et de mal placés et compare à la réponse de l'utilisateeur (de bien et mal placés )
     * si le chiffre est supérieur ou égal alors elle garde la valeur sinon elle la supprimme
     * pour la longueur supérieure à 8 lors du premier tour si les valeur de bien et mal placés sont égales ou supérieures alors si le nombre n'est pas dans la hash set il l'ajoute sinon il l'ignore
     */
    private void eliminationProposition() {


        String nbProposeBoucle;
        String nbProposeDecoupet[] = new String[lenght];
        String nbMysteredecoupet[] = new String[lenght];
        int compteurSpe = 0;
        int compteurNbPropose = 0;
        int compteurproposition = 0;
        int bienPlace;
        String essai;
        boolean existence;
        int malPlace;
        int bienEtMalPlace;
        for (int compteur = 0; compteur < nbMystere.length(); compteur++) {
            String decoupage = nbMystere.substring(compteur, compteur + 1);
            nbMysteredecoupet[compteur] = decoupage;
        }
        for (long i = (long) pow(10, (lenght - 1)); i < (pow(10, lenght) - 1); i++) {
            essai = String.valueOf(i);
            logger.debug(essai);
            bienPlace = 0;
            malPlace = 0;
            existence = solutionPossible.contains(essai);
            if (essai.equals(nbPropose)) {
                solutionPossible.remove(essai);
                existence = false;
            }
            if (existence || (lenght >= 8 && compteur == 1 && !existence)) {
                nbProposeBoucle = String.valueOf(i);
                for (int compteur = 0; compteur < nbProposeBoucle.length(); compteur++) {
                    String decoupage = nbProposeBoucle.substring(compteur, compteur + 1);
                    nbProposeDecoupet[compteur] = decoupage;
                }
                for (int verificateur = 0; verificateur < nbMysteredecoupet.length; verificateur++) {

                    String nbTest = nbMysteredecoupet[verificateur];
                    int nbDecoupe = Integer.parseInt(nbTest);
                    String proposition1;
                    proposition1 = nbProposeDecoupet[verificateur];
                    int propositioncut = Integer.parseInt(proposition1);

                    if (propositioncut == nbDecoupe) {
                        bienPlace = bienPlace + 1;
                    }


                }
                if (bienPlace >= bienPlaces && bienPlace <= lenght) {


                    int nvlenght = lenght - bienPlace;
                    String nvNbMystereDecoupe[] = new String[nvlenght];
                    String nvPropositionDecoupe[] = new String[nvlenght];
                    for (int verificateur = 0; verificateur < nbMysteredecoupet.length; verificateur++) {

                        String nbTest = nbMysteredecoupet[verificateur];
                        int nbDecoupe = Integer.parseInt(nbTest);
                        String proposition1;
                        proposition1 = nbProposeDecoupet[verificateur];
                        int propositioncut = Integer.parseInt(proposition1);

                        if (propositioncut != nbDecoupe) {
                            try {
                                nvNbMystereDecoupe[compteurNbPropose] = String.valueOf(nbDecoupe);
                            } catch (java.lang.ArrayIndexOutOfBoundsException e) {
                                verificateur = nbMysteredecoupet.length;
                            }
                            try {
                                nvPropositionDecoupe[compteurproposition] = String.valueOf(propositioncut);

                            } catch (java.lang.ArrayIndexOutOfBoundsException e) {
                                verificateur = nbMysteredecoupet.length;
                            }
                            compteurNbPropose = compteurNbPropose + 1;
                            compteurproposition = compteurproposition + 1;

                        }


                    }
                    compteurNbPropose = 0;
                    compteurproposition = 0;
                    nvlenght = 0;
                    int nvllePropositionDecoupe;
                    int nvNbMystDecoupe;

                    for (int compteur = 0; compteur < nvNbMystereDecoupe.length; compteur++) {
                        nvllePropositionDecoupe = Integer.parseInt(nvPropositionDecoupe[compteur]);
                        int futureLongueur = 0;
                        do {
                            nvNbMystDecoupe = Integer.parseInt(nvNbMystereDecoupe[compteurSpe]);
                            if (nvllePropositionDecoupe == nvNbMystDecoupe) {
                                malPlace++;
                                futureLongueur = nvlenght;
                            } else {
                                compteurSpe++;
                                futureLongueur++;
                            }
                        } while (futureLongueur < nvlenght);
                        compteurSpe = 0;
                    }
                    bienEtMalPlace = bienPlace + malPlace;
                    if (bienEtMalPlace < bienEtMalPlaces && existence) {
                        solutionPossible.remove(essai);
                    }
                    if (bienEtMalPlace >= bienEtMalPlaces && !existence) {
                        solutionPossible.add(essai);
                    }


                } else {
                    solutionPossible.remove(essai);
                }

            }

        }
        boolean verif = solutionPossible.contains(nbMystere);
        logger.debug("verification si le nombre mystere est toujours dans l hash set " + verif);
        logger.debug("nombre de solutions dans la hash set " + solutionPossible.size());

    }
}
