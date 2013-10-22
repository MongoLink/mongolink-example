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

import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.Method;
import org.restlet.data.Reference;
import org.restlet.representation.Representation;
import org.restlet.resource.Resource;
import org.restlet.service.ConverterService;

public class ClientResource extends Resource {

    public ClientResource(final String uri, final Restlet application) {
        this.reference = new Reference(uri);
        this.application = application;
    }

    public Representation get() {
        initHandling(Method.GET);
        return handle();
    }

    @Override
    public Representation handle() {
        Request request = getRequest();
        application.handle(request, getResponse());
        return getResponse().getEntity();
    }

    private void initHandling(final Method Method) {
        setRequest(new Request(Method, reference));
        setResponse(new Response(getRequest()));
    }

    public Representation post(final Object entity) {
        initHandling(Method.POST);
        getRequest().setEntity(getRepresentation(entity));
        return handle();
    }

    private Representation getRepresentation(final Object source) {
        if (source == null) {
            return null;
        }
        final ConverterService cs = getConverterService();
        return cs.toRepresentation(source);
    }

    public Representation put(final Object entity) {
        initHandling(Method.PUT);
        getRequest().setEntity(getRepresentation(entity));
        return handle();
    }

    public Representation delete() {
        initHandling(Method.DELETE);
        return handle();
    }

    @Override
    public String getAttribute(String name) {
        return null;
    }

    @Override
    public void setAttribute(String name, Object value) {
    }

    private final Reference reference;
    private final Restlet application;
}
