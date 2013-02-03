package org.mongolink.example.web.resources;

import org.json.JSONArray;
import org.json.JSONObject;
import org.mongolink.example.domain.Fruit;
import org.mongolink.example.domain.Repositories;
import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import java.util.List;

public class FruitsResource extends ServerResource {

    @Get
    public Representation get() {
        List<Fruit> fruits = Repositories.fruits().all();
        JSONArray array = new JSONArray();
        for (Fruit fruit : fruits) {
            array.put(new JSONObject(fruit));
        }
        return new JsonRepresentation(array);
    }

    @Post
    public void post(Form form) {
        Repositories.fruits().add(new Fruit(form.getFirstValue("name")));
        setStatus(Status.SUCCESS_CREATED);
    }
}
