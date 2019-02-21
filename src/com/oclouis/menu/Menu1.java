package com.oclouis.menu;

import com.oclouis.Config;
import com.oclouis.Mode2;
import com.oclouis.Mode1;
import com.oclouis.Result;
import com.oclouis.plusoumoins.PlusOuMoinsMode1;
import com.oclouis.plusoumoins.PlusOuMoinsMode2;
import org.apache.log4j.Logger;

/**
 * Classe servant à afficher le menu pour le plus ou moins
 */
public class Menu1 extends Menu {


    private final Config config;

    public Menu1(Config config) {
        this.config = config;
    }

    /**
     * affiche le menu pour le jeu plus ou moins et analyse l'entrée faite par l'utilisateur pour l'envoyer à la classe correspondante
     * @return retourne la valeur obtenue pour réafficher le menu principal
     */
    public Result specialMenu1() {
        Logger logger = Logger.getLogger(Menu1.class);
        int nbMenu1;
        Result retourMenu = Result.QUITTER;
        logger.info("affichage du menu pour le mode 1");
        do {
            nbMenu1 = getNbMenu();
            logger.info("choix du plus ou moins mode "+nbMenu1);
            if (nbMenu1 == 1) {


                Mode1 rand = new PlusOuMoinsMode1(false , config);
                retourMenu = rand.initialisation();
            }
            else if (nbMenu1 == 2) {

                Mode2 c = new PlusOuMoinsMode2(false ,config);
                c.main();
            }
            else if (nbMenu1 == 3) {
                PlusOuMoinsMode1 f = new PlusOuMoinsMode1(true, config);
                f.initialisation();
            }
            else if (nbMenu1 == 4) {
                 return Result.REJOUER;
            }
        }while (nbMenu1<1|| nbMenu1>4);
        return retourMenu;
    }
}

