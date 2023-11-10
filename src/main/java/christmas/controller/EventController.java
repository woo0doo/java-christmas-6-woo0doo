package christmas.controller;

import christmas.service.ValidateService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final ValidateService validateService = new ValidateService();

    public void run() {
        startAndDateOfVisitProcess();
    }

    private void startAndDateOfVisitProcess() {
        printStartMessage();
        inputDateOfVisit();
    }

    private void printStartMessage() {
        outputView.printStartMessage();
    }

    private void inputDateOfVisit() {
        try {
            String inputDateOfVisit = inputView.inputDateOfVisit();
            validateService.validateInputDateOfVisit(inputDateOfVisit);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            inputDateOfVisit();
        }
    }
}
