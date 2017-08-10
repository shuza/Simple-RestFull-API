package controllers;

import daos.MongoDbDao;
import daos.UserDao;
import models.UserModel;
import parsers.UserParser;
import play.mvc.Controller;
import play.mvc.Result;

public class UserController extends Controller {
    private static UserParser userParser;

    public UserController() {
        userParser = new UserParser();
    }

    public static Result createUser() {
        UserModel model = userParser.parseUser(request());

        String message = "failed";
        if (UserDao.getInstance().createUser(model)) {
            message = "user created successfully";
        }


        return ok("result " + message);
    }
}
