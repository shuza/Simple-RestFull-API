package daos;

import Settings.PostgreSqlSetting;
import utils.LogUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;


public class PostgreDao {

    public static Connection connect(String dbName) {
        try {
            String url = "jdbc:postgresql://" + PostgreSqlSetting.HOST + ":"
                    + PostgreSqlSetting.PORT + "/" + dbName;
            Properties props = new Properties();
            props.setProperty("user", PostgreSqlSetting.USER_NAME);
            props.setProperty("password", PostgreSqlSetting.PASSWORD);
            //props.setProperty("ssl", "true");
            Connection connection = DriverManager.getConnection(url, props);

            /*Connection connection = DriverManager.getConnection(url,
                    PostgreSqlSetting.USER_NAME,
                    PostgreSqlSetting.PASSWORD);*/

            LogUtil.printLogMessage(PostgreDao.class.getSimpleName(), "PostgreSQL connected successfully");

            return connection;

        } catch (Exception e) {
            LogUtil.printLogMessage(PostgreDao.class.getSimpleName(), "error", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public static boolean createDatabase(String databaseName) {
        try {
            Connection connection = connect("");
            PreparedStatement preparedStatement = connection.prepareStatement("CREATE DATABASE "+databaseName);
            int resultId = preparedStatement.executeUpdate();

            LogUtil.printLogMessage(PostgreDao.class.getSimpleName(), databaseName + " database created successfully");

            return true;
        } catch (Exception e) {
            LogUtil.printLogMessage(PostgreDao.class.getSimpleName(), "error", e.getMessage());
        }
        return false;
    }

}
