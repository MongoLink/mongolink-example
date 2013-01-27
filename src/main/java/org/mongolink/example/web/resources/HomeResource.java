package org.mongolink.example.web.resources;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class HomeResource extends ServerResource {

    @Get
    public Representation get() {
        return new JsonRepresentation("{ \"message\": \"hello!\" }");
    }
}
