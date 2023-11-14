package christmas.service;

import christmas.model.Menu;
import christmas.model.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static christmas.constant.NumberConstant.ZERO_VALUE;
import static christmas.model.Badge.*;
import static christmas.util.SeparationUtil.separateMenus;
import static christmas.util.SeparationUtil.separateMenusAndCount;
import static christmas.view.OutputView.NO_GIFT_MESSAGE;
import static christmas.view.OutputView.ONE_CHAMPAGNE_MESSAGE;

public class EventService {

    private final Person person = new Person();
    private final List<Menu> menus = new ArrayList<>();

    public void initEvent(String inputMenusAndCounts) {
        Map<Menu, Integer> menusAndCounts = resolveInputMenusAndCounts(inputMenusAndCounts);
        int totalOrderPriceBeforeDiscount = calculateTotalOrderPriceBeforeDiscount(menusAndCounts);
        setInitPerson(menusAndCounts, totalOrderPriceBeforeDiscount);
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public Map<Menu, Integer> getPersonMenusAndCounts() {
        return person.getMenuAndCount();
    }

    public boolean getIsPresentedChampagne() {
        return person.isPresentedChampagne();
    }


    public String getStringCommaTotalOrderPriceBeforeDiscount() {
        return person.getStringCommaTotalOrderPriceBeforeDiscount();
    }

    public String getGiftMenu() {
        if (person.isPresentedChampagne()) {
            return ONE_CHAMPAGNE_MESSAGE;
        }
        return NO_GIFT_MESSAGE;
    }

    public String getStringCommaExpectPaymentPrice() {
        return person.getStringCommaExpectPaymentPrice();
    }

    public String getStringCommaTotalDiscountPrice() {
        return person.getStringCommaTotalDiscountPrice();
    }

    private int getTotalDiscountPrice() {
        return person.getTotalDiscountPrice();
    }

    public void setTotalDiscountPrice(int totalDiscountPrice) {
        person.setTotalDiscountPrice(totalDiscountPrice);
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
            Menu menu = Menu.stringToEnum(separateMenusAndCount.get(0)); //menu 이름
            menus.add(menu);
            int count = Integer.parseInt(separateMenusAndCount.get(1)); //menu count
            menusAndCounts.put(menu, count);
        }

        return menusAndCounts;
    }

    private int calculateTotalOrderPriceBeforeDiscount(Map<Menu, Integer> menusAndCounts) {
        int totalPrice = ZERO_VALUE;
        for (Menu menu : menus) {
            Integer count = menusAndCounts.get(menu);
            totalPrice += menu.getPrice() * count;
        }
        return totalPrice;
    }

    public int getDateOfVisit() {
        return person.getDateOfVisit();
    }

    public void setDateOfVisit(String inputDateOfVisit) {
        int dateOfVisit = Integer.parseInt(inputDateOfVisit);
        person.setDateOfVisit(dateOfVisit);
    }

    private void setInitPerson(Map<Menu, Integer> menusAndCounts, int totalOrderPriceBeforeDiscount) {
        person.setMenuAndCount(menusAndCounts);
        person.setTotalOrderPriceBeforeDiscount(totalOrderPriceBeforeDiscount);
    }

    public boolean isEventTargetPerson() {
        return person.isEventTargetPerson();
    }
}
