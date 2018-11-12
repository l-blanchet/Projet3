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
    String signe1;
    private int boucleAlgo =4;
    int chiffreCourantTest;

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
        this.mode2();
        return nbMystereDecoupe;

    }
    private void mode2(){
        do {
            //todo afficher la valeur par défaut [5,5,5,5]
            if (compteur == 0) {
                compteur = compteur+1;
                this.affichePropositionParDefaut();
                this.saisieUserReponse();
            }
            else {
                this.nbPropose();
            }
                //todo faire saisir à l'utilisateur les "+" et les "-"
                this.saisieUserReponse();
                //todo verification de victoire /defaite /continuer
                //todo si continuer relancer un tour si besoin
                //todo si victoire/défaite afficher le menu de fin de jeu

        }while (compteur<5);


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
        chiffreCourantTest = 0;
        System.out.println(Arrays.toString(reponse));
        System.out.println(Arrays.toString(nbPropose));
        System.out.println("test réussi");
        System.out.println(compteur);

        this.mode2();
    }

    private void affichePropositionParDefaut() {

        System.out.println(Arrays.toString(nbPropose));
    }
    int chiffre;

    public String[] nbPropose() {
        nbProposePrecedent = nbPropose;
        int test = lenght-1 ;
        int boucletest = 0;



            for (int boucle = (test); boucle >= 0; boucle--) {
                int chiffreprec = Integer.parseInt(String.valueOf(nbProposePrecedent[boucletest]));

                signe1 = String.valueOf(reponse[boucletest]);
                boucletest = boucletest +1;
                chiffre = proposechiffre(0, chiffreprec, signe1);
                System.out.println(chiffre);


            }
        String chiffreString = Integer.toString(chiffre);
        for (int i = 0; i < chiffreString.length(); i++) {
            String decoupage = chiffreString.substring(i, i + 1);
            nbPropose[i] = decoupage;
        }
        boucleAlgo = 4;
        return nbPropose ;


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
        if (signe1.equals("=")){
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

        int chiffreCourantPartiel = (int) (resultat*(Math.pow (10, boucleAlgo)));
        chiffreCourantTest = chiffreCourantPartiel + chiffreCourantTest;
        chiffreCourant = chiffreCourantTest;
        return chiffreCourant;

    }
}

