package christmas.model;

import static christmas.constant.ErrorConstant.ERROR_PREFIX;

public enum Menu {

    BUTTON_MUSHROOM_SOUP(Course.APPETIZER, 6_000),
    TAPAS(Course.APPETIZER, 5_500),
    CAESAR_SALAD(Course.APPETIZER, 8_000),

    T_BONE_STEAK(Course.MAIN, 55_000),
    BBQ_RIB(Course.MAIN, 54_000),
    SEAFOOD_PASTA(Course.MAIN, 35_000),
    CHRISTMAS_PASTA(Course.MAIN, 25_000),
    CHOCOLATE_CAKE(Course.DESSERT, 15_000),
    ICECREAM(Course.DESSERT, 5_000),
    ZERO_COKE(Course.BEVERAGE, 3_000),
    RED_WINE(Course.BEVERAGE, 60_000),
    CHAMPAGNE(Course.BEVERAGE, 25_000);

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

    Menu(Course course, int price) {
        this.course = course;
        this.price = price;
    }

    private final Course course;
    private final int price;

    private static final String VALIDATE_CORRECT_MENU_ERROR_MESSAGE = "유효하지 않은 주문입니다. 다시 입력해 주세요.";

    public static Menu StringToEnum(String menu) {
        if (menu.equals("양송이수프")) return BUTTON_MUSHROOM_SOUP;
        else if (menu.equals("타파스")) return TAPAS;
        else if (menu.equals("시저샐러드")) return CAESAR_SALAD;
        else if (menu.equals("티본스테이크")) return T_BONE_STEAK;
        else if (menu.equals("바비큐립")) return BBQ_RIB;
        else if (menu.equals("해산물파스타")) return SEAFOOD_PASTA;
        else if (menu.equals("크리스마스파스타")) return CHRISTMAS_PASTA;
        else if (menu.equals("초코케이크")) return CHOCOLATE_CAKE;
        else if (menu.equals("아이스크림")) return ICECREAM;
        else if (menu.equals("제로콜라")) return ZERO_COKE;
        else if (menu.equals("레드와인")) return RED_WINE;
        else if (menu.equals("샴페인")) return CHAMPAGNE;
        throw new IllegalArgumentException(ERROR_PREFIX + VALIDATE_CORRECT_MENU_ERROR_MESSAGE);
    }
}
