package christmas.service;

import christmas.model.Menu;
import christmas.model.Menu.Course;
import christmas.model.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static christmas.constant.ErrorConstant.*;
import static christmas.util.SeparationUtil.separateMenus;
import static christmas.util.SeparationUtil.separateMenusAndCount;

public class EventService {


    private final Person person = new Person();
    int totalCount = 0;
    boolean isNotOnlyBeverage = false;
    private List<Menu> menus = new ArrayList<>();

    public void initEvent(String inputMenusAndCounts) {
        Map<Menu, Integer> menusAndCounts = resolveInputMenusAndCounts(inputMenusAndCounts);
        checkNotOnlyBeverage(isNotOnlyBeverage);
        int totalOrderPriceBeforeDiscount = calculateTotalOrderPriceBeforeDiscount(menusAndCounts);
        setInitPerson(totalCount, menusAndCounts, totalOrderPriceBeforeDiscount);
    }

    public void initMenus() {
        menus = new ArrayList<>();
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public Map<Menu, Integer> getPersonMenusAndCounts() {
        return person.getMenuAndCount();
    }

    public String getStringCommaTotalOrderPriceBeforeDiscount() {
        int totalOrderPriceBeforeDiscount = getTotalOrderPriceBeforeDiscount();
        return convertIntTotalPriceToStringCommaPrice(totalOrderPriceBeforeDiscount);
    }

    private int getTotalOrderPriceBeforeDiscount() {
        return person.getTotalOrderPriceBeforeDiscount();
    }

    private String convertIntTotalPriceToStringCommaPrice(int totalOrderPriceBeforeDiscount) {
        return String.format("%,d", totalOrderPriceBeforeDiscount);

    }

    private Map<Menu, Integer> resolveInputMenusAndCounts(String inputMenusAndCounts) {
        Map<Menu, Integer> menusAndCounts = new HashMap<>();
        List<String> separateMenus = separateMenus(inputMenusAndCounts);

        for (String menusAndCount : separateMenus) {
            List<String> separateMenusAndCount = separateMenusAndCount(menusAndCount);
            Menu menu = Menu.StringToEnum(separateMenusAndCount.get(0)); //menu 이름
            checkDuplicateMenu(menu);
            menus.add(menu);
            checkContainBesidesBeverage(menu.getCourse());
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

    private void checkDuplicateMenu(Menu menu) {
        if (menus.contains(menu))
            throw new IllegalArgumentException(ERROR_PREFIX + VALIDATE_CORRECT_MENU_ERROR_MESSAGE);
    }

    private void checkContainBesidesBeverage(Course course) {
        if (!course.equals(Course.BEVERAGE))
            isNotOnlyBeverage = true;
    }

    private void checkNotOnlyBeverage(boolean isNotOnlyBeverage) {
        if (!isNotOnlyBeverage)
            throw new IllegalArgumentException(ERROR_PREFIX + ONLY_BEVERAGE_ERROR_MESSAGE);
    }


    private void setInitPerson(int totalCount, Map<Menu, Integer> menusAndCounts, int totalOrderPriceBeforeDiscount) {
        person.setTotalCount(totalCount);
        person.setMenuAndCount(menusAndCounts);
        person.setTotalOrderPriceBeforeDiscount(totalOrderPriceBeforeDiscount);
    }
}
