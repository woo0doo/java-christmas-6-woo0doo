package christmas.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static christmas.util.SeparationUtil.separateMenus;
import static christmas.util.SeparationUtil.separateMenusAndCount;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SeparationUtilTest {

    @Test
    @DisplayName("메뉴별로 구분 - 성공")
    public void separateMenusTest() throws Exception {
        //given
        String input = "해산물파스타-1,초코케이크-2,아이스크림-3";

        //when
        List<String> separateMenus = separateMenus(input);

        //then
        assertThat(separateMenus.size()).isEqualTo(3);
        assertThat(separateMenus.get(0)).isEqualTo("해산물파스타-1");
        assertThat(separateMenus.get(1)).isEqualTo("초코케이크-2");
        assertThat(separateMenus.get(2)).isEqualTo("아이스크림-3");
    }

    @Test
    @DisplayName("메뉴/개수로 분리 - 성공")
    public void separateMenusAndCountTest() throws Exception {
        //given
        String input = "해산물파스타-1";

        //when
        List<String> separateMenusAndCount = separateMenusAndCount(input);

        //then
        assertThat(separateMenusAndCount.size()).isEqualTo(2);
        assertThat(separateMenusAndCount.get(0)).isEqualTo("해산물파스타");
        assertThat(separateMenusAndCount.get(1)).isEqualTo("1");
    }
}
