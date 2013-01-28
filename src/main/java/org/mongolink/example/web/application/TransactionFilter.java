/*
 * MongoLink, Object Document Mapper for Java and MongoDB
 *
 * Copyright (c) 2012, Arpinum or third-party contributors as
 * indicated by the @author tags
 *
 * MongoLink is free software: you can redistribute it and/or modify
 * it under the terms of the Lesser GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MongoLink is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * Lesser GNU General Public License for more details.
 *
 * You should have received a copy of the Lesser GNU General Public License
 * along with MongoLink.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

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
