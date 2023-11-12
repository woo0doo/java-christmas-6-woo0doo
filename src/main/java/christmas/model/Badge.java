package christmas.model;

public enum Badge {

    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);

    private final String BadgeKoreaName;
    private final int discountPriceRange;
    Badge(String badgeKoreaName, int discountPriceRange) {
        BadgeKoreaName = badgeKoreaName;
        this.discountPriceRange = discountPriceRange;
    }

    public static boolean isOverSantaDiscountPriceRange(int totalDiscountPrice) {
        return totalDiscountPrice >= SANTA.discountPriceRange;
    }

    public static boolean isOverTreeDiscountPriceRange(int totalDiscountPrice) {
        return totalDiscountPrice >= TREE.discountPriceRange;
    }

    public static boolean isOverStarDiscountPriceRange(int totalDiscountPrice) {
        return totalDiscountPrice >= STAR.discountPriceRange;
    }

    public String getBadgeKoreaName() {
        return BadgeKoreaName;
    }
}
