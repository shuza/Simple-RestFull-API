package daos;

import models.UserModel;
import org.mongodb.morphia.Datastore;
import utils.LogUtil;

import java.util.Iterator;
import java.util.List;

public class UserDao {
    private static UserDao userDao;
    private Datastore datastore;

    private UserDao() {
        datastore = MongoDbDao.getInstance().getDatastore();
    }

    public static UserDao getInstance() {
        if (userDao == null) {
            userDao = new UserDao();
        }
        return userDao;
    }

    public boolean createUser(UserModel model) {
        try {
            datastore.save(model);
            return true;
        } catch (Exception e) {
            LogUtil.printLogMessage("create_user", "error", e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public UserModel logIn(String username, String password) {
        try {
            List<UserModel> userModels = datastore.createQuery(UserModel.class)
                    .field("name").equal(username)
                    .field("password").equal(password)
                    .asList();
            if (userModels.size() > 0) {
                return userModels.get(0);
            }
        } catch (Exception e) {
            LogUtil.printLogMessage("log_in", "error", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

}
