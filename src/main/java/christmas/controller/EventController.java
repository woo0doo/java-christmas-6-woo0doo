package christmas.controller;

import christmas.service.EventService;
import christmas.service.ValidateService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final ValidateService validateService = new ValidateService();
    private final EventService eventService = new EventService();

    private String inputDateOfVisit;
    private String inputMenusAndCounts;

    public void run() {
        startAndDateOfVisitProcess();
        MenusAndCountsProcess();
    }

    private void MenusAndCountsProcess() {
        printAskOrderMenusAndCounts();
        inputOrdersAndCountsAndInitEvent();
    }

    private void inputOrdersAndCountsAndInitEvent() {
        try {
            inputMenusAndCounts = inputView.inputMenusAndCounts();
            validateService.validateInputMenusAndCounts(inputMenusAndCounts);
            eventService.initEvent(inputMenusAndCounts);
        } catch (IllegalArgumentException e) {
            eventService.initMenus();
            System.out.println(e.getMessage());
            inputOrdersAndCountsAndInitEvent();
        }
    }

    private void printAskOrderMenusAndCounts() {
        outputView.printAskOrderMenusAndCounts();
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
            inputDateOfVisit = inputView.inputDateOfVisit();
            validateService.validateInputDateOfVisit(inputDateOfVisit);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            inputDateOfVisit();
        }
    }
}
