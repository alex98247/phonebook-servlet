package com.digdes.school.phonebook.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {


    public static String getConfig(String name) {
       ;
        Properties properties = new Properties();
        try (InputStream resourceAsStream = Config.class.getResourceAsStream("/config.properties")){
            properties.load(resourceAsStream);
            return properties.getProperty(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
