package com.louisBlanchet;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PlusOuMoinsMode2 {
    private String[] nbPropose[5];
    private String[] nbPropose1;
    private String[] reponse["+","-"];

    public String[] recupNbMystère() {
        int lenght = 4;
        int nbEssais = 5;
        int nbMystere;
        do {
            System.out.println("vous avez sélectionné le mode 2 veuillez proposer un nombre supérieur à 1000");
            Scanner sc = new Scanner(System.in);
            nbMystere = 0;
            try {
                nbMystere = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("veillez à ne rentrer que des chiffres !");
            }
        } while (nbMystere < 1000);
        String nbMystere1 = Integer.toString(nbMystere);
        String[] nbMystereDecoupe = new String[nbMystere1.length()];

        for (int compteur = 0; compteur < nbMystere1.length(); compteur++) {
            String decoupage = nbMystere1.substring(compteur, compteur + 1);
            nbMystereDecoupe[compteur] = decoupage;
        }
        System.out.println(Arrays.toString(nbMystereDecoupe));
        return nbMystereDecoupe;

    }
    public String[] nbPropose() {
        nbPropose1 = nbPropose;
        for (int compteur = 0; compteur < 5; compteur++) {
            for (int boucle = 0; boucle <= 3; boucle++) {

                if (compteur == 0) {
                    nbPropose = [5, 5, 5, 5];
                }

                if ((nbPropose1[boucle] == 5) && (reponse[boucle] == "+")){
                    System.out.println("test reussi");
                }
            }
        }
    }
}
