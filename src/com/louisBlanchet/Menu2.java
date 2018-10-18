package com.louisBlanchet;

import java.util.Scanner;

public class Menu2 {
    public void specialMenu2 (){
        int nbMenu2;
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
                MainMenu main = new MainMenu();
                main.displayMenu();
            }
        }while (nbMenu2<1 ||nbMenu2>4);
        }
    }

