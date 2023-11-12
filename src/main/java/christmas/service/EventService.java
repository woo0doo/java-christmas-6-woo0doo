package christmas.service;

import christmas.model.Menu;
import christmas.model.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static christmas.constant.NumberConstant.BASED_ON_OVER_PRICE_GIVE_GIFT_MENU;
import static christmas.model.Badge.*;
import static christmas.util.ConvertUtil.convertIntPriceToStringCommaPrice;
import static christmas.util.SeparationUtil.separateMenus;
import static christmas.util.SeparationUtil.separateMenusAndCount;
import static christmas.view.OutputView.NO_GIFT_MESSAGE;
import static christmas.view.OutputView.ONE_CHAMPAGNE_MESSAGE;

public class EventService {

    private final Person person = new Person();
    int totalCount = 0;
    private final List<Menu> menus = new ArrayList<>();

    public void initEvent(String inputMenusAndCounts) {
        Map<Menu, Integer> menusAndCounts = resolveInputMenusAndCounts(inputMenusAndCounts);
        int totalOrderPriceBeforeDiscount = calculateTotalOrderPriceBeforeDiscount(menusAndCounts);
        setInitPerson(totalCount, menusAndCounts, totalOrderPriceBeforeDiscount);
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public Map<Menu, Integer> getPersonMenusAndCounts() {
        return person.getMenuAndCount();
    }

    public boolean getIsPresentedChampagne() {
        return person.getIsPresentedChampagne();
    }


    public String getStringCommaTotalOrderPriceBeforeDiscount() {
        int totalOrderPriceBeforeDiscount = getTotalOrderPriceBeforeDiscount();
        return convertIntPriceToStringCommaPrice(totalOrderPriceBeforeDiscount);
    }

    public String getGiftMenu() {
        if (person.getTotalOrderPriceBeforeDiscount() > BASED_ON_OVER_PRICE_GIVE_GIFT_MENU) {
            person.setPresentedChampagne(true);
            return ONE_CHAMPAGNE_MESSAGE;
        }
        person.setPresentedChampagne(false);
        return NO_GIFT_MESSAGE;
    }

    public String getStringCommaExpectPaymentPrice() {
        int expectPaymentPrice = getExpectPaymentPrice();
        return convertIntPriceToStringCommaPrice(expectPaymentPrice);
    }

    private int getExpectPaymentPrice() {
        int totalOrderPriceBeforeDiscount = person.getTotalOrderPriceBeforeDiscount();
        int totalDiscountPrice = person.getTotalDiscountPrice();
        return totalOrderPriceBeforeDiscount - totalDiscountPrice;
    }

    public String getStringCommaTotalDiscountPrice() {
        int totalDiscountPrice = getTotalDiscountPrice();
        return convertIntPriceToStringCommaPrice(totalDiscountPrice);
    }

    private int getTotalDiscountPrice() {
        return person.getTotalDiscountPrice();
    }

    public void setTotalDiscountPrice(int totalDiscountPrice) {
        person.setTotalDiscountPrice(totalDiscountPrice);
    }

    private int getTotalOrderPriceBeforeDiscount() {
        return person.getTotalOrderPriceBeforeDiscount();
    }

    public String getEventBadge() {
        int totalDiscountPrice = getTotalDiscountPrice();
        if (isOverSantaDiscountPriceRange(totalDiscountPrice))
            return SANTA.getBadgeKoreaName();

        if (isOverTreeDiscountPriceRange(totalDiscountPrice))
            return TREE.getBadgeKoreaName();

        if (isOverStarDiscountPriceRange(totalDiscountPrice))
            return STAR.getBadgeKoreaName();

        return NO_GIFT_MESSAGE;
    }

    private Map<Menu, Integer> resolveInputMenusAndCounts(String inputMenusAndCounts) {
        Map<Menu, Integer> menusAndCounts = new HashMap<>();
        List<String> separateMenus = separateMenus(inputMenusAndCounts);

        for (String menusAndCount : separateMenus) {
            List<String> separateMenusAndCount = separateMenusAndCount(menusAndCount);
            Menu menu = Menu.StringToEnum(separateMenusAndCount.get(0)); //menu 이름
            menus.add(menu);
            int count = Integer.parseInt(separateMenusAndCount.get(1)); //menu count
            totalCount += count;
            menusAndCounts.put(menu, count);
        }

        return menusAndCounts;
    }

    private int calculateTotalOrderPriceBeforeDiscount(Map<Menu, Integer> menusAndCounts) {
        int totalPrice = 0;
        for (Menu menu : menus) {
            Integer count = menusAndCounts.get(menu);
            totalPrice += menu.getPrice() * count;
        }
        return totalPrice;
    }

    public int getDateOfVisit() {
        return person.getDateOfVisit();
    }

    public void setDateOfVisit(String inputDateofVisit) {
        int dateOfVisit = Integer.parseInt(inputDateofVisit);
        person.setDateOfVisit(dateOfVisit);
    }

    private void setInitPerson(int totalCount, Map<Menu, Integer> menusAndCounts, int totalOrderPriceBeforeDiscount) {
        person.setTotalCount(totalCount);
        person.setMenuAndCount(menusAndCounts);
        person.setTotalOrderPriceBeforeDiscount(totalOrderPriceBeforeDiscount);
    }
}
