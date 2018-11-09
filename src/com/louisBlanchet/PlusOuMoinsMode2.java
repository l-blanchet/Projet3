package com.louisBlanchet;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PlusOuMoinsMode2 {

    private String[] nbPropose = {"5","5","5","5"};
    private String[] nbProposePrecedent;
    private String[] reponse = {"=","=","=","="};
    private int lenght = 4;
    private int compteur =0;

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
        String[] nbMystereDecoupe = new String[nbMystere1.length()];

        for (int i = 0; i < nbMystere1.length(); i++) {
            String decoupage = nbMystere1.substring(i, i + 1);
            nbMystereDecoupe[i] = decoupage;
        }
        System.out.println(Arrays.toString(nbMystereDecoupe));

        for ( compteur = 0; compteur < 5; compteur++) {
            //todo afficher la valeur par défaut [5,5,5,5]
            if (compteur == 0) {
                this.affichePropositionParDefaut();
            }
            this.nbPropose();
            //todo faire saisir à l'utilisateur les "+" et les "-"
            this.saisieUserReponse();
            //todo verification de victoire /defaite /continuer
            //todo si continuer relancer un tour si besoin
            //todo si victoire/défaite afficher le menu de fin de jeu

        }
        return nbMystereDecoupe;

    }

    private void saisieUserReponse() {
        boolean testrecup = true;
        String reponseNonDecoupe = null;
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
        System.out.println(Arrays.toString(reponse));
        System.out.println("test réussi");
    }

    private void affichePropositionParDefaut() {

        System.out.println(Arrays.toString(nbPropose));

    }


    public String[] nbPropose() {
        nbProposePrecedent = nbPropose;


            for (int boucle = lenght - 1; boucle == 0; boucle--) {
                int chiffreprec = Integer.parseInt(String.valueOf(nbProposePrecedent[boucle]));
                String signe1 = String.valueOf(reponse[boucle]);
                int chiffre = proposechiffre(0, chiffreprec, signe1);
                //todo mettre un return nbproposé

            }
        return null ;//todo à terminer

    }




    public int proposechiffre(int chiffreCourant, int chiffrePrecedent, String signe) {
        int valeurMax = 9;
        int valeurMin = 1;
        int resultat = 0;

        if (chiffrePrecedent == 3 && signe.equals("+")) {
            valeurMax = 5;
        }

        if (chiffrePrecedent == 7 && signe.equals("-")) {
            valeurMin = 5;
        }

        if (signe.equals("+")) {
            resultat = ((chiffrePrecedent + valeurMax) / 2);
            valeurMax = 9;

        }
        if (signe.equals("-")) {
            resultat = ((chiffrePrecedent + valeurMin) / 2);
            valeurMin = 1;
        }
        return resultat;

    }
}

