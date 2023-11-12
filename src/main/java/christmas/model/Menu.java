package christmas.model;

import static christmas.constant.ErrorConstant.ERROR_PREFIX;
import static christmas.constant.ErrorConstant.VALIDATE_CORRECT_MENU_ERROR_MESSAGE;

public enum Menu {

    BUTTON_MUSHROOM_SOUP("양송이수프", Course.APPETIZER, 6_000),
    TAPAS("타파스", Course.APPETIZER, 5_500),
    CAESAR_SALAD("시저샐러드", Course.APPETIZER, 8_000),

    T_BONE_STEAK("티본스테이크", Course.MAIN, 55_000),
    BBQ_RIB("바비큐립", Course.MAIN, 54_000),
    SEAFOOD_PASTA("해산물파스타", Course.MAIN, 35_000),
    CHRISTMAS_PASTA("크리스마스파스타", Course.MAIN, 25_000),
    CHOCOLATE_CAKE("초코케이크", Course.DESSERT, 15_000),
    ICECREAM("아이스크림", Course.DESSERT, 5_000),
    ZERO_COKE("제로콜라", Course.BEVERAGE, 3_000),
    RED_WINE("레드와인", Course.BEVERAGE, 60_000),
    CHAMPAGNE("샴페인", Course.BEVERAGE, 25_000);

    private final String menuKoreaName;
    private final Course course;
    private final int price;

    Menu(String menuKoreaName, Course course, int price) {
        this.menuKoreaName = menuKoreaName;
        this.course = course;
        this.price = price;
    }

    public static Menu StringToEnum(String menu) {
        if (menu.equals(BUTTON_MUSHROOM_SOUP.menuKoreaName)) return BUTTON_MUSHROOM_SOUP;
        else if (menu.equals(TAPAS.menuKoreaName)) return TAPAS;
        else if (menu.equals(CAESAR_SALAD.menuKoreaName)) return CAESAR_SALAD;
        else if (menu.equals(T_BONE_STEAK.menuKoreaName)) return T_BONE_STEAK;
        else if (menu.equals(BBQ_RIB.menuKoreaName)) return BBQ_RIB;
        else if (menu.equals(SEAFOOD_PASTA.menuKoreaName)) return SEAFOOD_PASTA;
        else if (menu.equals(CHRISTMAS_PASTA.menuKoreaName)) return CHRISTMAS_PASTA;
        else if (menu.equals(CHOCOLATE_CAKE.menuKoreaName)) return CHOCOLATE_CAKE;
        else if (menu.equals(ICECREAM.menuKoreaName)) return ICECREAM;
        else if (menu.equals(ZERO_COKE.menuKoreaName)) return ZERO_COKE;
        else if (menu.equals(RED_WINE.menuKoreaName)) return RED_WINE;
        else if (menu.equals(CHAMPAGNE.menuKoreaName)) return CHAMPAGNE;
        throw new IllegalArgumentException(ERROR_PREFIX + VALIDATE_CORRECT_MENU_ERROR_MESSAGE);
    }

    public String getMenuKoreaName() {
        return menuKoreaName;
    }

    public Course getCourse() {
        return course;
    }

    public int getPrice() {
        return price;
    }

    public enum Course {
        APPETIZER,
        MAIN,
        DESSERT,
        BEVERAGE
    }
}
