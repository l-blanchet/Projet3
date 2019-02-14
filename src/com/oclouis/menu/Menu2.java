package com.oclouis.menu;

import com.oclouis.Config;
import com.oclouis.Mode2;
import com.oclouis.Mode1;
import com.oclouis.mastermind.MastermindMode1;
import com.oclouis.mastermind.MastermindMode2;
import com.oclouis.Result;
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
        Logger logger = Logger.getLogger(Menu1.class);
        int nbMenu2;

        logger.info("affichage du menu pour le mode 1");
        do {

            nbMenu2 = getNbMenu();
            if (nbMenu2 == 1) {
                mode3 = false;

                Mode1 rand = new MastermindMode1(false, config);
                rand.initialisation();
            }
            if (nbMenu2 == 2) {
                mode3 = false;
                Mode2 test = new MastermindMode2(false,config);
                 test.main();
            }
            if (nbMenu2 == 3) {
                MastermindMode1 rand = new MastermindMode1(true, config);
                rand.initialisation();
            }
            if (nbMenu2 == 4) {
                return Result.REJOUER;
            }
        }while (nbMenu2<1 ||nbMenu2>4);
        return Result.QUITTER;
    }

    }

