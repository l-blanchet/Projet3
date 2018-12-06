package com.louisBlanchet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static java.lang.Math.*;

public class Randomizer {

    public String[] getRandomized(int lenght ) {
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
