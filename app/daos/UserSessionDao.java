package daos;

import models.UserSessionModel;
import org.mongodb.morphia.Datastore;

import java.util.Iterator;

public class UserSessionDao {
    private static UserSessionDao userSessionDao;
    private Datastore datastore;

    private UserSessionDao() {
        datastore = MongoDbDao.getInstance().getDatastore();
    }

    public static UserSessionDao getInstance() {
        if (userSessionDao == null) {
            userSessionDao = new UserSessionDao();
        }
        return userSessionDao;
    }

    public boolean createUserSession(UserSessionModel sessionModel) {
        try {
            datastore.save(sessionModel);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public UserSessionModel getUserSession(String token) {
        try {
            Iterator<UserSessionModel> iterator = datastore.createQuery(UserSessionModel.class)
                    .field("token")
                    .equal(token)
                    .iterator();
            if (iterator.hasNext()) {
                return iterator.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}