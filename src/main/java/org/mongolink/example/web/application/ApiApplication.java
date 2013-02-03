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

import org.mongolink.example.web.configuration.MongoConfiguration;
import org.mongolink.example.web.resources.FruitResource;
import org.mongolink.example.web.resources.FruitsResource;
import org.mongolink.example.web.resources.HomeResource;
import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class ApiApplication extends Application {

    @Override
    public Restlet createInboundRoot() {
        TransactionFilter transactionFilter = new TransactionFilter();
        ApiRouter apiRouter = new ApiRouter(getContext());
        transactionFilter.setNext(apiRouter);
        return transactionFilter;
    }

    @Override
    public void stop() throws Exception {
        MongoConfiguration.stop();
    }

    protected class ApiRouter extends Router {
        public ApiRouter(Context context) {
            super(context);
            attach();
        }

        private void attach() {
            attach("/", HomeResource.class);
            attach("/fruits", FruitsResource.class);
            attach("/fruits/{id}", FruitResource.class);
        }
    }
}
