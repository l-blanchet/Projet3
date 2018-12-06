package com.louisBlanchet;


import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu {
    public void displayMenu() {
        boolean jouerEncore;
       do {
           int nbMenu;
           do {

               System.out.println("bienvenue sur mon premier projet");
               System.out.println("--------MENU---------");
               System.out.println("1- Plus ou Moins");
               System.out.println("2- MasterMind");
               System.out.println("3- Options");
               System.out.println("4- Quitter");

               Scanner sc = new Scanner(System.in);
               nbMenu = 0;
               try {
                   nbMenu = sc.nextInt();
               } catch (InputMismatchException e) {
                   System.out.println("veillez à ne rentrer que des chiffres !");
               }
           } while (nbMenu < 1 || nbMenu > 4);

           jouerEncore = this.displaySelected(nbMenu);
       }while (jouerEncore == true);
    }

    public boolean displaySelected(int nbMenu) {
        boolean jouerEncore = false;
        if (nbMenu == 1) {
            Menu1 menuSpe = new Menu1();
            jouerEncore = menuSpe.specialMenu1();
        } else if (nbMenu == 2) {
            Menu2 menuSpe = new Menu2();
            jouerEncore = menuSpe.specialMenu2();
        } else if (nbMenu == 3) {
            Menu3 menuSpe = new Menu3();
            jouerEncore =menuSpe.specialMenu3();
        } else if (nbMenu == 4) {
            System.out.println("fermeture du programme ");
            System.exit(0);
        }
        return jouerEncore ;
    }


}


