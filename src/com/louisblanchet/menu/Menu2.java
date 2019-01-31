package com.louisblanchet.menu;

import com.louisblanchet.Config;
import com.louisblanchet.mastermind.MastermindMode1;
import com.louisblanchet.mastermind.MastermindMode2;
import com.louisblanchet.Result;
import org.apache.log4j.Logger;

/**
 * classe permettant la sélection du mode de jeu pour le mastermind
 */
public class Menu2 extends Menu {
    private final Config config;
    public boolean mode3;

    public Menu2(Config config) {
        this.config = config;
    }

    /**
     * méthode affichant le menu de sélection du mode de jeu du master mind et enregistrant la sélection de l'utilisateur pour l'emmener au mode de jeu désiré
     * @return retourne le choix de l'utilisateur dans le menu de fin de jeu
     */
    public Result specialMenu2(){
        int rejouer ;
        Logger logger = Logger.getLogger(Menu1.class);
        int nbMenu2;
        Result retourMenu = Result.QUITTER;
        logger.info("affichage du menu pour le mode 1");
        do {

            nbMenu2 = getNbMenu();
            if (nbMenu2 == 1) {
                mode3 = false;

                MastermindMode1 rand = new MastermindMode1(false, config);
                retourMenu = rand.Initialisation();
            }
            if (nbMenu2 == 2) {
                mode3 = false;
                MastermindMode2 test = new MastermindMode2(false,config);
                retourMenu = test.main();
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

