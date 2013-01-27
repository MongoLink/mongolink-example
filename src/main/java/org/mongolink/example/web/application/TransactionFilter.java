package org.mongolink.example.web.application;

import org.mongolink.MongoSession;
import org.mongolink.example.domain.Repositories;
import org.mongolink.example.persistence.MongoRepositories;
import org.mongolink.example.web.configuration.MongoConfiguration;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.routing.Filter;

public class TransactionFilter extends Filter {

    @Override
    protected int beforeHandle(final Request request, final Response response) {
        final MongoSession session = MongoConfiguration.createSession();
        session.start();
        currentSession.set(session);
        Repositories.initialise(new MongoRepositories(session));
        return Filter.CONTINUE;
    }

    @Override
    protected void afterHandle(final Request request, final Response response) {
        currentSession.get().stop();
        currentSession.remove();
    }

    private final ThreadLocal<MongoSession> currentSession = new ThreadLocal<MongoSession>();
}
