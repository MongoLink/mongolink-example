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

package org.mongolink.example.persistence;

import org.junit.Rule;
import org.junit.Test;
import org.mongolink.example.domain.*;
import org.mongolink.example.test.WithRepository;

import java.util.List;

import static org.fest.assertions.Assertions.*;

public class FruitMongoRepositoryTest {

    @Rule
    public WithRepository withRepository = new WithRepository();

    @Test
    public void canAdd() {
        Fruit fruit = new Fruit("a fruit");
        Repositories.fruits().add(fruit);
        withRepository.cleanSession();

        Fruit fruitFound = Repositories.fruits().get(fruit.getId());

        assertThat(fruitFound).isNotNull();
        assertThat(fruitFound.getName()).isEqualTo("a fruit");
        assertThat(fruitFound.getId()).isNotNull();
        assertThat(fruitFound.getCreationDate()).isNotNull();
    }

    @Test
    public void canAddBanana() {
        Banana banana = new Banana("a banana");
        Repositories.fruits().add(banana);
        withRepository.cleanSession();

        Fruit fruitFound = Repositories.fruits().get(banana.getId());

        assertThat(fruitFound).isNotNull();
        assertThat(fruitFound.getName()).isEqualTo("a banana");
        assertThat(fruitFound).isInstanceOf(Banana.class);
    }

    @Test
    public void canDelete() {
        Fruit fruit = new Fruit("another fruit");
        Repositories.fruits().add(fruit);

        Repositories.fruits().delete(fruit);
        withRepository.cleanSession();

        assertThat(Repositories.fruits().get(fruit.getId())).isNull();
    }

    @Test
    public void canGetAll() {
        Repositories.fruits().add(new Fruit("a fruit"));
        Repositories.fruits().add(new Fruit("another fruit"));
        withRepository.cleanSession();

        List<Fruit> fruits = Repositories.fruits().all();

        assertThat(fruits).hasSize(2);
    }

}
