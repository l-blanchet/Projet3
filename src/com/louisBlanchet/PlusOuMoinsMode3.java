package com.louisBlanchet;

public class PlusOuMoinsMode3 {
    public int compteur ;
    public  boolean main (){
        boolean jouerEncore ;

        PlusOuMoinsMode1 f = new PlusOuMoinsMode1();
        PlusOuMoinsMode2 g = new PlusOuMoinsMode2();
        do {
            if (compteur ==0) {
                f.getRandomized();
                g.main();
                compteur = compteur + 1;
            }
            jouerEncore = f.mode1();
            jouerEncore = g.mode2();
            compteur = compteur+1;
        }while (compteur != 99);
        jouerEncore = f.getRandomized();
        return jouerEncore;
    }
}
