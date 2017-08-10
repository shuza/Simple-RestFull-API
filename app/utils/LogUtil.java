package utils;

public class LogUtil {

    public static void printLogMessage(String tag, String type, String message) {
        System.out.println(tag + "\t" + type + "\t ===/ \t" + message);
    }

    public static void printLogMessage(String tag, String message) {
        printLogMessage(tag, "debug", message);
    }

}
