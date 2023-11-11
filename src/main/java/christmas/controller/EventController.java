package christmas.controller;

import christmas.model.Menu;
import christmas.service.EventService;
import christmas.service.ValidateService;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.List;
import java.util.Map;

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
        printEventBenefitPreviewMessage();
        printOrderMenus();
        printTotalOrderPriceBeforeDiscount();
    }

    private void printTotalOrderPriceBeforeDiscount() {
        String stringCommaTotalOrderPriceBeforeDiscount = eventService.getStringCommaTotalOrderPriceBeforeDiscount();
        outputView.printTotalOrderPriceBeforeDiscount(stringCommaTotalOrderPriceBeforeDiscount);
    }

    private void printOrderMenus() {
        List<Menu> menus = eventService.getMenus();
        Map<Menu, Integer> menusAndCounts = eventService.getPersonMenusAndCounts();
        outputView.printOrderMenus(menus, menusAndCounts);
    }

    private void MenusAndCountsProcess() {
        printAskOrderMenusAndCounts();
        inputOrdersAndCountsAndInitEvent();
    }

    private void printEventBenefitPreviewMessage() {
        outputView.printEventBenefitPreviewMessage();
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
