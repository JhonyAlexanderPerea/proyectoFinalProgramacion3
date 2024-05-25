package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateTimeUtil {

    private SimpleDateFormat dateFormat;

    public DateTimeUtil(String format, String timeZoneId) {
        dateFormat = new SimpleDateFormat(format);
        dateFormat.setTimeZone(TimeZone.getTimeZone(timeZoneId));
    }

    public boolean validateFormat(String format) {
        try {
            new SimpleDateFormat(format);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean validateFormat(String pattern, String dateTime) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            sdf.parse(dateTime);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public Date createDateFromString(String dateString) throws ParseException {
        return dateFormat.parse(dateString);
    }

    public String formatDateString(Date date) {
        return dateFormat.format(date);
    }

    public void setTimeZone(String timeZoneId) {
        dateFormat.setTimeZone(TimeZone.getTimeZone(timeZoneId));
    }

    public String getTimeZone() {
        return dateFormat.getTimeZone().getID();
    }
}

