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
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mongolink.example.domain.Banana;
import org.mongolink.example.domain.Repositories;
import org.mongolink.example.test.ClientResource;
import org.mongolink.example.test.WithWebApplication;
import org.mongolink.example.test.WithRepository;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.representation.Representation;

import java.io.IOException;

import static org.fest.assertions.Assertions.assertThat;

public class FruitsResourceTest extends WithRepository {

    @Rule
    public WithRepository withRepository = new WithRepository();

    @Rule
    public WithWebApplication webTester = new WithWebApplication();

    @Before
    public void before() {
        resource = webTester.newClientResource("/fruits");
    }

    @Test
    public void canGetAll() throws IOException {
        Banana banana = new Banana("a banana");
        Repositories.fruits().add(banana);

        Representation representation = resource.get();

        assertThat(resource.getStatus()).isEqualTo(Status.SUCCESS_OK);
        assertThat(representation.getMediaType()).isEqualTo(MediaType.APPLICATION_JSON);
        assertThat(representation.getText()).contains(new JSONObject(banana).toString());
    }

    @Test
    public void canCreate() {
        resource.post(new Form("name=a fruit"));

        assertThat(resource.getStatus()).isEqualTo(Status.SUCCESS_CREATED);
        assertThat(Repositories.fruits().all()).hasSize(1);
        assertThat(Repositories.fruits().all().get(0).getName()).isEqualTo("a fruit");
    }

    ClientResource resource;
}
