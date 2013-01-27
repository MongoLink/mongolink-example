package org.mongolink.example.persistence;

import com.foursquare.fongo.Fongo;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import org.mongolink.DbFactory;

public class FongoDBFactory extends DbFactory {


    @Override
    public DB get(String dbName) {
        return fongo.getDB(dbName);
    }


    public static void clean() {
        for (String databaseName : fongo.getDatabaseNames()) {
            DB db = fongo.getDB(databaseName);
            for (String collectionName : db.getCollectionNames()) {
                db.getCollection(collectionName).remove(new BasicDBObject());
            }

        }
    }


    private static final Fongo fongo = new Fongo("serveur de test");
}
