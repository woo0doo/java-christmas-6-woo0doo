package christmas.model;

import java.util.Map;

import static christmas.constant.ErrorConstant.ERROR_PREFIX;
import static christmas.constant.ErrorConstant.OVER_MAX_COUNT_SIZE_ERROR_MESSAGE;

public class Person {

    Map<Menu, Integer> menuAndCount;
    int totalCount;
    int totalOrderPriceBeforeDiscount;

    private static final int MAX_COUNT_SIZE = 20;

    public Map<Menu, Integer> getMenuAndCount() {
        return menuAndCount;
    }

    public int getTotalOrderPriceBeforeDiscount() {
        return totalOrderPriceBeforeDiscount;
    }

    public void setMenuAndCount(Map<Menu, Integer> menuAndCount) {
        this.menuAndCount = menuAndCount;
    }

    public void setTotalCount(int totalCount) {
        validateTotalCountOverMaxCount(totalCount);
        this.totalCount = totalCount;
    }

    public void setTotalOrderPriceBeforeDiscount(int totalOrderPriceBeforeDiscount) {
        this.totalOrderPriceBeforeDiscount = totalOrderPriceBeforeDiscount;
    }

    private void validateTotalCountOverMaxCount(int totalCount) {
        if (totalCount > MAX_COUNT_SIZE) throw new IllegalArgumentException(ERROR_PREFIX + OVER_MAX_COUNT_SIZE_ERROR_MESSAGE);
    }
}
