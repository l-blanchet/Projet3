package com.louisBlanchet;

import org.apache.log4j.Logger;

/**
 * Classe servant à afficher le menu pour le plus ou moins
 */
public class Menu1 extends Menu{
    /**
     * affiche le menu pour le jeu plus ou moins et analyse l'entrée faite par l'utilisateur pour l'envoyer à la classe correspondante
     * @return retourne la valeur obtenue pour réafficher le menu principal
     */
    public boolean specialMenu1() {
        Logger logger = Logger.getLogger(Menu1.class);
        int nbMenu1;
        boolean retourMenu = false;
        logger.info("affichage du menu pour le mode 1");
        do {
            nbMenu1 = getNbMenu();
            logger.info("choix de menu"+nbMenu1);
            if (nbMenu1 == 1) {

                PlusOuMoinsMode1 rand = new PlusOuMoinsMode1();
                retourMenu = rand.getRandomized();
            }
            else if (nbMenu1 == 2) {
                PlusOuMoinsMode2 c = new PlusOuMoinsMode2();
                c.main();
            }
            else if (nbMenu1 == 3) {
                System.out.println("test3");
            }
            else if (nbMenu1 == 4) {
                 return true;
            }
        }while (nbMenu1<1|| nbMenu1>4);
        return retourMenu;
        }

}

