package christmas.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static christmas.util.ConvertUtil.convertIntPriceToStringCommaPrice;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ConvertUtilTest {

    @Test
    @DisplayName("숫자 -> 금액으로 변경")
    public void convertIntPriceToStringCommaPriceSuccess() throws Exception {
        //given
        int price = 100_000;

        //when
        String stringCommaPrice = convertIntPriceToStringCommaPrice(price);

        //then
        assertThat(stringCommaPrice).isEqualTo("100,000");
    }
}
