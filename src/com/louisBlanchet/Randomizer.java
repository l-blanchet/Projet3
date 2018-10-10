package com.louisBlanchet;

import static java.lang.Math.*;

public class Randomizer {



    public String[] getRandomized() {
         int length = 4;
        double d = random();
        d *= pow(10, length);
        int n = (int) d;
        String nbMystere;
        nbMystere = Integer.toString(n);


        while (nbMystere.length() <length){
            double random = random();
            random = random*10;
            int random1 = (int) random;
            nbMystere = (+random1+ nbMystere);
        }

        String[] nbMystereDecoupe = new String[nbMystere.length()];

        for (int compteur = 0; compteur < nbMystere.length(); compteur++) {
            String decoupage = nbMystere.substring(compteur, compteur + 1);
            nbMystereDecoupe[compteur] = decoupage;
        }
        return nbMystereDecoupe;
    }

}
