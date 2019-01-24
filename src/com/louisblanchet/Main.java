package com.louisblanchet;

public class Main {

    public static void main(String[] args) {
        Config config = new Config();

        if (args[0].equalsIgnoreCase("dev")) {
            config.setDevMod(true);
        }
        MainMenu main = new MainMenu(config);
        main.displayMenu();
    }
}
