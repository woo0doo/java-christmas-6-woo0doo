package christmas.validator;

import static christmas.constant.ErrorConstant.ERROR_PREFIX;
import static christmas.constant.ErrorConstant.VALIDATE_DATE_OF_VISIT_ERROR_MESSAGE;
import static christmas.constant.ValidateConstant.*;
import static christmas.util.ConvertUtil.convertStringToInt;

public class InputDateOfVisitValidator {



    public void validateDateOfVisit(String inputDateOfVisit) {
        checkNotDigit(inputDateOfVisit);
        checkNotCorrectRangeOfDate(inputDateOfVisit);
    }

    private void checkNotCorrectRangeOfDate(String inputDateOfVisit) {
        int dateOfVisit = convertStringToInt(inputDateOfVisit);
        if (dateOfVisit < START_DATE_RANGE || dateOfVisit > END_DATE_RANGE)
            throw new IllegalArgumentException(ERROR_PREFIX + VALIDATE_DATE_OF_VISIT_ERROR_MESSAGE);
    }

}
