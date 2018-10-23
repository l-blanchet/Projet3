package com.louisBlanchet;


import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PlusOuMoinsMode1 extends Randomizer {

    private String[] nbMystereDecoupe;
    private String[] nbMysteredecoupe1;



    public String[] getRandomized() {

        super.getRandomized();
        mode1();
        return new String[]{"test"};
    }

    public void mode1() {
        Randomizer test = new Randomizer();
        nbMystereDecoupe = test.getRandomized();
        nbMysteredecoupe1 = nbMystereDecoupe;
        int nbEssais = 7;
        System.out.println("Vous avez sélectionné le mode 1, vous avez " + nbEssais + " essais, bonne chance !");
        for (int compteur = 0; compteur < nbEssais; compteur++) {
            essais(compteur);
        }
    }

    private void essais(int compteur) {
        System.out.println(Arrays.toString(nbMystereDecoupe));
        System.out.println(compteur);
        String[] propositionDecoupe = essai();
        Scanner sc;

        if (Arrays.equals(propositionDecoupe, nbMysteredecoupe1) || compteur == 6) {
            messageVictoire(compteur, propositionDecoupe);
        }
        System.out.println(Arrays.toString(propositionDecoupe));
    }

    private void messageVictoire(int compteur, String[] propositionDecoupe) {
        Scanner sc;
        if (Arrays.equals(propositionDecoupe, nbMysteredecoupe1)) {
            System.out.println("Vous avez gagné en " + compteur + " essais, Bravo!");
        }
        if (compteur == 6) {
            System.out.println("Vous avez perdu");
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
                mode1();
            } else if (selection == 2) {
                MainMenu main = new MainMenu();
                main.displayMenu();
            } else if (selection == 3) {
                System.out.println("fermeture du programme ");
                System.exit(0);
            }

        } while ((selection < 1) || (selection > 3));
    }

    private String[] essai() {
        String proposition = proposition();
        String[] propositionDecoupe = decoupage(proposition);

        verification(proposition, propositionDecoupe);
        return propositionDecoupe;
    }

    private void verification(String proposition, String[] propositionDecoupe) {
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
        int essai ;
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
