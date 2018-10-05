package com.louisBlanchet;


import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu {
    public void displayMenu() {
        System.out.println("bienvenue sur mon premier projet");
        System.out.println("--------MENU---------");
        System.out.println("1- Plus ou Moins");
        System.out.println("2- MasterMind");
        System.out.println("3- Options");
        System.out.println("4- Quitter");

        Scanner sc = new Scanner(System.in);
        int nbMenu = 0;
        try {
            nbMenu = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("veillez à ne rentrer que des chiffres !");
            displayMenu();
        }

        displaySelected(nbMenu);
    }
    public void displaySelected(int nbMenu) {



        if (nbMenu == 1) {
/*            menu1 menuspe = new menu1();
            menuspe.specialMenu1();*/
            Randomizer test = new Randomizer();
            String[] length = test.getRandomized();
            PlusOuMoinsMode1 rand = new PlusOuMoinsMode1();
            rand.getRandomized();
        }
        else if (nbMenu == 2) {
            Menu2 menuspe = new Menu2();
            menuspe.specialMenu2();
        }
        else if (nbMenu == 3) {
            Menu3 menuspe = new Menu3();
            menuspe.specialMenu3();
        }
        else if (nbMenu == 4) {
            System.out.println("fermeture du programme ");
            System.exit(0);
        }
        else {
            System.out.println("vous n'avez pas sélectionné de valeur acceptée");
            displayMenu();
        }

     }


}


