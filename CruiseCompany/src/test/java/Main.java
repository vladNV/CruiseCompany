import java.time.*;
import java.time.temporal.ChronoUnit;

public class Main {
    public static void main(String[] args) {
        LocalDateTime from = LocalDateTime
                .of(2018, Month.JANUARY, 1, 10, 0, 0);
        LocalDateTime to = LocalDateTime
                .of(2018, Month.JANUARY, 7, 21, 30, 0);
        LocalDateTime duration = LocalDateTime.from(from);

        long days = duration.until(to, ChronoUnit.DAYS);
        duration = duration.plusDays(days);
        long hours = duration.until(to, ChronoUnit.HOURS);
        duration = duration.plusHours(hours);
        System.out.println(days);
        System.out.println(hours);
    }
}
