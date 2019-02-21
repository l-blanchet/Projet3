package com.oclouis.plusoumoins;

import com.oclouis.Config;
import com.oclouis.Game;
import com.oclouis.Mode1;
import com.oclouis.Result;
import org.apache.log4j.Logger;

import java.util.Arrays;

/**
 * cette classe joue le plus ou moins en mode attaquant
 */
public class PlusOuMoinsMode1 extends Game implements Mode1 {
    private final Config config;
    private final Logger logger = Logger.getLogger(PlusOuMoinsMode1.class);
    private int compteur = 0;
    private int lenght;
    private boolean devMod;
    private final boolean mode3;
    private int nbEssais;

    public PlusOuMoinsMode1(boolean mode3, Config config) {
        this.mode3 = mode3;
        this.config = config;
    }

    /**
     * méthode servant pour récuperer les valeurs présentes dans le fichier de configuration et relancer une partie
     *
     * @return : retourne la valeur obtenue dans messageVictoire
     */
    public Result initialisation() {
        lenght = config.getLength();
        devMod = config.isDevMod();
        nbEssais = config.getNbEssai();

        Result jouerEncore;
        do {
            jouerEncore = this.mode1();
            logger.info("lancement du tour " + compteur);
        } while (jouerEncore == Result.REJOUER);
        return jouerEncore;
    }

    /**
     * Méthode principale de cette classe c'est la méthode chargée de relancer chaque tour et de lancer le Game quand le besoin est
     *
     * @return : retourne la valeur obtenue dans messageVictoire
     */
    private Result mode1() {
        PlusOuMoinsMode2 modeHybride = null;
        Result jouerEncore = Result.REJOUER;
        logger.info("lancement du jeu ou relance d'un tour");
        if (mode3) {
            modeHybride = new PlusOuMoinsMode2(mode3, config);
            nbEssais = 99;
        }
        if (!mode3) {
            System.out.println("Vous avez sélectionné le mode 1, vous avez " + nbEssais + " essais, bonne chance !");
        }

        do {
            if ((compteur == 0 && jouerEncore == Result.RELANCER) || (jouerEncore == Result.REJOUER && compteur == 0)) {
                randomizer();
            }
            jouerEncore = this.essais();
            if (mode3 && 1 == compteur) {
                jouerEncore = modeHybride.main();
            }
            if (mode3 && compteur > 1) {

                jouerEncore = modeHybride.mode2();
            }
            if (jouerEncore == Result.RELANCER) {
                compteur = 0;
            }
        } while (jouerEncore == Result.REJOUER || jouerEncore == Result.RELANCER);
        return jouerEncore;

    }

    /**
     * lance la classe Game pour obtenir un nombre de longueur = lenght
     *
     * @return : retourne true pour continuer le do/while de mode1
     */
    private void randomizer() {
        logger.info("lancement de randomizer");

        nbMystereDecoupe = getRandomized(lenght);
        nbMysteredecoupePrecedent = nbMystereDecoupe;
        logger.info("randomizer vient de renvoyer le nombre mystere" + Arrays.toString(nbMystereDecoupe));
    }

    /**
     * cette méthode effectue un essai
     *
     * @return : sert à indiquer ce que l'utilisateur veut faire en renvoyant le booléen obtenu dans messageVictoire
     */
    private Result essais() {
        int affichageCompteur = compteur;
        if (devMod) {
            System.out.println(Arrays.toString(nbMystereDecoupe));
        }
        compteur = compteur + 1;
        System.out.println("tour effectué: " + affichageCompteur);
        String[] propositionDecoupe = essai();
        boolean verif = false;
        logger.info("verification de rencontre des conditions de victoire ou défaite");
        if (Arrays.equals(propositionDecoupe, nbMysteredecoupePrecedent) || compteur == 6) {
            verif = true;
        }
        System.out.println(Arrays.toString(propositionDecoupe));
        if (verif) {
            Result jouerEncore;
            jouerEncore = this.messageVictoire(propositionDecoupe);
            return jouerEncore;
        }
        return Result.REJOUER;
    }

    /**
     * cette méthode affiche le message de victoire/défaite et affiche le menu de sélection
     *
     * @param propositionDecoupe : la proposition de l'utilisateur decoupé dans une String[]
     * @return : sert à indiquer ce que veux faire l'utilisateur après la partie; true pour relancer un niveau et false pour revenir au menu principal
     */
    private Result messageVictoire(String[] propositionDecoupe) {

        if (Arrays.equals(propositionDecoupe, nbMysteredecoupePrecedent)) {
            System.out.println("Vous avez gagné en " + compteur + " essais, Bravo!");
            compteur = 0;
        }
        if (compteur == 6) {
            System.out.println("Vous avez perdu, la salution était " + Arrays.toString(nbMystereDecoupe));
            compteur = 0;
        }
        return rejouer();
    }

    /**
     * cette méthode est là où l'ordinateur compare le nombre mystère et la proposition et affiche une String composé de "+-=" correspondant
     *
     * @param proposition        : sert à obtenir la longueur de la proposition
     * @param propositionDecoupe : pour récuperer le nombre proposé par l'utilisateur
     * @return :renvoie true pour continuer le tour
     */
    protected void verification(String proposition, String[] propositionDecoupe) {
        logger.info("vérification de la proposition de l'utilisateur");
        String[] reponse = new String[proposition.length()];

        for (int verificateur = 0; verificateur < proposition.length(); verificateur++) {
            String nbMystere;
            nbMystere = nbMystereDecoupe[verificateur];
            int nbDecoupe = Integer.parseInt(nbMystere);
            String proposition1;
            proposition1 = propositionDecoupe[verificateur];
            int propositioncut = Integer.parseInt(proposition1);

            if (propositioncut == nbDecoupe) {
                reponse[verificateur] = "=";
            } else if (propositioncut < nbDecoupe) {
                reponse[verificateur] = "+";
            } else if (propositioncut > nbDecoupe) {
                reponse[verificateur] = "-";
            }

        }
        System.out.println("réponse" + Arrays.toString(reponse));
    }

}
