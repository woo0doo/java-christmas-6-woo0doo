package christmas.validator;

import christmas.model.Menu;

import java.util.List;
import java.util.regex.Pattern;

import static christmas.constant.ErrorConstant.*;
import static christmas.util.SeparationUtil.separateMenus;
import static christmas.util.SeparationUtil.separateMenusAndCount;

public class InputValidator {
    private static final String DIGIT_VALIDATE_REGEX = "-?[0-9]+";
    private static final int START_DATE_RANGE = 1;
    private static final int END_DATE_RANGE = 31;

    public void validateDateOfVisit(String inputDateOfVisit) {
        if (isNotDigit(inputDateOfVisit) || isNotCorrectRangeOfDate(inputDateOfVisit)) {
            throw new IllegalArgumentException(ERROR_PREFIX + VALIDATE_DATE_OF_VISIT_ERROR_MESSAGE);
        }
    }

    public void validateMenusAndCounts(String inputMenusAndCounts) {
        List<String> menusAndCounts = separateMenus(inputMenusAndCounts);
        for (String menusAndCount : menusAndCounts) {
            List<String> separateMenusAndCount = separateMenusAndCount(menusAndCount);
            Menu.StringToEnum(separateMenusAndCount.get(0));
            if (isNotDigit(separateMenusAndCount.get(1))) {
                throw new IllegalArgumentException(ERROR_PREFIX + VALIDATE_CORRECT_MENU_ERROR_MESSAGE);
            }
        }
    }

    private boolean isNotDigit(String inputString) {
        return !Pattern.compile(DIGIT_VALIDATE_REGEX).matcher(inputString).matches();
    }

    private boolean isNotCorrectRangeOfDate(String inputDateOfVisit) {
        int dateOfVisit = Integer.parseInt(inputDateOfVisit);
        return dateOfVisit < START_DATE_RANGE || dateOfVisit > END_DATE_RANGE;
    }
}
