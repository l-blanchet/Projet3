package com.louisBlanchet;

import java.util.Scanner;

public class Menu1 {
    public void specialMenu1() {
        int nbMenu1;
        do {
            System.out.println("Choisissez le mode de jeu voulu");
            System.out.println("1- Mode Challenger");
            System.out.println("2- Mode Défenseur ");
            System.out.println("3- Mode Hybride");
            System.out.println("4- Retour");
            Scanner sc = new Scanner(System.in);
            nbMenu1 = sc.nextInt();
            if (nbMenu1 == 1) {
                Randomizer test = new Randomizer();
                String[] length = test.getRandomized();
                PlusOuMoinsMode1 rand = new PlusOuMoinsMode1();
                rand.getRandomized();
            }
            else if (nbMenu1 == 2) {
                System.out.println("test2");
            }
            else if (nbMenu1 == 3) {
                System.out.println("test3");
            }
            else if (nbMenu1 == 4) {
                MainMenu main = new MainMenu();
                main.displayMenu();
            }
        }while (nbMenu1<1|| nbMenu1>4);

        }
    }

