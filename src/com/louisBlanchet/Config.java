package com.louisBlanchet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    int length;
    boolean devMod;

    public Config() {
        property();
    }

    public boolean isDevMod() {
        return devMod;
    }

    public void property() {

        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("src/domain/properties/config.properties");

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            System.out.println(prop.getProperty("lenght"));


        } catch (IOException ex) {
            ex.printStackTrace();
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



    }

    public int getLength() {
        return length;
    }
}
