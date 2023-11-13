package christmas.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static christmas.constant.ErrorConstant.ERROR_PREFIX;
import static christmas.constant.ErrorConstant.VALIDATE_CORRECT_MENU_ERROR_MESSAGE;
import static christmas.model.Menu.stringToEnum;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class MenuTest {
    
    @Test
    @DisplayName("메뉴 존재 확인 - 성공(양송이수프)")
    public void stringToEnumButtonIsExistMushroomSoup() throws Exception {
        //given
        String inputMenu = "양송이수프";

        //when
        Menu menu = stringToEnum(inputMenu);

        //then
        assertThat(menu).isEqualTo(Menu.BUTTON_MUSHROOM_SOUP);
    }

    @Test
    @DisplayName("메뉴 존재 확인 - 성공(타파스)")
    public void stringToEnumButtonIsExistTapas() throws Exception {
        //given
        String inputMenu = "타파스";

        //when
        Menu menu = stringToEnum(inputMenu);

        //then
        assertThat(menu).isEqualTo(Menu.TAPAS);
    }

    @Test
    @DisplayName("메뉴 존재 확인 - 성공(시저샐러드)")
    public void stringToEnumButtonIsExistCaesarSalad() throws Exception {
        //given
        String inputMenu = "시저샐러드";

        //when
        Menu menu = stringToEnum(inputMenu);

        //then
        assertThat(menu).isEqualTo(Menu.CAESAR_SALAD);
    }

    @Test
    @DisplayName("메뉴 존재 확인 - 성공(티본스테이크)")
    public void stringToEnumButtonIsExistTBoneSteak() throws Exception {
        //given
        String inputMenu = "티본스테이크";

        //when
        Menu menu = stringToEnum(inputMenu);

        //then
        assertThat(menu).isEqualTo(Menu.T_BONE_STEAK);
    }

    @Test
    @DisplayName("메뉴 존재 확인 - 성공(바비큐립)")
    public void stringToEnumButtonIsExistBBQRib() throws Exception {
        //given
        String inputMenu = "바비큐립";

        //when
        Menu menu = stringToEnum(inputMenu);

        //then
        assertThat(menu).isEqualTo(Menu.BBQ_RIB);
    }

    @Test
    @DisplayName("메뉴 존재 확인 - 성공(해산물파스타)")
    public void stringToEnumButtonIsExistSeafoodPasta() throws Exception {
        //given
        String inputMenu = "해산물파스타";

        //when
        Menu menu = stringToEnum(inputMenu);

        //then
        assertThat(menu).isEqualTo(Menu.SEAFOOD_PASTA);
    }

    @Test
    @DisplayName("메뉴 존재 확인 - 성공(크리스마스파스타)")
    public void stringToEnumButtonIsExistChristmasPasta() throws Exception {
        //given
        String inputMenu = "크리스마스파스타";

        //when
        Menu menu = stringToEnum(inputMenu);

        //then
        assertThat(menu).isEqualTo(Menu.CHRISTMAS_PASTA);
    }

    @Test
    @DisplayName("메뉴 존재 확인 - 성공(초코케이크)")
    public void stringToEnumButtonIsExistChocolateCake() throws Exception {
        //given
        String inputMenu = "초코케이크";

        //when
        Menu menu = stringToEnum(inputMenu);

        //then
        assertThat(menu).isEqualTo(Menu.CHOCOLATE_CAKE);
    }

    @Test
    @DisplayName("메뉴 존재 확인 - 성공(아이스크림)")
    public void stringToEnumButtonIsExistIceCream() throws Exception {
        //given
        String inputMenu = "아이스크림";

        //when
        Menu menu = stringToEnum(inputMenu);

        //then
        assertThat(menu).isEqualTo(Menu.ICECREAM);
    }

    @Test
    @DisplayName("메뉴 존재 확인 - 성공(제로콜라)")
    public void stringToEnumButtonIsExistZeroCoke() throws Exception {
        //given
        String inputMenu = "제로콜라";

        //when
        Menu menu = stringToEnum(inputMenu);

        //then
        assertThat(menu).isEqualTo(Menu.ZERO_COKE);
    }

    @Test
    @DisplayName("메뉴 존재 확인 - 성공(레드와인)")
    public void stringToEnumButtonIsExistRedWine() throws Exception {
        //given
        String inputMenu = "레드와인";

        //when
        Menu menu = stringToEnum(inputMenu);

        //then
        assertThat(menu).isEqualTo(Menu.RED_WINE);
    }

    @Test
    @DisplayName("메뉴 존재 확인 - 성공(샴페인)")
    public void stringToEnumButtonIsExistChampagne() throws Exception {
        //given
        String inputMenu = "샴페인";

        //when
        Menu menu = stringToEnum(inputMenu);

        //then
        assertThat(menu).isEqualTo(Menu.CHAMPAGNE);
    }

    @Test
    @DisplayName("메뉴 존재 확인 - 실패(존재하지 않는 메뉴)")
    public void stringToEnumButtonIsNotExistMenu() throws Exception {
        //given
        String inputMenu = "치킨";

        //when - then
        assertThatThrownBy(() -> stringToEnum(inputMenu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_PREFIX + VALIDATE_CORRECT_MENU_ERROR_MESSAGE);
    }
}
