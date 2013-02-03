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

package org.mongolink.example.web.resources;

import org.json.JSONObject;
import org.mongolink.example.domain.Fruit;
import org.mongolink.example.domain.Repositories;
import org.restlet.data.Form;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.*;

import java.util.UUID;

public class FruitResource extends ServerResource {

    @Override
    protected void doInit() throws ResourceException {
        fruit = Repositories.fruits().get(UUID.fromString(getRequestAttributes().get("id").toString()));
    }

    @Get
    public Representation get() {
         return new JsonRepresentation(new JSONObject(fruit));
    }

    @Put
    public void put(Form form) {
        fruit.setName(form.getFirstValue("name"));
    }

    @Delete
    public Representation delete() {
        Repositories.fruits().delete(fruit);
        return null;
    }

    private Fruit fruit;
}
