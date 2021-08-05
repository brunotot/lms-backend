package tvz.btot.zavrsni.infrastructure.utils;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

public final class DateTimeUtils {
    private static final String defaultDatePattern = "dd.MM.yyyy HH:mm:ss";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(defaultDatePattern);

    public static Timestamp convertDateStringToTimestamp(final String dateString) {
        return dateString == null ? null : Timestamp.valueOf(dateString);
    }

    public static Timestamp convertInstantToTimestamp(final Instant instant) {
        return instant == null ? null : Timestamp.from(instant);
    }

    public static String getCurrentDateString() {
        return convertTimestampToDateString(convertInstantToTimestamp(getCurrentDateInstant()));
    }

    public static Instant getCurrentDateInstant() {
        return Instant.now();
    }

    public static String convertTimestampToDateString(final Timestamp timestamp) {
        assert timestamp != null;
        return timestamp.toLocalDateTime().format(formatter);
    }

    private DateTimeUtils() {
        throw new UnsupportedOperationException();
    }
}
