package christmas.constant;

import java.util.regex.Pattern;

public class ValidateConstant {

    public static final String DIGIT_VALIDATE_REGEX = "-?[0-9]+";
    public static final int START_DATE_RANGE = 1;
    public static final int END_DATE_RANGE = 31;

    public static boolean isNotDigit(String inputString) {
        return !Pattern.compile(DIGIT_VALIDATE_REGEX).matcher(inputString).matches();
    }
}
