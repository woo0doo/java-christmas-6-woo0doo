package christmas.view;

public class OutputView {

    private static final String INTRODUCE_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String ASK_DATE_OF_VISIT_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String ASK_ORDER_MENUS_AND_COUNTS = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    public void printStartMessage() {
        printIntroduceMessage();
        printAskDateOfVisitMessage();
    }

    public void printAskOrderMenusAndCounts() {
        System.out.println(ASK_ORDER_MENUS_AND_COUNTS);
    }

    private void printIntroduceMessage() {
        System.out.println(INTRODUCE_MESSAGE);
    }

    private void printAskDateOfVisitMessage() {
        System.out.println(ASK_DATE_OF_VISIT_MESSAGE);
    }
}
