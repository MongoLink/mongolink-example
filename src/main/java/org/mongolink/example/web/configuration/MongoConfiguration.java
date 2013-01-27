package org.mongolink.example.web.configuration;

import org.mongolink.MongoSession;
import org.mongolink.MongoSessionManager;
import org.mongolink.Settings;
import org.mongolink.domain.UpdateStrategies;
import org.mongolink.domain.mapper.ContextBuilder;

public class MongoConfiguration {

    public static void stop() {
        Singleton.INSTANCE.mongoSessionManager.close();
    }

    public static MongoSession createSession() {
        return Singleton.INSTANCE.mongoSessionManager.createSession();
    }

    private enum Singleton {

        INSTANCE;

        private Singleton() {
            Properties properties = new Properties();
            final Settings settings = Settings.defaultInstance().withHost(properties.getDBHost())
                    .withDbName(properties.getDBName()).withDefaultUpdateStrategy(UpdateStrategies.DIFF);
            ContextBuilder builder = new ContextBuilder("org.mongolink.example.persistence.mapping");
            mongoSessionManager = MongoSessionManager.create(builder, settings);
        }

        private final MongoSessionManager mongoSessionManager;
    }
}
