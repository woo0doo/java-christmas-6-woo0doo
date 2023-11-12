package christmas.validator;

import christmas.model.Menu;

import java.util.ArrayList;
import java.util.List;

import static christmas.constant.ErrorConstant.*;
import static christmas.constant.NumberConstant.MAX_COUNT_SIZE;
import static christmas.constant.ValidateConstant.isNotDigit;
import static christmas.util.ConvertUtil.convertStringToInt;
import static christmas.util.SeparationUtil.separateMenus;
import static christmas.util.SeparationUtil.separateMenusAndCount;

public class InputMenuAndCountValidator {

    boolean isNotOnlyBeverage = false;
    List<Menu> menus = new ArrayList<>();
    int totalCount = 0;

    public void validateMenusAndCounts(String inputMenusAndCounts) {
        List<String> menusAndCounts = separateMenus(inputMenusAndCounts);
        for (String menusAndCount : menusAndCounts) {
            List<String> separateMenusAndCount = separateMenusAndCount(menusAndCount);
            Menu menu = Menu.StringToEnum(separateMenusAndCount.get(0));
            checkDuplicateMenu(menus, menu);
            containBesidesBeverage(menu.getCourse());
            menus.add(menu);
            String inputCount = separateMenusAndCount.get(1);
            if (isNotDigit(inputCount) || isNotPositive(inputCount))
                throw new IllegalArgumentException(ERROR_PREFIX + VALIDATE_CORRECT_MENU_ERROR_MESSAGE);
            addCount(inputCount);
        }
        checkNotOnlyBeverage(isNotOnlyBeverage);
        checkTotalCountOverMaxCount(totalCount);
    }

    private boolean isNotPositive(String inputCount) {
        int count = convertStringToInt(inputCount);
        return count<1;
    }

    private void addCount(String inputCount) {
        int count = convertStringToInt(inputCount);
        totalCount += count;
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

    private void checkTotalCountOverMaxCount(int totalCount) {
        if (totalCount > MAX_COUNT_SIZE)
            throw new IllegalArgumentException(ERROR_PREFIX + OVER_MAX_COUNT_SIZE_ERROR_MESSAGE);
    }
}
