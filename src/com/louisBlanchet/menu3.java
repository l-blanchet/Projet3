package com.louisBlanchet;

import java.util.Scanner;

public class menu3 {
    public void specialMenu3 (){
        System.out.println("Vous êtes à présent dans les options " );
        System.out.println("1- Options Avancées");
        System.out.println("2- explications des modes de jeu ");
        System.out.println("3- retour ");

        Scanner sc = new Scanner(System.in);
        int nbMenu3 = sc.nextInt();

        if (nbMenu3 == 1){
            System.out.println("cette section n'est pas encore disponible veuillez revenir plus tard");
            specialMenu3();
        }
        if (nbMenu3 == 2){
            System.out.println("Mode Challenger : Vous devez deviner le code/combinaison de l'ordinateur ");
            System.out.println("Mode Défenseur : Vous devez protéger votre code/combinaison à l'ordinateur ");
            System.out.println("Mode Hybride : Vous devez à la fois proteger votre code/combinaison à l'ordinateur et deviner le code/combinaison de l'ordinateur ");
            System.out.println("Appuyez sur 1 pour revenir à la page précédente");
            int nbMenuspe;
            do {
            Scanner sc1 = new Scanner(System.in);
             nbMenuspe = sc1.nextInt();

                if (nbMenuspe == 1) {
                    specialMenu3();
                }
                if (nbMenuspe != 1) {
                    System.out.println("veuillez entrer le bon chiffre ");

                }
            }while (nbMenuspe != 1);
        }
        if (nbMenu3 == 3){
            MainMenu main = new MainMenu();
            main.displayMenu();
        }
    }
}
