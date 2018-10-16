package com.louisBlanchet;

import java.util.Scanner;

public class Menu3 {
    public void specialMenu3 (){
        System.out.println("Voici les explications des différents modes " );
        System.out.println("Mode Challenger : Vous devez deviner le code/combinaison de l'ordinateur ");
        System.out.println("Mode Défenseur : Vous devez protéger votre code/combinaison à l'ordinateur ");
        System.out.println("Mode Hybride : Vous devez à la fois proteger votre code/combinaison à l'ordinateur et deviner le code/combinaison de l'ordinateur ");
        System.out.println("Appuyez sur 1 pour revenir à la page précédente");

        Scanner sc = new Scanner(System.in);
        int nbMenu3 = sc.nextInt();

        if (nbMenu3 == 1){
            MainMenu main = new MainMenu();
            main.displayMenu();
        }
    }
}
