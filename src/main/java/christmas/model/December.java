package christmas.model;

import java.util.List;

public enum December {

    WEEKDAY(List.of(3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 17, 18, 19, 20, 21, 24, 25, 26, 27, 28, 31)),
    WEEKEND(List.of(1, 2, 8, 9, 15, 16, 22, 23, 29, 30)),
    STAR_DAY(List.of(3, 10, 17, 24, 25, 31));

    private final List<Integer> day;

    December(List<Integer> day) {
        this.day = day;
    }

    public static boolean containDayInWeekday(int dateOfVisit) {
        return WEEKDAY.day.contains(dateOfVisit);
    }

    public static boolean containDayInWeekend(int dateOfVisit) {
        return WEEKEND.day.contains(dateOfVisit);
    }

    public static boolean containDayInStarDay(int dateofVisit) {
        return STAR_DAY.day.contains(dateofVisit);
    }

}
