import java.time.LocalDateTime;
import java.time.Period;

public enum Plan {
    MONTHLY(Period.ofMonths(1), 0),
    QUARTERLY(Period.ofMonths(3), 5),
    YEARLY(Period.ofYears(1), 15);

    private final Period period;
    private final int discountRate;

    Plan(Period period, int discountRate) {
        this.period = period;
        this.discountRate = discountRate;
    }

    public long calculatePrice(long price, Integer couponPercent) {
        int couponPercentValue = (couponPercent == null) ? 0 : couponPercent;
        return price - (price * (discountRate + couponPercentValue) / 100);
    }

    public LocalDateTime calculateEndDate(LocalDateTime startDate) {
        return startDate.plus(period);
    }

}
