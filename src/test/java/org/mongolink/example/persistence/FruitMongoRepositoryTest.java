package org.mongolink.example.persistence;

import org.junit.Before;
import org.junit.Test;
import org.mongolink.example.domain.Banana;
import org.mongolink.example.domain.Fruit;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

public class FruitMongoRepositoryTest extends RepositoryTest {

    private FruitMongoRepository repository;

    @Before
    public void before() {
        repository = new FruitMongoRepository(session);
    }

    @Test
    public void canAdd() {
        Fruit fruit = new Fruit("a fruit");
        repository.add(fruit);
        cleanSession();

        Fruit fruitFound = repository.get(fruit.getId());

        assertThat(fruitFound).isNotNull();
        assertThat(fruitFound.getName()).isEqualTo("a fruit");
        assertThat(fruitFound.getId()).isNotNull();
        assertThat(fruitFound.getCreationDate()).isNotNull();
    }

    @Test
    public void canAddBanana() {
        Banana banana = new Banana("a banana");
        repository.add(banana);
        cleanSession();

        Fruit fruitFound = repository.get(banana.getId());

        assertThat(fruitFound).isNotNull();
        assertThat(fruitFound.getName()).isEqualTo("a banana");
        assertThat(fruitFound).isInstanceOf(Banana.class);
    }

    @Test
    public void canDelete() {
        Fruit fruit = new Fruit("another fruit");
        repository.add(fruit);

        repository.delete(fruit);
        cleanSession();

        assertThat(repository.get(fruit.getId())).isNull();
    }

    @Test
    public void canGetAll() {
        repository.add(new Fruit("a fruit"));
        repository.add(new Fruit("another fruit"));

        List<Fruit> fruits = repository.all();

        assertThat(fruits).hasSize(2);
    }
}
