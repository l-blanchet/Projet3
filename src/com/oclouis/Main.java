package com.oclouis;

class Main {

    public static void main(String[] args) {
        Config config = new Config();

        if (args.length >0 && args[0].equalsIgnoreCase("dev")) {
            config.setDevMod(true);
        }
        MainMenu main = new MainMenu(config);
        main.displayMenu();
    }
}
