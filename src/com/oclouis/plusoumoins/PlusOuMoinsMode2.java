package com.oclouis.plusoumoins;


import com.oclouis.Config;
import com.oclouis.Game;
import com.oclouis.Mode2;
import com.oclouis.Result;
import org.apache.log4j.Logger;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import static java.lang.Math.pow;

/**
 * cette classe joue le plus ou moins en mode défenseur
 */

public class PlusOuMoinsMode2 extends Game implements Mode2 {
    private final Config configuration;
    private final boolean mode3;
    private String signeReponsePrecedente;
    private int chiffreProposeTransitoire;
    private int chiffre;
    private final Logger logger = Logger.getLogger(PlusOuMoinsMode2.class);
    private String[] nbPropose;
    private String[] reponse;
    private String[] nbMystereDecoupe;
    private int lenght;
    private int compteur;
    private int nbEssais;
    private int boucleAlgo;

    public PlusOuMoinsMode2(boolean mode3, Config config) {
        this.mode3 = mode3;
        this.configuration = config;
    }



    /**
     * méthode qui lance le jeu
     */
    public Result main() {
        Result jouerEncore;
        boolean check;
        do {
            logger.info("lancement d'un tour");
            check = false;
            jouerEncore = this.recupNbMystere();
        } while (check);
        return jouerEncore;
    }

