package controllers;

import Settings.PostgreSqlSetting;
import daos.PostgreDao;
import play.mvc.Controller;
import play.mvc.Result;

import java.sql.Connection;

public class PostgreController extends Controller {

    public static Result testConnection() {
        Connection connection = PostgreDao.connect("");
        //boolean isConnected = PostgreDao.connect("postgres");
        String message = "Connection failed!!";
        if (connection != null) {
            message = "Connected successfully";
        }

        return ok("result: " + message);

    }

    public static Result createDatabase(String databaseName) {
        boolean isSuccess = PostgreDao.createDatabase(databaseName);
        String message = databaseName + " create failed";
        if (isSuccess) {
            message = databaseName + " create successfully";
        }

        return ok("result: " + message);
    }

}
