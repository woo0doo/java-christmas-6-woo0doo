package christmas.view;

import christmas.model.Menu;

import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String INTRODUCE_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String ASK_DATE_OF_VISIT_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String ASK_ORDER_MENUS_AND_COUNTS_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    private static final String EVENT_BENEFIT_PREVIEW_MESSAGE = "12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String BLANK_LINE = "";
    private static final String ORDER_MENU_MESSAGE = "<주문 메뉴>";
    private static final String ORDER_MENU_AND_COUNT = "%s %d개\n";
    private static final String TOTAL_ORDER_PRICE_BEFORE_DISCOUNT_MESSAGE = "<할인 전 총주문 금액>";
    private static final String PRICE_WON = "%s원\n";
    private static final String GIFT_MENU_MESSAGE = "<증정 메뉴>";
    private static final String BENEFITS_DETAILS = "<혜택 내역>";
    public static final String NO_GIFT_MESSAGE = "없음";
    private static final String TOTAL_DISCOUNT_PRICE_MESSAGE = "<총혜택 금액>";
    private static final String MINUS_SIGN = "-";


    public void printStartMessage() {
        printIntroduceMessage();
        printAskDateOfVisitMessage();
    }

    public void printAskOrderMenusAndCounts() {
        System.out.println(ASK_ORDER_MENUS_AND_COUNTS_MESSAGE);
    }

    public void printEventBenefitPreviewMessage() {
        System.out.println(EVENT_BENEFIT_PREVIEW_MESSAGE);
        printBlankLine();
    }

    public void printOrderMenus(List<Menu> menus, Map<Menu, Integer> menusAndCounts) {
        System.out.println(ORDER_MENU_MESSAGE);
        for (Menu menu : menus) {
            System.out.printf(ORDER_MENU_AND_COUNT, menu.getMenuKoreaName(), menusAndCounts.get(menu));
        }
        printBlankLine();
    }

    public void printTotalOrderPriceBeforeDiscount(String stringCommaTotalOrderPriceBeforeDiscount) {
        System.out.println(TOTAL_ORDER_PRICE_BEFORE_DISCOUNT_MESSAGE);
        System.out.printf(PRICE_WON, stringCommaTotalOrderPriceBeforeDiscount);
        printBlankLine();
    }

    public void printGiftMenu(String giftMenuMessage) {
        System.out.println(GIFT_MENU_MESSAGE);
        System.out.println(giftMenuMessage);
        printBlankLine();
    }

    public void printDiscountDetail(List<String> discountDetails) {
        System.out.println(BENEFITS_DETAILS);
        if (discountDetails.size() == 0) {
            System.out.println(NO_GIFT_MESSAGE);
            return;
        }
        for (String discountDetail : discountDetails) {
            System.out.println(discountDetail);
        }
        printBlankLine();
    }
    public void printTotalDiscountPrice(String stringCommaTotalDiscountPrice) {
        System.out.println(TOTAL_DISCOUNT_PRICE_MESSAGE);
        System.out.printf(MINUS_SIGN + PRICE_WON, stringCommaTotalDiscountPrice);
        printBlankLine();
    }

    private void printBlankLine() {
        System.out.println(BLANK_LINE);
    }

    private void printIntroduceMessage() {
        System.out.println(INTRODUCE_MESSAGE);
    }

    private void printAskDateOfVisitMessage() {
        System.out.println(ASK_DATE_OF_VISIT_MESSAGE);
    }
}
