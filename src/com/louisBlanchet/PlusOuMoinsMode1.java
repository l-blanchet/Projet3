package com.louisBlanchet;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PlusOuMoinsMode1 extends Randomizer {
    public String[] getRandomized() {

        super.getRandomized();
        mode1();

        return new String[]{"test"};
    }
    public void mode1(){
        Randomizer test = new Randomizer();
        String nbMystereDecoupe[] = test.getRandomized();
        int nbEssais = 7;
        System.out.println("Vous avez sélectionné le mode 1, vous avez "+nbEssais+" essais, bonne chance !");
        for (int compteur =0 ; compteur<nbEssais;compteur++) {
            System.out.println("proposition:");
            Scanner sc = new Scanner(System.in);
            int essai = 0;
            try {
                essai = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("veillez à ne rentrer que des chiffres !");
                mode1();
            }

            String proposition = Integer.toString(essai);
            if (proposition.length()<nbMystereDecoupe.length|| proposition.length()>nbMystereDecoupe.length){
                System.out.println("vous n'avez pas rentré le bon nombre de chiffres réesayez");
                mode1();
            }
            String[] propositionDecoupe = new String[proposition.length()];

            for (int compteurtest = 0; compteurtest < proposition.length(); compteurtest++) {
                String decoupage = proposition.substring(compteurtest, compteurtest + 1);
                propositionDecoupe[compteurtest] = decoupage;
            }
            System.out.println(nbMystereDecoupe);
        }
    }
}
