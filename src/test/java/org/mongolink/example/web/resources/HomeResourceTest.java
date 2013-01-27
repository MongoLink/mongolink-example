package org.mongolink.example.web.resources;

import org.junit.Test;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;

import java.io.IOException;

import static org.fest.assertions.Assertions.assertThat;

public class HomeResourceTest {

    @Test
    public void canRepresent() throws IOException {
        Representation representation = new HomeResource().get();

        assertThat(representation.getMediaType()).isEqualTo(MediaType.APPLICATION_JSON);
        assertThat(representation.getText()).isEqualTo("{ \"message\": \"hello!\" }");
    }
}
