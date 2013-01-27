package org.mongolink.example.persistence.mapping;

import org.mongolink.domain.mapper.AggregateMap;
import org.mongolink.domain.mapper.SubclassMap;
import org.mongolink.example.domain.Banana;
import org.mongolink.example.domain.Fruit;

@SuppressWarnings("UnusedDeclaration")
public class FruitMapping extends AggregateMap<Fruit> {
    public FruitMapping() {
        super(Fruit.class);
    }

    @Override
    public void map() {
        id(element().getId()).natural();
        property(element().getName());
        property(element().getCreationDate());
        subclass(new SubclassMap<Banana>(Banana.class) {

            @Override
            protected void map() {

            }
        });
    }
}
