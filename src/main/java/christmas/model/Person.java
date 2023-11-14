package christmas.model;

import java.util.Map;

import static christmas.constant.NumberConstant.*;
import static christmas.util.ConvertUtil.convertIntPriceToStringCommaPrice;
import static christmas.view.OutputView.*;

public class Person {

    private Map<Menu, Integer> menuAndCount;
    private int totalOrderPriceBeforeDiscount;
    private int totalDiscountPrice;
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

    public void setTotalOrderPriceBeforeDiscount(int totalOrderPriceBeforeDiscount) {
        this.totalOrderPriceBeforeDiscount = totalOrderPriceBeforeDiscount;
    }

    public int getTotalDiscountPrice() {
        return totalDiscountPrice;
    }

    public void setTotalDiscountPrice(int totalDiscountPrice) {
        this.totalDiscountPrice = totalDiscountPrice;
    }

    public boolean isPresentedChampagne() {
        return totalOrderPriceBeforeDiscount >= BASED_ON_OVER_PRICE_GIVE_GIFT_MENU;
    }

    public int getExpectPaymentPrice() {
        if (isPresentedChampagne())
            return totalOrderPriceBeforeDiscount - totalDiscountPrice + CHAMPAGNE_PRICE;
        return totalOrderPriceBeforeDiscount - totalDiscountPrice;
    }

    public String getStringCommaTotalDiscountPrice() {
        if (totalDiscountPrice == ZERO_VALUE) return STRING_ZERO_VALUE;
        return MINUS_SIGN +convertIntPriceToStringCommaPrice(totalDiscountPrice);
    }

    public String getStringCommaExpectPaymentPrice() {
        int expectPaymentPrice = getExpectPaymentPrice();
        return convertIntPriceToStringCommaPrice(expectPaymentPrice);
    }

    public String getStringCommaTotalOrderPriceBeforeDiscount() {
        return convertIntPriceToStringCommaPrice(totalOrderPriceBeforeDiscount);
    }

    public boolean isEventTargetPerson() {
        return totalOrderPriceBeforeDiscount >= TEN_THOUSAND;
    }
}
