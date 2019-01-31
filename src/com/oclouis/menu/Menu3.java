package com.oclouis.menu;

import com.oclouis.Result;
import org.apache.log4j.Logger;

import java.util.Scanner;

/**
 * classe permettant l'affichage du menu d'information des modes de jeu
 */
public class Menu3 {
    public Result specialMenu3 (){
        Logger logger = Logger.getLogger(Menu3.class);
        logger.info("affichage du menu 3");
        boolean retourMenu;
        System.out.println("Voici les explications des différents modes " );
        System.out.println("Mode Challenger : Vous devez deviner le code/combinaison de l'ordinateur ");
        System.out.println("Mode Défenseur : Vous devez protéger votre code/combinaison à l'ordinateur ");
        System.out.println("Mode Hybride : Vous devez à la fois proteger votre code/combinaison à l'ordinateur et deviner le code/combinaison de l'ordinateur ");
        System.out.println("Appuyez sur 1 pour revenir à la page précédente");

        Scanner sc = new Scanner(System.in);
        int nbMenu3 = sc.nextInt();

        return Result.QUITTER ;
    }
}
