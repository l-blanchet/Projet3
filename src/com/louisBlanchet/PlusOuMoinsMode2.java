package com.louisBlanchet;


import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.Math.pow;


public class PlusOuMoinsMode2 {

    String signe1;
    int chiffreCourantTest;
    int chiffre;
    private String[] nbPropose;
    private String[] nbProposePrecedent;
    private String[] reponse;
    private String[] nbMystereDecoupe;
    private int lenght;
    private int compteur;
    private int boucleAlgo;
    private boolean verif;
    boolean devMod;


    public PlusOuMoinsMode2(int lenght) {
        this.lenght = lenght;
    }

    public void main() {
        boolean jouerEncore;
        do {
            jouerEncore = this.recupNbMystère();
        } while (jouerEncore);
    }

    public boolean recupNbMystère() {
        Config configuration = new Config();
        lenght = configuration.getLength();
        devMod = configuration.isDevMod();

        nbPropose = new String[lenght];
        for (int i =0 ; i < lenght; i++){
            nbPropose[i]="5";
        }
        reponse = new String[lenght];
        for (int i =0 ; i < lenght; i++){
            reponse[i]="=";
        }
        nbMystereDecoupe = new String[lenght];
        for (int i =0 ; i < lenght; i++){
            nbMystereDecoupe[i]="1";
        }
        System.out.println(Arrays.toString(nbMystereDecoupe));
        compteur = 0;
        boucleAlgo = 4;
        verif = false;


        int nbMystere;
        int valeurMax = (int) pow(10,lenght-1);
        do {

            System.out.println("vous avez sélectionné le mode 2 veuillez proposer un nombre supérieur à " +valeurMax);
            Scanner sc = new Scanner(System.in);
            nbMystere = 0;
            try {
                nbMystere = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("veillez à ne rentrer que des chiffres !");
            }
        } while (nbMystere < valeurMax);
        String nbMystere1 = Integer.toString(nbMystere);

        for (int i = 0; i < nbMystere1.length(); i++) {
            String decoupage = nbMystere1.substring(i, i + 1);
            nbMystereDecoupe[i] = decoupage;
        }
        boolean jouerEncore;
        jouerEncore = this.mode2();
        return jouerEncore;

    }

    private boolean mode2() {
        boolean jouerEncore = false;
        do {
            if (compteur == 0) {

                this.affichePropositionParDefaut();
            }
            this.nbPropose();
            compteur = compteur + 1;

            this.saisieUserReponse();

            jouerEncore = this.verification();


            //todo verification doit retourner un true ou false pour dire s'il a trouve soit il doit relancer un tour
        } while (jouerEncore == false);//todo sortir s'il a trouvé
        jouerEncore = this.rejouer();
        return jouerEncore;


    }

    private void affichePropositionParDefaut() {

        System.out.println(Arrays.toString(nbPropose));
    }

    private void saisieUserReponse() {
        boolean testrecup = true;
        String reponseNonDecoupe = null;
        System.out.println(Arrays.toString(reponse));
        System.out.println(Arrays.toString(nbPropose));
        System.out.println(Arrays.toString(this.nbMystereDecoupe));

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

    }

    private boolean verification() {
        //todo retourner true s il a trouve et false s'il n a pas trouvé
        if (Arrays.equals(nbPropose, nbMystereDecoupe)) {

            System.out.println("L'ordinateur a trouvé en " + compteur + " coups , Vous avez perdu !");
            return true;


        } else if (compteur >= 5) {
            System.out.println("Vous avez gagné !");
            return true ;
        }
        return false;
    }

    private boolean rejouer() {
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
                return true;
            } else if (selection == 2) {
                return false;
            } else if (selection == 3) {
                System.out.println("fermeture du programme ");
                System.exit(0);
            }

        } while ((selection < 1) || (selection > 3));
        return false;
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
        boucleAlgo = lenght;
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
            valeurMax = 10;
            boucleAlgo--;

        }
        if (signe1.equals("-")) {
            resultat = ((chiffrePrecedent + valeurMin) / 2);
            valeurMin = 1;
            boucleAlgo--;
        }

        int chiffreCourantPartiel = (int) (resultat * (pow(10, boucleAlgo)));
        chiffreCourantTest = chiffreCourantPartiel + chiffreCourantTest;
        chiffreCourant = chiffreCourantTest;
        return chiffreCourant;

    }
}

