package daos;


import Settings.MongoDbSetting;
import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

public class MongoDbDao {
    private static MongoDbDao mongoDbDao;
    private Datastore datastore;

    private MongoDbDao() {
        Morphia morphia = new Morphia();
        morphia.mapPackage("models");
        datastore = morphia.createDatastore(new MongoClient(MongoDbSetting.MONGODB_HOST,
                MongoDbSetting.MONGODB_PORT), MongoDbSetting.MONGODB_DB_NAME_SESSION);
        datastore.ensureIndexes();
    }

    public static MongoDbDao getInstance() {
        if (mongoDbDao == null) {
            mongoDbDao = new MongoDbDao();
        }
        return mongoDbDao;
    }

    public Datastore getDatastore() {
        return datastore;
    }
}
