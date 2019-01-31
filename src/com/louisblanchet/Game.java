package com.louisblanchet;

import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.Math.pow;
import static java.lang.Math.random;

/**
 * Classe créant un nombre aléatoire
 */
public abstract class Game {
    protected String[] nbMystereDecoupe;
    protected String[] nbMysteredecoupe1;
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

         nbMystereDecoupe = new String[nbMystere.length()];

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

    protected Result rejouer() {
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
                return Result.RELANCER ;

            } else if (selection == 2) {
                logger.info("retour au menu principal");
                return Result.QUITTER;
            } else if (selection == 3) {
                logger.info("fermeture du programme");
                System.out.println("fermeture du programme ");
                System.exit(0);
            }



        } while ((selection < 1) || (selection > 3));
        return  null;
    }
    public int getRejouer() {
        return rejouer;
    }

    /**
     * cette méthode sert à decouper la proposition et à la transformer en String[]
     *
     * @param proposition : obligatoire pour la découper
     * @return :retourne la proposition de l'utilisateur en String[]
     */
    protected String[] decoupage(String proposition) {
        logger.info("découpage de la proposition de l'utilisateur");
        String[] propositionDecoupe = new String[proposition.length()];

        for (int compteurtest = 0; compteurtest < proposition.length(); compteurtest++) {
            String decoupage = proposition.substring(compteurtest, compteurtest + 1);
            propositionDecoupe[compteurtest] = decoupage;
        }
        return propositionDecoupe;
    }

    /**
     * cette méthode est le déclencheur du tour il appelle toutes les fonctions utiles pour le tour
     *
     * @return : retourne la proposition de l'utilisateur en String[]
     */
    protected String[] essai() {
        String proposition = proposition();
        String[] propositionDecoupe = decoupage(proposition);

        verification(proposition, propositionDecoupe);
        return propositionDecoupe;
    }

    protected abstract boolean verification(String proposition, String[] propositionDecoupe);

    /**
     * cette méthode demande à l'utilisateur d'entrer une proposition
     *
     * @return : retourne la proposition saisi par l'utilisateur
     */
    private String proposition() {
        boolean checkeur = false;
        String proposition = null;
        int essai;


        while (!checkeur) {
            Scanner sc = new Scanner(System.in);
            System.out.println("proposition:");

            try {
                essai = sc.nextInt();
                proposition = Integer.toString(essai);

                if (proposition.length() < nbMysteredecoupe1.length || proposition.length() > nbMysteredecoupe1.length) {
                    System.out.println("vous n'avez pas rentré le bon nombre de chiffres réesayez");
                    System.out.println(Arrays.toString(nbMystereDecoupe));
                    logger.debug("la proposition ne comportait pas le bon nombre de chiffre");
                } else {
                    checkeur = true;
                }
            } catch (InputMismatchException e) {
                logger.debug("l'utilisateur n'a pas rentré de chiffre");
                System.out.println("veillez à ne rentrer que des chiffres !");
                checkeur = false;

            }

        }
        return proposition;

    }
}
