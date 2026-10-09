import java.time.LocalDateTime;

public enum Plan {
    MONTHLY(1, 0),
    QUARTERLY(3, 5),
    YEARLY(12, 15);

    private final int periodMonth;
    private final int discountRate;

    Plan(int periodMonth, int discountRate) {
        this.periodMonth = periodMonth;
        this.discountRate = discountRate;
    }

    public long calculatePrice(long price, Integer couponPercent) {
        int couponPercentValue = (couponPercent == null) ? 0 : couponPercent;
        return price - (price * (discountRate + couponPercentValue) / 100);
    }

    public LocalDateTime calculateEndDate(LocalDateTime startDate) {
        return startDate.plusMonths(periodMonth);
    }

}
