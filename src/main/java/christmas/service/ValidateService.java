package christmas.service;

import christmas.validator.InputValidator;

public class ValidateService {

    private final InputValidator inputValidator = new InputValidator();

    public void validateInputDateOfVisit(String inputDateOfVisit) {
        inputValidator.validateDateOfVisit(inputDateOfVisit);
    }
}
