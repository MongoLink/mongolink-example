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

import org.mongolink.MongoSession;
import org.mongolink.example.domain.Repository;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class MongoRepository<T> implements Repository<T> {

    protected MongoRepository(MongoSession session) {
        this.session = session;
    }

    @Override
    public T get(Object id) {
        return session.get(id, persistentType());
    }

    @Override
    public void delete(T entity) {
        session.delete(entity);
    }

    @Override
    public void add(T entity) {
        session.save(entity);
    }

    @Override
    public List<T> all() {
        return session.getAll(persistentType());
    }

    protected final Class<T> persistentType() {
        final ParameterizedType superclass = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<T>) superclass.getActualTypeArguments()[0];
    }


    protected final MongoSession session;
}
