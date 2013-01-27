package org.mongolink.example.domain;

public class Banana extends Fruit {

    protected Banana() {
        // for mongolink
    }

    public Banana(String name) {
        super(name);
    }

}
