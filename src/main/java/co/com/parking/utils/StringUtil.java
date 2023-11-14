package co.com.parking.utils;

public class StringUtil {
    public static String getFromParking(String parking, String... args) {
        return String.format(parking, args);
    }

    public static String firstUpperLetter(String value){
        return value.substring(0, 1).toUpperCase() + value.substring(1);
    }
}
