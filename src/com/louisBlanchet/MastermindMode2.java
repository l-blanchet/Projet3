package com.louisBlanchet;

import java.util.HashSet;

import static java.lang.Math.pow;

public class MastermindMode2 {
    int lenght;
    boolean mode3;
    int nbCouleur;

    public  MastermindMode2(boolean mode3) {
        mode3 = this.mode3;
    }


    public void test1(){
        Config configuration = new Config();
        nbCouleur = configuration.getNbCouleur();
        lenght = configuration.getLength();

        HashSet test = new HashSet();

        for (int i = (int) pow(nbCouleur, (lenght - 1)); i < (pow(nbCouleur, lenght)-1); i++) {

                String convert = Integer.toString(i,nbCouleur);
                test.add(convert);
        }
        System.out.println(test);
        int n;
        n =test.size();
        System.out.println(n);
    }
}
