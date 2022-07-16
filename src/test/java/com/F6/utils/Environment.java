package com.F6.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Environment {

    /*
     Environment variables that will apply for qa, apiqa, dev and staging environments
     */

    public static final String URL;
    public static final String DB_URL;
    public static final String DB_USERNAME;
    public static final String DB_PASSWORD;



    /*
       if we pass the environment from terminal then use that one
       if we do not pass the driver from terminal then use the one property file
     */
    static{
        Properties properties = null;
        String environment = System.getProperty("environment") != null ? environment = System.getProperty("environment") : ConfigurationReader.get("environment");


        try {

            String path = System.getProperty("user.dir") + "/src/test/resources/Environments/" + environment + ".properties";

            FileInputStream input = new FileInputStream(path);
            properties = new Properties();
            properties.load(input);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        URL = properties.getProperty("url");
        DB_URL = properties.getProperty("dbUrl");
        DB_USERNAME = properties.getProperty("dbUsername");
        DB_PASSWORD = properties.getProperty("dbPassword");


    }


}
