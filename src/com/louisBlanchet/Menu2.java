package com.louisBlanchet;

import org.apache.log4j.Logger;

/**
 * classe permettant la sélection du mode de jeu pour le mastermind
 */
class Menu2 extends Menu {
    /**
     * méthode affichant le menu de sélection du mode de jeu du master mind et enregistrant la sélection de l'utilisateur pour l'emmener au mode de jeu désiré
     * @return retourne le choix de l'utilisateur dans le menu de fin de jeu
     */
    Result specialMenu2(){
        Logger logger = Logger.getLogger(Menu2.class);
        logger.info("affichage du menu 2");
        int nbMenu2;
        boolean retourMenu;
        do {

            nbMenu2 = getNbMenu();
            if (nbMenu2 == 1) {
                System.out.println("test1");
            }
            if (nbMenu2 == 2) {
                System.out.println("test2");
            }
            if (nbMenu2 == 3) {
                System.out.println("test3");
            }
            if (nbMenu2 == 4) {
                return Result.REJOUER;
            }
        }while (nbMenu2<1 ||nbMenu2>4);
        return Result.QUITTER;
    }

    }

