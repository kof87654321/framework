package com.zl.service.emchat.comm;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {

    public static Properties getProperties() {

        Properties p = new Properties();

        try {
            InputStream inputStream = PropertiesUtils.class.getClassLoader().getResourceAsStream("emchat.properties");

            p.load(inputStream);

        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return p;
    }
}