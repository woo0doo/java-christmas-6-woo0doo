package christmas.util;

public class ConvertUtil {

    public static String convertIntPriceToStringCommaPrice(int intPrice) {
        return String.format("%,d", intPrice);
    }
}
