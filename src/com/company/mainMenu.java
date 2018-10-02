package com.company;


import java.util.Scanner;

public class mainMenu {
    public void displayMenu (){
        System.out.println("bienvenue sur mon premier projet");
        System.out.println("--------MENU---------");
        System.out.println("1- Plus ou Moins");
        System.out.println("2- MasterMind");
        System.out.println("3- Options");
        System.out.println("4- Quitter");

        Scanner sc = new Scanner(System.in);
        int nbMenu = sc.nextInt();
        displaySelected(nbMenu);

    }
    public void displaySelected(int nbMenu) {



        if (nbMenu == 1) {
            System.out.println("1- Mode Challenger");
            System.out.println("2- Mode Défenseur ");
            System.out.println("3- Mode Hybride");
            System.out.println("4- Retour");
            randomizer rand = new randomizer();
            rand.getRandomized();
        }
        if (nbMenu == 2) {
            System.out.println("1- Mode Challenger");
            System.out.println("2- Mode Défenseur ");
            System.out.println("3- Mode Hybride");
            System.out.println("4- Retour");
        }
        if (nbMenu == 3) {
//           ici proposez le mode développeur quand je saurais faire
            nbMenu = 1;
            System.out.println(nbMenu);
        }
        if (nbMenu == 4) {
            System.out.println("fermeture du programme ");
            System.exit(0);
        }
        if (nbMenu <= 0 || nbMenu >= 5) {
            System.out.println("vous n'avez pas sélectionné de valeur acceptée");
            displayMenu();
        }

     }


}
