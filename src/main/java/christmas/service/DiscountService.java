package christmas.service;

import christmas.model.December;
import christmas.model.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static christmas.constant.NumberConstant.*;
import static christmas.util.ConvertUtil.convertIntPriceToStringCommaPrice;
import static christmas.view.OutputView.*;

public class DiscountService {

    private final EventService eventService;
    int totalDiscountPrice = 0;
    List<String> discountDetailsMessage = new ArrayList<>();
    public DiscountService(EventService eventService) {
        this.eventService = eventService;
    }

    public List<String> getDiscountDetails() {
        List<Menu> menus = eventService.getMenus();
        int dateOfVisit = eventService.getDateOfVisit();
        Map<Menu, Integer> menusAndCounts = eventService.getPersonMenusAndCounts();
        addChristmasD_DayDiscountMessage(discountChristmasD_Day(dateOfVisit));
        discountWeekday(menusAndCounts, menus, dateOfVisit);
        discountWeekend(menusAndCounts, menus, dateOfVisit);
        discountStarDay(dateOfVisit);
        discountGiftEvent(eventService.getIsPresentedChampagne());
        eventService.setTotalDiscountPrice(totalDiscountPrice);
        return discountDetailsMessage;
    }

    private void discountGiftEvent(boolean isPresentedChampagne) {
        if (!isPresentedChampagne)
            return;

        totalDiscountPrice += CHAMPAGNE_PRICE;
        String stringCommaChampagneDiscountPrice = convertIntPriceToStringCommaPrice(CHAMPAGNE_PRICE);
        addDiscountPriceMessage(GIFT_DISCOUNT_MESSAGE, stringCommaChampagneDiscountPrice);
    }

    private void discountStarDay(int dateOfVisit) {
        if (!December.containDayInStarDay(dateOfVisit))
            return;

        totalDiscountPrice += STAR_DAY_DISCOUNT_PRICE;
        String stringCommaStarDayDiscountPrice = convertIntPriceToStringCommaPrice(STAR_DAY_DISCOUNT_PRICE);
        addDiscountPriceMessage(STAR_DISCOUNT_MESSAGE, stringCommaStarDayDiscountPrice);
    }

    private void discountWeekday(Map<Menu, Integer> menusAndCounts, List<Menu> menus, int dateOfVisit) {
        if (!December.containDayInWeekday(dateOfVisit))
            return;

        int dessertMenuNumber = countCourseMenu(menusAndCounts, menus, Menu.Course.DESSERT);
        if (isZeroValue(dessertMenuNumber)) return;

        int weekdayDiscountPrice = dessertMenuNumber * THIS_YEAR_NUMBER_VALUE;
        totalDiscountPrice += weekdayDiscountPrice;
        String stringCommaWeekdayDiscountPrice = convertIntPriceToStringCommaPrice(weekdayDiscountPrice);
        addDiscountPriceMessage(WEEKDAY_DISCOUNT_MESSAGE, stringCommaWeekdayDiscountPrice);
    }

    private void discountWeekend(Map<Menu, Integer> menusAndCounts, List<Menu> menus, int dateOfVisit) {
        if (!December.containDayInWeekend(dateOfVisit))
            return;

        int mainMenuNumber = countCourseMenu(menusAndCounts, menus, Menu.Course.MAIN);
        if (isZeroValue(mainMenuNumber)) return;

        int weekendDiscountPrice = mainMenuNumber * THIS_YEAR_NUMBER_VALUE;
        totalDiscountPrice += weekendDiscountPrice;
        String stringCommaWeekendDiscountPrice = convertIntPriceToStringCommaPrice(weekendDiscountPrice);
        addDiscountPriceMessage(WEEKEND_DISCOUNT_MESSAGE, stringCommaWeekendDiscountPrice);
    }

    private int countCourseMenu(Map<Menu, Integer> menusAndCounts, List<Menu> menus, Menu.Course course) {
        int dessertMenuNumber = ZERO_VALUE;

        for (Menu menu : menus) {
            if (menu.getCourse().equals(course))
                dessertMenuNumber += menusAndCounts.get(menu);
        }

        return dessertMenuNumber;
    }

    private void addChristmasD_DayDiscountMessage(int discountPrice) {
        if (isZeroValue(discountPrice))
            return;
        String stringCommaDiscountPrice = convertIntPriceToStringCommaPrice(discountPrice);
        addDiscountPriceMessage(CHRISTMAS_D_DAY_DISCOUNT_MESSAGE, stringCommaDiscountPrice);
    }

    private void addDiscountPriceMessage(String discountType, String stringCommaDiscountPrice) {
        discountDetailsMessage.add(discountType + stringCommaDiscountPrice + WON);
    }

    private int discountChristmasD_Day(int dateOfVisit) {
        if (isOverDateChristmas(dateOfVisit))
            return ZERO_VALUE;

        int discountPrice = THOUSAND_VALUE + HUNDRED_VALUE * (dateOfVisit - ONE_VALUE);
        totalDiscountPrice += discountPrice;
        return discountPrice;
    }

    private boolean isZeroValue(int price) {
        return price == ZERO_VALUE;
    }

    private boolean isOverDateChristmas(int dateOfVisit) {
        return dateOfVisit > CHRISTMAS_DAY;
    }
}
