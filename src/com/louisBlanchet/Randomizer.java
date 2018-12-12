package com.louisBlanchet;

import static java.lang.Math.pow;
import static java.lang.Math.random;

/**
 * Classe créant un nombre aléatoire
 */
public class Randomizer {

    public String[] getRandomized(int lenght ) {
        /**
         * méthode générant un nombre aéatoire en utilisant la fonction random
         */
        double d = random();
        d *= pow(10, lenght);
        int n = (int) d;
        String nbMystere;
        nbMystere = Integer.toString(n);


        while (nbMystere.length() < lenght) {
            double random = random();
            random = random * 10;
            int random1 = (int) random;
            nbMystere = (+random1 + nbMystere);
        }

        String[] nbMystereDecoupe = new String[nbMystere.length()];

        for (int compteur = 0; compteur < nbMystere.length(); compteur++) {
            String decoupage = nbMystere.substring(compteur, compteur + 1);
            nbMystereDecoupe[compteur] = decoupage;
        }
        return nbMystereDecoupe;
    }

}
