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

package org.mongolink.example.test;

import org.junit.rules.ExternalResource;
import org.mongolink.MongoSession;
import org.mongolink.MongoSessionManager;
import org.mongolink.Settings;
import org.mongolink.domain.mapper.ContextBuilder;
import org.mongolink.example.domain.Repositories;
import org.mongolink.example.persistence.MongoRepositories;

public class WithRepository extends ExternalResource {

    @Override
    protected void before() throws Throwable {
        ContextBuilder contextBuilder = new ContextBuilder("org.mongolink.example.persistence.mapping");
        MongoSessionManager mongoSessionManager = MongoSessionManager.create(contextBuilder, Settings.defaultInstance().withDbFactory(FongoDBFactory.class));
        session = mongoSessionManager.createSession();
        session.start();
        Repositories.initialise(new MongoRepositories(session));
    }

    @Override
    protected void after() {
        session.stop();
        FongoDBFactory.clean();
    }

    public void cleanSession() {
        session.flush();
        session.clear();
    }

    private MongoSession session;
}
