package christmas.model;

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

    private enum Course {
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
}
