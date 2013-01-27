package org.mongolink.example.domain;

public abstract class Repositories {

    public static void initialise(Repositories instance) {
        Repositories.instance = instance;
    }

    public static FruitRepository fruits() {
        return instance.fruitsRepository();
    }

    protected abstract FruitRepository fruitsRepository();

    private static Repositories instance;
}
