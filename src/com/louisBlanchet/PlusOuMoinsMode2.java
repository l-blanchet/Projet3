package com.louisBlanchet;


import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.Math.pow;

/**
 * cette classe joue le plus ou moins en mode défenseur
 */

public class PlusOuMoinsMode2 extends Game {
    boolean mode3 ;
    private Logger logger = Logger.getLogger(PlusOuMoinsMode2.class);
    int compteurplusOuMoinsMode1;
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

    public PlusOuMoinsMode2(boolean mode3) {
        this.mode3 = mode3;
    }

    public PlusOuMoinsMode2(int compteurPlusOuMoinsMode1) {
        this.compteurplusOuMoinsMode1 = compteurPlusOuMoinsMode1;
    }

    /**
     * méthode qui lance le jeu
     */
    public void main() {
        boolean jouerEncore;
        do {
            logger.info("lancement d'un tour");
            jouerEncore = this.recupNbMystere();
        } while (jouerEncore == false );
    }

    /**
     * méthode se lancant à chaque nouvelle partie met les valeurs principales à zéro et demande un nombre mystère
     * @return retourne la sélection de l'utilisateur dans la méthode rejouer()
     */
    public boolean recupNbMystere() {
        logger.info("mise à zéro des valeurs");
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
        compteur = 0;
        boucleAlgo = 4;
        verif = false;


        int nbMystere;
        int valeurMax = (int) pow(10,lenght-1);
        do {
            logger.info("sélectio du nombre mystère");
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

    /**
     * cette méthode est le coeur de cette classe c'est elle qui est chargée de lancer les méthodes pour faire fonctionner le jeu
     * @return retourne la sélection de l'utilisateur dans la méthode rejouer()
     */
    public boolean mode2() {


        boolean jouerEncore = true;
        do {
            logger.info("lancement d'un tour");
            if (compteur == 0) {

                this.affichePropositionParDefaut();
            }
            this.nbPropose();
            if(mode3 == false) {
                compteur = compteur + 1;
            }
            this.saisieUserReponse();

            jouerEncore = this.verification();
            if (mode3 == true){
                return jouerEncore;
            }


        } while (jouerEncore == true);
        jouerEncore = this.rejouer();
        return jouerEncore;


    }

    /**
     * affiche la proposition par défaut lors du premier tour afin que dans les prochains tours il puisse s'appuyer sur des valeurs
     */
    private void affichePropositionParDefaut() {

        System.out.println(Arrays.toString(nbPropose));
    }

    /**
     * cette méthode sert à demander à l'utilisateur une réponse en fonction de ce que l'ordinateur a proposé comme chiffre
     */
    private void saisieUserReponse() {
        logger.info("récupératon de la réponse utilisateur ");
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

    /**
     * cette méthode vérifie la proposition utilisateur et la proposition de l'ordinateur pour vérifier un éventuelle égalité
     * @return retourne la sélection de l'utilisateur dans la méthode rejouer()
     */
    private boolean verification() {
        logger.info("comparaison proposition ordinateur et nombre mystère");
        if (Arrays.equals(nbPropose, nbMystereDecoupe)) {

            System.out.println("L'ordinateur a trouvé en " + compteur + " coups , Vous avez perdu !");
            compteurplusOuMoinsMode1 = 0;
            boolean jouerEncore;
            jouerEncore = rejouer();
            return jouerEncore;


        } else if (compteur >= 5) {
            System.out.println("Vous avez gagné !");
            compteurplusOuMoinsMode1 = 0;
            boolean jouerEncore;
            jouerEncore = rejouer();
            return jouerEncore;
        }
        return true;
    }

    /**
     * cette méthode découpe le résultat obtenu de l'ordinateur et la réponse donné par l'utilisateur pour proposer de nouvelles valeurs en adéquation avec ces deux résultats
     * @return retourne la nouvelle valeur que l'ordinateur proposera
     */
    public String[] nbPropose() {
        logger.info("analyse de la réponse utilisateur et proposition d'une réponse adéquate");
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

    /**
     * cette méthode sert à proposer une nouvelle valeur à l'aide de deux valeurs : la proposition précedente et la réponse de l'utilisateur (en "+", "-" et "=" )
     * @param chiffreCourant le chiffre qui sera proposé à l'issue de cette méthode
     * @param chiffrePrecedent le chiffre proposé au tour précédent
     * @param signe le signe donné par l'utilisateur
     * @return le chiffre déduit par l'algorithme
     */
    public int proposechiffre(int chiffreCourant, int chiffrePrecedent, String signe) {
        logger.info("analyse de la réponse utilisateur et proposition d'une réponse adéquate chiffre par chiffre ");
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

