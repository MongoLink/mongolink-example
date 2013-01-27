package org.mongolink.example.persistence;

import org.mongolink.MongoSession;
import org.mongolink.example.domain.FruitRepository;
import org.mongolink.example.domain.Repositories;

public class MongoRepositories extends Repositories {

    public MongoRepositories(MongoSession session) {
        this.session = session;
    }

    @Override
    protected FruitRepository fruitsRepository() {
        return new FruitMongoRepository(session);
    }

    private MongoSession session;


}
