package christmas.view;

public class OutputView {

    private static final String INTRODUCE_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String ASK_DATE_OF_VISIT_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";

    public void printStartMessage() {
        printIntroduceMessage();
        printAskDateOfVisitMessage();
    }

    private void printIntroduceMessage() {
        System.out.println(INTRODUCE_MESSAGE);
    }

    private void printAskDateOfVisitMessage() {
        System.out.println(ASK_DATE_OF_VISIT_MESSAGE);
    }
}
