package christmas.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static christmas.constant.ErrorConstant.ERROR_PREFIX;
import static christmas.constant.ErrorConstant.VALIDATE_DATE_OF_VISIT_ERROR_MESSAGE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class InputDateOfVisitValidatorTest {

    InputDateOfVisitValidator inputDateOfVisitValidator = new InputDateOfVisitValidator();

    @Test
    @DisplayName("날짜 입력 - 성공")
    public void inputDateOfVisitSuccess() throws Exception {
        //given
        String inputDateOfVisit = "5";

        //when - then
        assertDoesNotThrow(() -> inputDateOfVisitValidator.validateDateOfVisit(inputDateOfVisit));
    }

    @Test
    @DisplayName("날짜 입력 - 실패(숫자 X)")
    public void inputDateOfVisitIsNotDigit() throws Exception {
        //given
        String inputDateOfVisit = "a";

        //when - then
        assertThatThrownBy(() -> inputDateOfVisitValidator.validateDateOfVisit(inputDateOfVisit))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_PREFIX + VALIDATE_DATE_OF_VISIT_ERROR_MESSAGE);
    }

    @Test
    @DisplayName("날짜 입력 - 실패(범위 초과)")
    public void inputDateOfVisitIsNotCorrectRangeOver() throws Exception {
        //given
        String inputDateOfVisit = "32";

        //when = then
        assertThatThrownBy(() -> inputDateOfVisitValidator.validateDateOfVisit(inputDateOfVisit))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_PREFIX + VALIDATE_DATE_OF_VISIT_ERROR_MESSAGE);
    }

    @Test
    @DisplayName("날짜 입력 - 실패(범위 미만)")
    public void inputDateOfVisitIsNotCorrectRangeLess() throws Exception {
        //given
        String inputDateOfVisit = "0";

        //when = then
        assertThatThrownBy(() -> inputDateOfVisitValidator.validateDateOfVisit(inputDateOfVisit))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_PREFIX + VALIDATE_DATE_OF_VISIT_ERROR_MESSAGE);
    }
}
