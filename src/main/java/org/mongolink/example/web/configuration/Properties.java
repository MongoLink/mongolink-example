package org.mongolink.example.web.configuration;

import org.mongolink.Settings;

import java.io.InputStream;

public class Properties {

    public Settings addSettings(Settings settings) {
        return settings.withHost(getDBHost()).withPort(getDBPort()).
                withDbName(getDBName()).
                withAuthentication(getDBUser(), getDBPassword());
    }

    public String getDBHost() {
        return getProperty("db.host");
    }

    public String getDBName() {
        return getProperty("db.name");
    }

    public int getDBPort() {
        return Integer.valueOf(getProperty("db.port"));
    }

    public String getDBUser() {
        return getProperty("db.user");
    }

    public String getDBPassword() {
        return getProperty("db.password");
    }

    private String getProperty(String nom) {
        return Config.INSTANCE.properties.getProperty(nom);
    }

    private static enum Config {
        INSTANCE;

        private Config() {
            InputStream stream = getClass().getClassLoader().getResourceAsStream("conf.properties");
            properties = new java.util.Properties();
            try {
                properties.load(stream);
                stream.close();
            } catch (Exception e) {
                //
            }
        }

        private final java.util.Properties properties;
    }

}
