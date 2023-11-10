package christmas.validator;

import java.util.regex.Pattern;

import static christmas.constant.ErrorConstant.ERROR_PREFIX;

public class InputValidator {
    private static final String VALIDATE_DATE_OF_VISIT_ERROR_MESSAGE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final String DIGIT_VALIDATE_REGEX = "-?[0-9]+";
    private static final int START_DATE_RANGE = 1;
    private static final int END_DATE_RANGE = 31;

    public void validateDateOfVisit(String inputDateOfVisit) {
        if (isNotDigitOrNotCorrectRangeOfDate(inputDateOfVisit)) {
            throw new IllegalArgumentException(ERROR_PREFIX + VALIDATE_DATE_OF_VISIT_ERROR_MESSAGE);
        }
    }

    private boolean isNotDigitOrNotCorrectRangeOfDate(String inputDateOfVisit) {
        if (Pattern.compile(DIGIT_VALIDATE_REGEX).matcher(inputDateOfVisit).matches()) {
            return true;
        }

        int dateOfVisit = Integer.parseInt(inputDateOfVisit);
        return dateOfVisit < START_DATE_RANGE || dateOfVisit > END_DATE_RANGE;
    }
}
