package utils;

import java.util.Calendar;

public class Authutil {

    public static String getRandomIdentifier(String salt) {
        String token = String.valueOf(Calendar.getInstance().getTimeInMillis());
        return token;
    }

    public static String randomAccessToken(String userName) {
        return getRandomIdentifier(userName);
    }

}
