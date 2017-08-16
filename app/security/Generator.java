package security;

import org.apache.commons.codec.binary.Base64;

import java.util.UUID;

public class Generator {

    public static String createAccessToken() {
        return encryptToBase64(UUID.randomUUID().toString().replace("-", String.valueOf(Math.random())));
    }

    public static String createRefreshToken() {
        return encryptToBase64(UUID.randomUUID().toString().replaceAll("-", "?" + String.valueOf(Math.random()) + "<"));
    }

    public String createUniqueHash() {
        return encryptToBase64(UUID.randomUUID().toString());
    }

    public static String encryptToBase64(String text) {
        return Base64.encodeBase64String(text.getBytes());
    }

}
