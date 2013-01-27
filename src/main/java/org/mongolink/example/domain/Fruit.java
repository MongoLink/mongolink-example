package org.mongolink.example.domain;

import java.util.Date;
import java.util.UUID;

public class Fruit {

    @SuppressWarnings("UnusedDeclaration")
    protected Fruit() {
        // for mongolink
    }

    public Fruit(String name) {
        this.name = name;
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getCreationDate() {
        return creationDate;
    }


    private UUID id;
    private String name;
    private Date creationDate = new Date();
}
