package christmas.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static christmas.model.Badge.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BadgeTest {

    @Test
    @DisplayName("이벤트 배지 산타 증정 여부 - O")
    public void isOverSantaDiscountPriceRangeTrue() throws Exception {
        //given
        int totalDiscountPrice = 20_000;

        //when
        boolean isPresented = isOverSantaDiscountPriceRange(totalDiscountPrice);

        //then
        assertThat(isPresented).isTrue();
    }

    @Test
    @DisplayName("이벤트 배지 산타 증정 여부 - X (가격 부족)")
    public void isOverSantaDiscountPriceRangeFalse() throws Exception {
        //given
        int totalDiscountPrice = 19_999;

        //when
        boolean isPresented = isOverSantaDiscountPriceRange(totalDiscountPrice);

        //then
        assertThat(isPresented).isFalse();
    }

    @Test
    @DisplayName("이벤트 배지 트리 증정 여부 - O")
    public void isOverTreeDiscountPriceRangeTrue() throws Exception {
        //given
        int totalDiscountPrice = 10_000;

        //when
        boolean isPresented = isOverTreeDiscountPriceRange(totalDiscountPrice);

        //then
        assertThat(isPresented).isTrue();
    }

    @Test
    @DisplayName("이벤트 배지 트리 증정 여부 - X (가격 부족)")
    public void isOverTreeDiscountPriceRangeFalse() throws Exception {
        //given
        int totalDiscountPrice = 9_999;

        //when
        boolean isPresented = isOverTreeDiscountPriceRange(totalDiscountPrice);

        //then
        assertThat(isPresented).isFalse();
    }

    @Test
    @DisplayName("이벤트 배지 스타 증정 여부 - 성공")
    public void isOverStarDiscountPriceRangeTrue() throws Exception {
        //given
        int totalDiscountPrice = 5_000;

        //when
        boolean isPresented = isOverStarDiscountPriceRange(totalDiscountPrice);

        //then
        assertThat(isPresented).isTrue();
    }

    @Test
    @DisplayName("이벤트 배지 스타 증정 여부 - X (가격 부족)")
    public void isOverStarDiscountPriceRangeFalse() throws Exception {
        //given
        int totalDiscountPrice = 4_999;

        //when
        boolean isPresented = isOverStarDiscountPriceRange(totalDiscountPrice);

        //then
        assertThat(isPresented).isFalse();
    }
}
