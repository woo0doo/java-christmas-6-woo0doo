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
        printGiftMenu();
        printDiscountDetail();
        printTotalDiscountPrice();
        printExpectPaymentPrice();
        printEventBadge();
    }

    private void printEventBadge() {
        String eventBadge = eventService.getEventBadge();
        outputView.printEventBadge(eventBadge);
    }

    private void printExpectPaymentPrice() {
        String stringCommaExpectPaymentPrice = eventService.getStringCommaExpectPaymentPrice();
        outputView.printExpectPaymentPrice(stringCommaExpectPaymentPrice);
    }

    private void printTotalDiscountPrice() {
        String stringCommaTotalDiscountPrice = eventService.getStringCommaTotalDiscountPrice();
        outputView.printTotalDiscountPrice(stringCommaTotalDiscountPrice);
    }

    private void printDiscountDetail() {
        List<String> discountDetails = eventService.getDiscountDetails();
        outputView.printDiscountDetail(discountDetails);
    }

    private void printGiftMenu() {
        String giftMenuMessage = eventService.getGiftMenu();
        outputView.printGiftMenu(giftMenuMessage);
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
        eventService.initEvent(inputMenusAndCounts);
    }

    private void printEventBenefitPreviewMessage() {
        outputView.printEventBenefitPreviewMessage();
    }

    private void inputOrdersAndCountsAndInitEvent() {
        try {
            inputMenusAndCounts = inputView.inputMenusAndCounts();
            validateService.validateInputMenusAndCounts(inputMenusAndCounts);
        } catch (IllegalArgumentException e) {
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
        eventService.setDateOfVisit(inputDateOfVisit);
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
