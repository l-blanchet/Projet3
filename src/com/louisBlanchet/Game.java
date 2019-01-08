package com.louisBlanchet;

import org.apache.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.Math.pow;
import static java.lang.Math.random;

/**
 * Classe créant un nombre aléatoire
 */
public class Game {
    int rejouer = 0;
    protected Logger logger = Logger.getLogger(Game.class);

    public String[] getRandomized(int lenght ) {
        /**
         * méthode générant un nombre aéatoire en utilisant la fonction random
         */
        double d = random();
        d *= pow(10, lenght);
        int n = (int) d;
        String nbMystere;
        nbMystere = Integer.toString(n);


        while (nbMystere.length() < lenght) {
            double random = random();
            random = random * 10;
            int random1 = (int) random;
            nbMystere = (+random1 + nbMystere);
        }

        String[] nbMystereDecoupe = new String[nbMystere.length()];

        for (int compteur = 0; compteur < nbMystere.length(); compteur++) {
            String decoupage = nbMystere.substring(compteur, compteur + 1);
            nbMystereDecoupe[compteur] = decoupage;
        }
        return nbMystereDecoupe;
    }

    /**
     * cette méthode affiche le menu de sélection pour rejouer, revenir au menu principal ou quitter l'application
     * @return retourne le choix de l'utilisateur pour savoir s'il veut rejouer ou pas
     */

    protected boolean rejouer() {
        Scanner sc;

        int selection;
        do {
            System.out.println("veuillez sélectionner dans quelle section voulez vous aller");
            System.out.println("1- Rejouer");
            System.out.println("2- Menu Principal");
            System.out.println("3- Quitter");

            sc = new Scanner(System.in);
            selection = 0;
            try {
                selection = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("veillez à ne rentrer que des chiffres !");
            }
            if (selection == 1) {
                logger.info("relancement du jeu");
                rejouer = rejouer +1 ;
                return true;
            } else if (selection == 2) {
                logger.info("retour au menu principal");
                return false;
            } else if (selection == 3) {
                logger.info("fermeture du programme");
                System.out.println("fermeture du programme ");
                System.exit(0);
            }

            return false;

        } while ((selection < 1) || (selection > 3));
    }
    public int getRejouer() {
        return rejouer;
    }
}
