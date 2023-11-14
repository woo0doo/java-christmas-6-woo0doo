package christmas.service;

import christmas.model.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class EventServiceTest {

    EventService eventService = new EventService();

    @Test
    @DisplayName("person에 메뉴 정상 저장되는지 확인")
    public void getPersonMenusAndCountsTest() throws Exception {
        //given
        String inputMenusAndCounts = "티본스테이크-1,초코케이크-2";
        eventService.initEvent(inputMenusAndCounts);

        //when
        Map<Menu, Integer> personMenusAndCounts = eventService.getPersonMenusAndCounts();

        //then
        assertThat(personMenusAndCounts.get(Menu.T_BONE_STEAK)).isEqualTo(1);
        assertThat(personMenusAndCounts.get(Menu.CHOCOLATE_CAKE)).isEqualTo(2);
    }

    @Test
    @DisplayName("샴페인 증정 여부 - 참 반환")
    public void getIsPresentedChampagneTrueTest() throws Exception {
        //given
        String inputMenusAndCount = "크리스마스파스타-4,초코케이크-1,아이스크림-1";   //12만원
        eventService.initEvent(inputMenusAndCount);

        //when
        boolean isPresentedChampagne = eventService.getIsPresentedChampagne();

        //then
        assertThat(isPresentedChampagne).isTrue();
    }

    @Test
    @DisplayName("샴페인 증정 여부 - 거짓 반환")
    public void getIsPresentedChampagneFalseTest() throws Exception {
        //given
        String inputMenusAndCount = "크리스마스파스타-4,초코케이크-1";   //11만 5천원
        eventService.initEvent(inputMenusAndCount);

        //when
        boolean isPresentedChampagne = eventService.getIsPresentedChampagne();

        //then
        assertThat(isPresentedChampagne).isFalse();
    }
}
