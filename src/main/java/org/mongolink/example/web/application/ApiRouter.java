package org.mongolink.example.web.application;

import org.mongolink.example.web.ressources.FruitsResource;
import org.mongolink.example.web.ressources.HomeResource;
import org.restlet.Context;
import org.restlet.routing.Router;

public class ApiRouter extends Router {
    public ApiRouter(Context context) {
        super(context);
        attach();
    }

    private void attach() {
        attach("/", HomeResource.class);
        attach("/fruits", FruitsResource.class);
    }
}
