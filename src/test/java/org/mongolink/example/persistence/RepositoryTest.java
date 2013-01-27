package org.mongolink.example.persistence;

import org.junit.After;
import org.junit.Before;
import org.mongolink.MongoSession;
import org.mongolink.MongoSessionManager;
import org.mongolink.Settings;
import org.mongolink.domain.mapper.ContextBuilder;
import org.mongolink.example.domain.Repositories;

public abstract class RepositoryTest {
    @Before
    public void beforeTest() {
        ContextBuilder contextBuilder = new ContextBuilder("org.mongolink.example.persistence.mapping");
        MongoSessionManager mongoSessionManager = MongoSessionManager.create(contextBuilder, Settings.defaultInstance().withDbFactory(FongoDBFactory.class));
        session = mongoSessionManager.createSession();
        session.start();
        Repositories.initialise(new MongoRepositories(session));
    }

    @After
    public void afterTest() {
        session.stop();
        FongoDBFactory.clean();
    }

    protected void cleanSession() {
        session.flush();
        session.clear();
    }

    protected MongoSession session;
}
