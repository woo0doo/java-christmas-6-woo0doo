package christmas.service;

import christmas.model.December;
import christmas.model.Menu;
import christmas.model.Menu.Course;
import christmas.model.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static christmas.constant.ErrorConstant.*;
import static christmas.util.ConvertUtil.convertIntPriceToStringCommaPrice;
import static christmas.util.SeparationUtil.separateMenus;
import static christmas.util.SeparationUtil.separateMenusAndCount;
import static christmas.view.OutputView.NO_GIFT_MESSAGE;

public class EventService {

    private static final int BASED_ON_OVER_PRICE_GIVE_GIFT_MENU = 120_000;
    private static final String ONE_CHAMPAGNE_MESSAGE = "샴페인 1개";
    private static final int STAR_DAY_DISCOUNT_PRICE = 1000;
    private static final int CHAMPAGNE_PRICE = 25000;

    private final Person person = new Person();
    int totalCount = 0;
    int totalDiscountPrice = 0;
    List<String> discountDetailsMessage = new ArrayList<>();

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

    private int getExpectPaymentPrice()  {
        int totalOrderPriceBeforeDiscount = person.getTotalOrderPriceBeforeDiscount();
        int totalDiscountPrice = person.getTotalDiscountPrice();
        return totalOrderPriceBeforeDiscount-totalDiscountPrice;
    }

    public String getStringCommaTotalDiscountPrice() {
        int totalDiscountPrice = getTotalDiscountPrice();
        return convertIntPriceToStringCommaPrice(totalDiscountPrice);
    }

    private int getTotalDiscountPrice() {
        return person.getTotalDiscountPrice();
    }

    private int getTotalOrderPriceBeforeDiscount() {
        return person.getTotalOrderPriceBeforeDiscount();
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

    public void setDateOfVisit(String inputDateofVisit) {
        int dateOfVisit = Integer.parseInt(inputDateofVisit);
        person.setDateOfVisit(dateOfVisit);
    }

    public int getDateOfVisit() {
        return person.getDateOfVisit();
    }

    private void setInitPerson(int totalCount, Map<Menu, Integer> menusAndCounts, int totalOrderPriceBeforeDiscount) {
        person.setTotalCount(totalCount);
        person.setMenuAndCount(menusAndCounts);
        person.setTotalOrderPriceBeforeDiscount(totalOrderPriceBeforeDiscount);
    }

    public List<String> getDiscountDetails() {
        List<Menu> menus = getMenus();
        int dateOfVisit = getDateOfVisit();
        Map<Menu, Integer> menusAndCounts = getPersonMenusAndCounts();
        addChristmasD_DayDiscountMessage(discountChristmasD_Day(dateOfVisit));
        discountWeekday(menusAndCounts, menus, dateOfVisit);
        discountWeekend(menusAndCounts, menus, dateOfVisit);
        discountStarDay(dateOfVisit);
        discountGiftEvent(person);
        person.setTotalDiscountPrice(totalDiscountPrice);
        return discountDetailsMessage;
    }

    private void discountGiftEvent(Person person) {
        if (!person.getIsPresentedChampagne())
            return;

        totalDiscountPrice += CHAMPAGNE_PRICE;
        String stringCommaChampagneDiscountPrice = convertIntPriceToStringCommaPrice(CHAMPAGNE_PRICE);
        discountDetailsMessage.add("증정 이벤트: -" + stringCommaChampagneDiscountPrice + "원");
    }

    private void discountStarDay(int dateOfVisit) {
        if (!December.containDayInStarDay(dateOfVisit))
            return;

        totalDiscountPrice += STAR_DAY_DISCOUNT_PRICE;
        String stringCommaStarDayDiscountPrice = convertIntPriceToStringCommaPrice(STAR_DAY_DISCOUNT_PRICE);
        addDiscountPriceMessage("특별", stringCommaStarDayDiscountPrice);
    }

    private void discountWeekday(Map<Menu, Integer> menusAndCounts, List<Menu> menus, int dateOfVisit) {
        if (!December.containDayInWeekday(dateOfVisit))
            return;

        int dessertMenuNumber = countCourseMenu(menusAndCounts, menus, Menu.Course.DESSERT);
        if (dessertMenuNumber == 0) return;

        int weekdayDiscountPrice = dessertMenuNumber * 2023;
        totalDiscountPrice += weekdayDiscountPrice;
        String stringCommaWeekdayDiscountPrice = convertIntPriceToStringCommaPrice(weekdayDiscountPrice);
        addDiscountPriceMessage("평일", stringCommaWeekdayDiscountPrice);
    }

    private void discountWeekend(Map<Menu, Integer> menusAndCounts, List<Menu> menus, int dateOfVisit) {
        if (!December.containDayInWeekend(dateOfVisit))
            return;

        int mainMenuNumber = countCourseMenu(menusAndCounts, menus, Menu.Course.MAIN);
        if (mainMenuNumber == 0) return;

        int weekendDiscountPrice = mainMenuNumber * 2023;
        totalDiscountPrice += weekendDiscountPrice;
        String stringCommaWeekendDiscountPrice = convertIntPriceToStringCommaPrice(weekendDiscountPrice);
        addDiscountPriceMessage("주말", stringCommaWeekendDiscountPrice);
    }

    private int countCourseMenu(Map<Menu, Integer> menusAndCounts, List<Menu> menus, Menu.Course course) {
        int dessertMenuNumber = 0;

        for (Menu menu : menus) {
            if (menu.getCourse().equals(course))
                dessertMenuNumber += menusAndCounts.get(menu);
        }

        return dessertMenuNumber;
    }

    private void addChristmasD_DayDiscountMessage(int discountPrice) {
        if (discountPrice == 0)
            return;
        String stringCommaDiscountPrice = convertIntPriceToStringCommaPrice(discountPrice);
        addDiscountPriceMessage("크리스마스 디데이", stringCommaDiscountPrice);
    }

    private void addDiscountPriceMessage(String discountType, String stringCommaDiscountPrice) {
        discountDetailsMessage.add(discountType + " 할인: -" + stringCommaDiscountPrice + "원");
    }

    private int discountChristmasD_Day(int dateOfVisit) {
        if (isOverDateChristmas(dateOfVisit))
            return 0;

        int discountPrice = 1000 + 100 * (dateOfVisit - 1);
        totalDiscountPrice += discountPrice;
        return discountPrice;
    }

    private boolean isOverDateChristmas(int dateOfVisit) {
        return dateOfVisit > 25;
    }
}
