package org.mongolink.example.persistence;

import org.mongolink.MongoSession;
import org.mongolink.example.domain.Fruit;
import org.mongolink.example.domain.FruitRepository;

public class FruitMongoRepository extends MongoRepository<Fruit> implements FruitRepository {
    public FruitMongoRepository(MongoSession mongoSession) {
        super(mongoSession);
    }

}
