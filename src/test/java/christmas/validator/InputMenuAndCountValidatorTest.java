package christmas.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static christmas.constant.ErrorConstant.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class InputMenuAndCountValidatorTest {

    InputMenuAndCountValidator inputMenuAndCountValidator = new InputMenuAndCountValidator();

    @Test
    @DisplayName("메뉴와 개수 입력 - 성공")
    public void inputMenuAndCountSuccess() throws Exception {
        //given
        String inputMenuAndCount = "바비큐립-1,레드와인-1";
        //when
        assertDoesNotThrow(() -> inputMenuAndCountValidator.validateMenusAndCounts(inputMenuAndCount));
    }
    @Test
    @DisplayName("메뉴와 개수 입력 - 실패(존재하지 않는 메뉴)")
    public void inputMenuAndCountNotExistMenu() throws Exception {
        //given
        String inputMenuAndCount = "먹을거-1";
        //when - then
        assertThatThrownBy(() -> inputMenuAndCountValidator.validateMenusAndCounts(inputMenuAndCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_PREFIX + VALIDATE_CORRECT_MENU_ERROR_MESSAGE);
    }

    @Test
    @DisplayName("메뉴와 개수 입력 - 실패(맞지 않는 형식)")
    public void inputMenuAndCountNotCorrectFormat() throws Exception {
        //given
        String inputMenuAndCount = "먹을거 하나";

        //when - then
        assertThatThrownBy(() -> inputMenuAndCountValidator.validateMenusAndCounts(inputMenuAndCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_PREFIX + VALIDATE_CORRECT_MENU_ERROR_MESSAGE);
    }

    @Test
    @DisplayName("메뉴와 개수 입력 - 실패(중복된 메뉴 주문)")
    public void inputMenuAndCountDuplicateOrder() throws Exception {
        //given
        String inputMenuAndCount = "바비큐립-1,바비큐립-1";

        //when - then
        assertThatThrownBy(() -> inputMenuAndCountValidator.validateMenusAndCounts(inputMenuAndCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_PREFIX + VALIDATE_CORRECT_MENU_ERROR_MESSAGE);
    }

    @Test
    @DisplayName("메뉴와 개수 입력 - 실패(개수가 양수 X)")
    public void inputMenuAndCountLessThanOne() throws Exception {
        //given
        String inputMenuAndCount = "바비큐립-0";

        //when - then
        assertThatThrownBy(() -> inputMenuAndCountValidator.validateMenusAndCounts(inputMenuAndCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_PREFIX + VALIDATE_CORRECT_MENU_ERROR_MESSAGE);
    }

    @Test
    @DisplayName("메뉴와 개수 입력 - 실패(음료만 주문)")
    public void inputMenuAndCountOnlyBeverage() throws Exception {
        //given
        String inputMenuAndCount = "제로콜라-1";

        //when - then
        assertThatThrownBy(() -> inputMenuAndCountValidator.validateMenusAndCounts(inputMenuAndCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_PREFIX + ONLY_BEVERAGE_ERROR_MESSAGE);
    }

    @Test
    @DisplayName("메뉴와 개수 입력 - 실패(20개 이상 주문)")
    public void inputMenuAndCountOverTwenty() throws Exception {
        //given
        String inputMenuAndCount = "바비큐립-10,초코케이크-5,레드와인-6";

        //when - then
        assertThatThrownBy(() -> inputMenuAndCountValidator.validateMenusAndCounts(inputMenuAndCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_PREFIX + OVER_MAX_COUNT_SIZE_ERROR_MESSAGE);
    }
}
