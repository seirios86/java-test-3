import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

public class Subscription {

    private final UUID subscriptionId;
    private final String customerName;
    private final Plan plan;
    private final long price;
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;
    private final Integer couponPercent;

    public Subscription(String customerName, Plan plan, long price, LocalDateTime startDateTime, Integer couponPercent) {
        this.subscriptionId = UUID.randomUUID();
        this.customerName = customerName;
        this.plan = plan;
        this.price = price;
        this.startDateTime = startDateTime;
        this.endDateTime = plan.calculateEndDate(startDateTime);
        this.couponPercent = couponPercent;
    }

    public Subscription renew() {
        return new Subscription(customerName, plan, price, endDateTime, couponPercent);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Subscription that = (Subscription) o;
        return Objects.equals(subscriptionId, that.subscriptionId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append("구독번호: ").append(subscriptionId).append("\n")
                .append("고객: ").append(customerName).append("\n")
                .append("플랜: ").append(plan.name()).append("\n")
                .append("가입일: ").append(formatDate(startDateTime)).append("\n")
                .append("만료일: ").append(formatDate(endDateTime)).append("\n")
                .append("기본가격: ").append(price).append("원").append("\n");
        if (couponPercent != null) {
            sb.append("쿠폰: ").append(couponPercent).append("%").append("\n");
        }
        return sb.append("결제금액: ").append(plan.calculatePrice(price, couponPercent)).append("원").append("\n\n")
                .append("갱신 전 만료일: ").append(formatDate(endDateTime)).append("\n")
                .append("갱신 후 만료일: ").append(formatDate(plan.calculateEndDate(endDateTime))).append("\n")
                .toString();
    }

    private static String formatDate(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
        return localDateTime.format(formatter);
    }
}
