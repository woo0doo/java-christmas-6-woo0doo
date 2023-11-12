package christmas.model;

import java.util.Map;

public class Person {

    Map<Menu, Integer> menuAndCount;
    int totalCount;
    int totalOrderPriceBeforeDiscount;
    boolean isPresentedChampagne;
    int totalDiscountPrice;
    private int dateOfVisit;

    public int getDateOfVisit() {
        return dateOfVisit;
    }

    public void setDateOfVisit(int dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
    }

    public Map<Menu, Integer> getMenuAndCount() {
        return menuAndCount;
    }

    public void setMenuAndCount(Map<Menu, Integer> menuAndCount) {
        this.menuAndCount = menuAndCount;
    }

    public int getTotalOrderPriceBeforeDiscount() {
        return totalOrderPriceBeforeDiscount;
    }

    public void setTotalOrderPriceBeforeDiscount(int totalOrderPriceBeforeDiscount) {
        this.totalOrderPriceBeforeDiscount = totalOrderPriceBeforeDiscount;
    }

    public boolean getIsPresentedChampagne() {
        return isPresentedChampagne;
    }

    public int getTotalDiscountPrice() {
        return totalDiscountPrice;
    }

    public void setTotalDiscountPrice(int totalDiscountPrice) {
        this.totalDiscountPrice = totalDiscountPrice;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setPresentedChampagne(boolean presentedChampagne) {
        isPresentedChampagne = presentedChampagne;
    }
}
