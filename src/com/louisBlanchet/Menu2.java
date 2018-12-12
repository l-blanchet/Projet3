package com.louisBlanchet;

import org.apache.log4j.Logger;

import java.util.Scanner;

/**
 * classe permettant la sélection du mode de jeu pour le mastermind
 */
public class Menu2 {
    /**
     * méthodde affichant le menu de sélection du mode de jeu du master minf$d et enegistrant la sélectionde l'utilisateur pour l'emmener au mode de jeu désiré
     * @return retourne le choix de l'utilisateur dans le menu de fin de jeu 
     */
    public boolean specialMenu2 (){
        Logger logger = Logger.getLogger(Menu2.class);
        logger.info("affichage du menu 2");
        int nbMenu2;
        boolean retourMenu;
        do {
            System.out.println("Choisissez le mode de jeu voulu");
            System.out.println("1- Mode Challenger");
            System.out.println("2- Mode Défenseur ");
            System.out.println("3- Mode Hybride");
            System.out.println("4- Retour");
            Scanner sc = new Scanner(System.in);
            nbMenu2 = sc.nextInt();
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
                return true;
            }
        }while (nbMenu2<1 ||nbMenu2>4);
        return false;
    }

    }

