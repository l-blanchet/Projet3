package com.louisBlanchet;


import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PlusOuMoinsMode1 extends Randomizer {

    private String[] nbMystereDecoupe;
    private String[] nbMysteredecoupe1;
    int compteur = 0;
    int lenght;
    boolean devMod;

    public boolean getRandomized() {
        Config configuration = new Config();
        lenght = configuration.getLength();
        devMod = configuration.isDevMod();

        boolean jouerEncore;
        do {
            jouerEncore = this.mode1();
        } while (jouerEncore == true);
        return jouerEncore;
    }

    public boolean mode1() {
        boolean jouerEncore = true;

        int nbEssais = 7;
        System.out.println("Vous avez sélectionné le mode 1, vous avez " + nbEssais + " essais, bonne chance !");


        do {
            if (compteur == 0 && jouerEncore == true) {
                randomizer();
            }
            jouerEncore = this.essais(compteur);
        } while (jouerEncore == true );
        return jouerEncore;

    }

    public boolean randomizer() {
        Randomizer test = new Randomizer();
        nbMystereDecoupe = test.getRandomized(lenght);
        nbMysteredecoupe1 = nbMystereDecoupe;
        return true;
    }


    private boolean essais(int compteur1) {
        compteur1 = compteur;
        if (devMod == true) {
            System.out.println(Arrays.toString(nbMystereDecoupe));
        }
        compteur = compteur + 1;
        System.out.println(compteur1);
        String[] propositionDecoupe = essai();
        boolean verif = false;
        if (Arrays.equals(propositionDecoupe, nbMysteredecoupe1) || compteur == 6) {
            verif = true;
        }
        System.out.println(Arrays.toString(propositionDecoupe));
        if (verif == true) {
            boolean jouerEncore;
            jouerEncore = this.messageVictoire(compteur, propositionDecoupe);
            return jouerEncore;
        }
        return true;
    }

    private boolean messageVictoire(int compteur2, String[] propositionDecoupe) {
        Scanner sc;
        compteur2 = compteur;
        boolean rejouer;
        if (Arrays.equals(propositionDecoupe, nbMysteredecoupe1)) {
            System.out.println("Vous avez gagné en " + compteur + " essais, Bravo!");
            randomizer();
            compteur = 0;
        }
        if (compteur == 6) {
            System.out.println("Vous avez perdu");
            randomizer();
            compteur = 0;
        }
        int selection;
        do {
            System.out.println("veuillez sélectionner dans quelle section voulez vous aller");
            System.out.println("1- Rejouer");
            System.out.println("2- Menu Principal");
            System.out.println("3- Quitter");

            sc = new Scanner(System.in);
            selection = 0;
            try {
                selection = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("veillez à ne rentrer que des chiffres !");
            }
            if (selection == 1) {
                return true;
            } else if (selection == 2) {
                return false;
            } else if (selection == 3) {
                System.out.println("fermeture du programme ");
                System.exit(0);
            }

            return false;
        } while ((selection < 1) || (selection > 3));
    }

    private String[] essai() {
        String proposition = proposition();
        String[] propositionDecoupe = decoupage(proposition);

        verification(proposition, propositionDecoupe);
        return propositionDecoupe;
    }

    private boolean verification(String proposition, String[] propositionDecoupe) {
        String[] reponse = new String[proposition.length()];

        for (int verificateur = 0; verificateur < proposition.length(); verificateur++) {
            String nbMystere;
            nbMystere = nbMystereDecoupe[verificateur];
            int nbDecoupe = Integer.parseInt(nbMystere);
            String proposition1;
            proposition1 = propositionDecoupe[verificateur];
            int propositioncut = Integer.parseInt(proposition1);

            if (propositioncut == nbDecoupe) {
                reponse[verificateur] = "=";
            } else if (propositioncut < nbDecoupe) {
                reponse[verificateur] = "-";
            } else if (propositioncut > nbDecoupe) {
                reponse[verificateur] = "+";
            }

        }
        System.out.println("réponse" + Arrays.toString(reponse));
        return true;
    }

    private String[] decoupage(String proposition) {
        String[] propositionDecoupe = new String[proposition.length()];

        for (int compteurtest = 0; compteurtest < proposition.length(); compteurtest++) {
            String decoupage = proposition.substring(compteurtest, compteurtest + 1);
            propositionDecoupe[compteurtest] = decoupage;
        }
        return propositionDecoupe;
    }

    private String proposition() {
        boolean checkeur = false;
        String proposition = null;
        int essai;
        Scanner sc = new Scanner(System.in);

        while (!checkeur) {

            System.out.println("proposition:");

            try {
                essai = sc.nextInt();
                proposition = Integer.toString(essai);

                if (proposition.length() < nbMysteredecoupe1.length || proposition.length() > nbMysteredecoupe1.length) {
                    System.out.println("vous n'avez pas rentré le bon nombre de chiffres réesayez");
                } else {
                    checkeur = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("veillez à ne rentrer que des chiffres !");
                checkeur = false;
            }

        }
        return proposition;

    }
}