    /**
     * méthode se lancant à chaque nouvelle partie met les valeurs principales à zéro et demande un nombre mystère
     *
     * @return retourne la sélection de l'utilisateur dans la méthode rejouer()
     */
    private Result recupNbMystere() {
        logger.info("mise à zéro des valeurs");
        lenght = configuration.getLength();
        boolean devMod = configuration.isDevMod();
        nbEssais = configuration.getNbEssai();

        nbPropose = new String[lenght];
        for (int i = 0; i < lenght; i++) {
            nbPropose[i] = "5";
        }
        reponse = new String[lenght];
        for (int i = 0; i < lenght; i++) {
            reponse[i] = "=";
        }
        nbMystereDecoupe = new String[lenght];
        for (int i = 0; i < lenght; i++) {
            nbMystereDecoupe[i] = "1";
        }

        boucleAlgo = 4;
        boolean verif = false;


        int nbMystere;
        int valeurMax = (int) pow(10, lenght - 1);
        do {
            logger.info("sélection du nombre mystère");
            System.out.println("vous avez sélectionné le mode 2 veuillez proposer un nombre supérieur à " + valeurMax);
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
        Result jouerEncore;
        jouerEncore = this.mode2();
        return jouerEncore;

    }

    /**
     * cette méthode est le coeur de cette classe c'est elle qui est chargée de lancer les méthodes pour faire fonctionner le jeu
     *
     * @return retourne la sélection de l'utilisateur dans la méthode rejouer()
     */
    public Result mode2() {


        Result jouerEncore;
        do {
            logger.info("lancement d'un tour");
            if (compteur == 0) {

                this.affichePropositionParDefaut();
            }
            this.nbPropose();
            if (!mode3) {
                compteur = compteur + 1;
            }
            this.saisieUserReponse();

            jouerEncore = this.verification();
            if (mode3) {
                return jouerEncore;
            }


        } while (jouerEncore == Result.REJOUER);
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
       // System.out.println( Arrays.toString(reponse));/todo ne pas oublier de voir l utilite de cette ligne
        System.out.println("Ceci est le nombre que l'ordinateur vous propose : " +Arrays.toString(nbPropose));
        System.out.println("Ceci est le nombre que vous avez proposé : " +Arrays.toString(this.nbMystereDecoupe));

        do {
            System.out.println("Veuillez rentrer des + et des - en vous basant sur la reponse de l ordinateur par rapport à la votre ");
            Scanner sc = new Scanner(System.in);

            try {
                reponseNonDecoupe = sc.next();
            } catch (InputMismatchException e) {
                System.out.println("veillez à ne rentrer que des plus ou moins !");
                testrecup = false;
            }
        } while (!testrecup);

        for (int i = 0; i < reponseNonDecoupe.length(); i++) {
            String decoupage = reponseNonDecoupe.substring(i, i + 1);
            reponse[i] = decoupage;
        }
        chiffreProposeTransitoire = 0;

    }

    /**
     * cette méthode vérifie la proposition utilisateur et la proposition de l'ordinateur pour vérifier un éventuelle égalité
     *
     * @return retourne la sélection de l'utilisateur dans la méthode rejouer()
     */
    private Result verification() {
        logger.info("comparaison proposition ordinateur et nombre mystère");
        if (Arrays.equals(nbPropose, nbMystereDecoupe)) {

            System.out.println("L'ordinateur a trouvé en " + compteur + " coups , Vous avez perdu !");
            Result jouerEncore;
            jouerEncore = rejouer();
            return jouerEncore;


        } else if (compteur >= nbEssais) {
            System.out.println("Vous avez gagné !");
            Result jouerEncore;
            jouerEncore = rejouer();
            return jouerEncore;
        }
        return Result.REJOUER;
    }

    /**
     * cette méthode découpe le résultat obtenu de l'ordinateur et la réponse donné par l'utilisateur pour proposer de nouvelles valeurs en adéquation avec ces deux résultats
     *
     * return retourne la nouvelle valeur que l'ordinateur proposera
     */
    private void nbPropose() {
        logger.info("analyse de la réponse utilisateur et proposition d'une réponse adéquate");
        String[] nbProposePrecedent = nbPropose;
        int compteurTableau = 0;


        for (int boucle = lenght-1; boucle >= 0; boucle--) {
            int chiffreprec = Integer.parseInt(String.valueOf(nbProposePrecedent[compteurTableau]));

            signeReponsePrecedente = String.valueOf(reponse[compteurTableau]);
            compteurTableau = compteurTableau + 1;
            chiffre = proposechiffre(chiffreprec, signeReponsePrecedente);


        }
        String chiffreString = Integer.toString(chiffre);
        for (int i = 0; i < chiffreString.length(); i++) {
            String decoupage = chiffreString.substring(i, i + 1);
            nbPropose[i] = decoupage;
        }
        boucleAlgo = lenght;


    }

    /**
     * cette méthode sert à proposer une nouvelle valeur à l'aide de deux valeurs : la proposition précedente et la réponse de l'utilisateur (en "+", "-" et "=" )
     *
     * @param chiffrePrecedent le chiffre proposé au tour précédent
     * @param signe            le signe donné par l'utilisateur
     * @return le chiffre déduit par l'algorithme
     */
    private int proposechiffre(int chiffrePrecedent, String signe) {
        logger.info("analyse de la réponse utilisateur et proposition d'une réponse adéquate chiffre par chiffre ");
        int valeurMax = 10;
        int valeurMin = 1;
        int resultat = 0;


        if (chiffrePrecedent == 3 && signeReponsePrecedente.equals("+")) {
            valeurMax = 5;
        }

        if (chiffrePrecedent == 7 && signeReponsePrecedente.equals("-")) {
            valeurMin = 5;
        }
        if (signeReponsePrecedente.equals("=")) {
            resultat = chiffrePrecedent;
            boucleAlgo--;
        }

        if (signeReponsePrecedente.equals("+")) {
            resultat = ((chiffrePrecedent + valeurMax) / 2);
            boucleAlgo--;

        }
        if (signeReponsePrecedente.equals("-")) {
            resultat = ((chiffrePrecedent + valeurMin) / 2);
            boucleAlgo--;
        }

        int chiffreCourantPartiel = (int) (resultat * (pow(10, boucleAlgo)));
        chiffreProposeTransitoire = chiffreCourantPartiel + chiffreProposeTransitoire;
        int chiffreCourant = chiffreProposeTransitoire;
        return chiffreCourant;

    }

    @Override
    protected void verification(String proposition, String[] propositionDecoupe) {
        Boolean.parseBoolean(null);
    }
}

