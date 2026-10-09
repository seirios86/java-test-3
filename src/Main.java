import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) {
        String strStartDateTime = "2026-10-09 14:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime parsedDate = LocalDateTime.parse(strStartDateTime, formatter);

        Subscription subscription1 = new Subscription("홍길동", Plan.MONTHLY, 10000, parsedDate, null);
        System.out.println(subscription1);

        Subscription subscription1_1 = subscription1.renew();
        System.out.println(subscription1_1);

        System.out.println("갱신 전 만료일: " + subscription1.getFormattedEndDateTime());
        System.out.println("갱신 후 만료일: " + subscription1_1.getFormattedEndDateTime());
        System.out.println();

        Subscription subscription2 = new Subscription("홍길동", Plan.QUARTERLY, 30000, parsedDate, 5);
        System.out.println(subscription2);

        Subscription subscription3 = new Subscription("홍길동", Plan.YEARLY, 100000, parsedDate, 10);
        System.out.println(subscription3);
    }

}