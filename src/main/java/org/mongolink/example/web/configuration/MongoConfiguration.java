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

package org.mongolink.example.web.configuration;

import org.mongolink.*;
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
            ContextBuilder builder = new ContextBuilder("org.mongolink.example.persistence.mapping");
            mongoSessionManager = MongoSessionManager.create(builder, new Properties().addSettings(Settings.defaultInstance()));
        }

        private final MongoSessionManager mongoSessionManager;
    }
}
