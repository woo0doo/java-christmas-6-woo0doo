package christmas.util;

import java.util.List;
import java.util.stream.Stream;

public class SeparationUtil {

    private static final String COMMA_SPLIT = ",";
    private static final String MINUS_SIGN_SPLIT = "-";

    public static List<String> separateMenus(String inputWinningNumber) {   // 리턴 값 ex) [해산물파스타-2,초코케이크-1]
        return Stream.of(inputWinningNumber.split(COMMA_SPLIT)).toList();
    }

    public static List<String> separateMenusAndCount(String menus) {    // 리턴 값 ex) [해산물파스타,2]
        return Stream.of(menus.split(MINUS_SIGN_SPLIT)).toList();
    }
}
