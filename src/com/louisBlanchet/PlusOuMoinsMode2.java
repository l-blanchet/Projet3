package com.louisBlanchet;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PlusOuMoinsMode2 {

    String signe1;
    int chiffreCourantTest;
    int chiffre;
    private String[] nbPropose = {"5", "5", "5", "5"};
    private String[] nbProposePrecedent;
    private String[] reponse = {"=", "=", "=", "="};
    private String[] nbMystereDecoupe = {"1", "1", "1", "1"};
    private int lenght = 4;
    private int compteur = 0;
    private int boucleAlgo = 4;


    public PlusOuMoinsMode2(int lenght) {
        this.lenght = lenght;
    }

    public String[] recupNbMystère() {
        int lenght = 4;
        int nbEssais = 5;
        int nbMystere;
        do {
            System.out.println("vous avez sélectionné le mode 2 veuillez proposer un nombre supérieur à 1000");
            Scanner sc = new Scanner(System.in);
            nbMystere = 0;
            try {
                nbMystere = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("veillez à ne rentrer que des chiffres !");
            }
        } while (nbMystere < 1000);
        String nbMystere1 = Integer.toString(nbMystere);

        for (int i = 0; i < nbMystere1.length(); i++) {
            String decoupage = nbMystere1.substring(i, i + 1);
            nbMystereDecoupe[i] = decoupage;
        }
        this.mode2();
        return nbMystereDecoupe;

    }

    private void mode2() {
        do {
            if (compteur == 0) {

                this.affichePropositionParDefaut();
                compteur = compteur + 1;
                // System.out.println(Arrays.toString(nbPropose));
                this.saisieUserReponse();
            }
            this.nbPropose();
            compteur = compteur + 1;

            this.saisieUserReponse();
            //todo verification de victoire /defaite /continuer
            this.verification();
            //todo si continuer relancer un tour si besoin
            //todo si victoire/défaite afficher le menu de fin de jeu

        } while (compteur <= 5);


    }

    private void affichePropositionParDefaut() {

        System.out.println(Arrays.toString(nbPropose));
    }

    private void saisieUserReponse() {
        boolean testrecup = true;
        String reponseNonDecoupe = null;
        System.out.println(Arrays.toString(reponse));
        System.out.println(Arrays.toString(nbPropose));
        System.out.println(Arrays.toString(nbMystereDecoupe));
        System.out.println(compteur);
        do {
            System.out.println("Veuillez rentrer des + et des - en vous basant sur la reponse de l ordinateur par rapport à la votre ");
            Scanner sc = new Scanner(System.in);

            try {
                reponseNonDecoupe = sc.next();
            } catch (InputMismatchException e) {
                System.out.println("veillez à ne rentrer que des plus ou moins !");
                testrecup = false;
            }
        } while (testrecup == false);

        for (int i = 0; i < reponseNonDecoupe.length(); i++) {
            String decoupage = reponseNonDecoupe.substring(i, i + 1);
            reponse[i] = decoupage;
        }
        chiffreCourantTest = 0;


        this.verification();
    }

    private void verification() {
        if (Arrays.equals(nbPropose, nbMystereDecoupe)) {
            System.out.println("vous avez perdu");

        } else if (compteur >= 5) {
            System.out.println("Vous avez gagné !");
        } else {
            this.mode2();
        }
        int selection;
        do {
            System.out.println("veuillez sélectionner dans quelle section voulez vous aller");
            System.out.println("1- Rejouer");
            System.out.println("2- Menu Principal");
            System.out.println("3- Quitter");

            Scanner sc = new Scanner(System.in);
            selection = 0;
            try {
                selection = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("veillez à ne rentrer que des chiffres !");
            }
            if (selection == 1) {
                recupNbMystère();
            } else if (selection == 2) {
                MainMenu main = new MainMenu();
                main.displayMenu();
            } else if (selection == 3) {
                System.out.println("fermeture du programme ");
                System.exit(0);
            }

        } while ((selection < 1) || (selection > 3));
    }

    public String[] nbPropose() {
        nbProposePrecedent = nbPropose;
        int test = lenght - 1;
        int boucletest = 0;


        for (int boucle = (test); boucle >= 0; boucle--) {
            int chiffreprec = Integer.parseInt(String.valueOf(nbProposePrecedent[boucletest]));

            signe1 = String.valueOf(reponse[boucletest]);
            boucletest = boucletest + 1;
            chiffre = proposechiffre(0, chiffreprec, signe1);


        }
        String chiffreString = Integer.toString(chiffre);
        for (int i = 0; i < chiffreString.length(); i++) {
            String decoupage = chiffreString.substring(i, i + 1);
            nbPropose[i] = decoupage;
        }
        boucleAlgo = 4;
        return nbPropose;


    }


    public int proposechiffre(int chiffreCourant, int chiffrePrecedent, String signe) {
        int valeurMax = 10;
        int valeurMin = 1;
        int resultat = 0;


        if (chiffrePrecedent == 3 && signe1.equals("+")) {
            valeurMax = 5;
        }

        if (chiffrePrecedent == 7 && signe1.equals("-")) {
            valeurMin = 5;
        }
        if (signe1.equals("=")) {
            resultat = chiffrePrecedent;
            boucleAlgo--;
        }

        if (signe1.equals("+")) {
            resultat = ((chiffrePrecedent + valeurMax) / 2);
            valeurMax = 9;
            boucleAlgo--;

        }
        if (signe1.equals("-")) {
            resultat = ((chiffrePrecedent + valeurMin) / 2);
            valeurMin = 1;
            boucleAlgo--;
        }

        int chiffreCourantPartiel = (int) (resultat * (Math.pow(10, boucleAlgo)));
        chiffreCourantTest = chiffreCourantPartiel + chiffreCourantTest;
        chiffreCourant = chiffreCourantTest;
        return chiffreCourant;

    }
}

