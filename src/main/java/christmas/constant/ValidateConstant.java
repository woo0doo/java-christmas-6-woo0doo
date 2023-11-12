package christmas.constant;

import java.util.regex.Pattern;

import static christmas.constant.ErrorConstant.ERROR_PREFIX;
import static christmas.constant.ErrorConstant.VALIDATE_CORRECT_MENU_ERROR_MESSAGE;

public class ValidateConstant {

    public static final String DIGIT_VALIDATE_REGEX = "-?[0-9]+";
    public static final int START_DATE_RANGE = 1;
    public static final int END_DATE_RANGE = 31;

    public static void checkNotDigit(String inputString) {
        if (!Pattern.compile(DIGIT_VALIDATE_REGEX).matcher(inputString).matches())
            throw new IllegalArgumentException(ERROR_PREFIX + VALIDATE_CORRECT_MENU_ERROR_MESSAGE);
    }
}
