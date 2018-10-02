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
            menu1 menuspe = new menu1();
            menuspe.specialMenu1();
            randomizer rand = new randomizer();
            rand.getRandomized();
        }
        if (nbMenu == 2) {
            menu2 menuspe = new menu2();
            menuspe.specialMenu2();
        }
        if (nbMenu == 3) {
            menu3 menuspe = new menu3();
            menuspe.specialMenu3();


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
