package christmas.service;

import christmas.validator.InputDateOfVisitValidator;
import christmas.validator.InputMenuAndCountValidator;

public class ValidateService {

    private final InputDateOfVisitValidator inputDateOfVisitValidator = new InputDateOfVisitValidator();
    private final InputMenuAndCountValidator inputMenuAndCountValidator = new InputMenuAndCountValidator();

    public void validateInputDateOfVisit(String inputDateOfVisit) {
        inputDateOfVisitValidator.validateDateOfVisit(inputDateOfVisit);
    }

    public void validateInputMenusAndCounts(String inputMenusAndCounts) {
        inputMenuAndCountValidator.init();
        inputMenuAndCountValidator.validateMenusAndCounts(inputMenusAndCounts);
    }
}
