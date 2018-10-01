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
        if (nbMenu == 1){
            System.out.println("menu 1");
        }
    }
}
