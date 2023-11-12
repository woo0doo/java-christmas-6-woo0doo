package christmas.model;

import java.util.Map;

import static christmas.constant.ErrorConstant.ERROR_PREFIX;
import static christmas.constant.ErrorConstant.OVER_MAX_COUNT_SIZE_ERROR_MESSAGE;

public class Person {

    private int dateOfVisit;
    Map<Menu, Integer> menuAndCount;
    int totalCount;
    int totalOrderPriceBeforeDiscount;
    boolean isPresentedChampagne;
    int totalDiscountPrice;

    private static final int MAX_COUNT_SIZE = 20;

    public int getDateOfVisit() {
        return dateOfVisit;
    }

    public Map<Menu, Integer> getMenuAndCount() {
        return menuAndCount;
    }

    public int getTotalOrderPriceBeforeDiscount() {
        return totalOrderPriceBeforeDiscount;
    }

    public boolean getIsPresentedChampagne() {
        return isPresentedChampagne;
    }

    public int getTotalDiscountPrice() {
        return totalDiscountPrice;
    }

    public void setDateOfVisit(int dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
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

    public void setPresentedChampagne(boolean presentedChampagne) {
        isPresentedChampagne = presentedChampagne;
    }

    public void setTotalDiscountPrice(int totalDiscountPrice) {
        this.totalDiscountPrice = totalDiscountPrice;
    }

    private void validateTotalCountOverMaxCount(int totalCount) {
        if (totalCount > MAX_COUNT_SIZE) throw new IllegalArgumentException(ERROR_PREFIX + OVER_MAX_COUNT_SIZE_ERROR_MESSAGE);
    }
}
