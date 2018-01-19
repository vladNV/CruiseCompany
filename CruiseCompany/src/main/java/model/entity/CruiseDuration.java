package model.entity;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class CruiseDuration {
    private long days;
    private long hours;
    private long minutes;

    CruiseDuration(LocalDateTime from, LocalDateTime to) {
        duration(from, to);
    }

    private void duration(LocalDateTime from, LocalDateTime to) {
        days = from.until(to, ChronoUnit.DAYS);
        from = from.plusDays(days);
        hours = from.until(to, ChronoUnit.HOURS);
        from = from.plusHours(hours);
        minutes = from.until(to, ChronoUnit.MINUTES);
    }

    public long getDays() {
        return days;
    }

    public long getHours() {
        return hours;
    }

    public long getMinutes() {
        return minutes;
    }

    @Override
    public String toString() {
        return "CruiseDuration{" +
                "days=" + days +
                ", hours=" + hours +
                ", minutes=" + minutes +
                '}';
    }
}
