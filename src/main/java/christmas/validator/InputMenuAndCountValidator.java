package christmas.validator;

import christmas.model.Menu;

import java.util.ArrayList;
import java.util.List;

import static christmas.constant.ErrorConstant.*;
import static christmas.constant.ValidateConstant.isNotDigit;
import static christmas.util.SeparationUtil.separateMenus;
import static christmas.util.SeparationUtil.separateMenusAndCount;

public class InputMenuAndCountValidator {

    boolean isNotOnlyBeverage = false;
    List<Menu> menus = new ArrayList<>();

    public void validateMenusAndCounts(String inputMenusAndCounts) {
        List<String> menusAndCounts = separateMenus(inputMenusAndCounts);
        for (String menusAndCount : menusAndCounts) {
            List<String> separateMenusAndCount = separateMenusAndCount(menusAndCount);
            Menu menu = Menu.StringToEnum(separateMenusAndCount.get(0));
            checkDuplicateMenu(menus, menu);
            containBesidesBeverage(menu.getCourse());
            menus.add(menu);
            if (isNotDigit(separateMenusAndCount.get(1)))
                throw new IllegalArgumentException(ERROR_PREFIX + VALIDATE_CORRECT_MENU_ERROR_MESSAGE);
        }
        checkNotOnlyBeverage(isNotOnlyBeverage);
    }

    private void checkNotOnlyBeverage(boolean isNotOnlyBeverage) {
        if (!isNotOnlyBeverage)
            throw new IllegalArgumentException(ERROR_PREFIX + ONLY_BEVERAGE_ERROR_MESSAGE);
    }

    private void containBesidesBeverage(Menu.Course course) {
        if (!course.equals(Menu.Course.BEVERAGE))
            isNotOnlyBeverage = true;
    }

    private void checkDuplicateMenu(List<Menu> menus, Menu menu) {
        if (menus.contains(menu))
            throw new IllegalArgumentException(ERROR_PREFIX + VALIDATE_CORRECT_MENU_ERROR_MESSAGE);
    }

    public void init() {
        this.isNotOnlyBeverage = false;
        this.menus = new ArrayList<>();
    }
}
