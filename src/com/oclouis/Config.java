package com.oclouis;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * classe récupérant les valeurs présentes dans le fichier de config
 */
public class Config {
    /**
     * méthode générale récupérant toute les valeurs du fichier de configuration
     */
    private static final Logger logger = Logger.getLogger(Config.class);
    private int length;
    private boolean devMod;
    private int nbEssai;
    private int nbCouleur;

    public Config() {
        property();
    }



    private void property() {

        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("config/config.properties");

            // load a properties file
            prop.load(input);




        } catch (IOException ex) {
            logger.fatal("fichier non trouvé", ex);
            System.exit(1);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        String test = prop.getProperty("lenght");
        length = Integer.parseInt(test);
        String modDev = prop.getProperty("devMod");
        devMod = Boolean.parseBoolean(modDev);
        String nbEssais = prop.getProperty("nbEssai");
        nbEssai = Integer.parseInt(nbEssais);
        String nbCouleurs = prop.getProperty("nbCouleur");
        nbCouleur = Integer.parseInt(nbCouleurs);


    }

    public int getLength() {
        return length;
    }

    public void setDevMod(boolean devMod) {
        this.devMod = devMod;
    }

    public boolean isDevMod() {
        return devMod;
    }

    public int getNbEssai() {
        return nbEssai;
    }

    public int getNbCouleur() {
        return nbCouleur;
    }
}
