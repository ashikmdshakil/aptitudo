package com.cnsbd.aptitudo.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DateTimeUtil {

    public static final String DD_MM_YYYY = "dd-MM-yyyy";
    public static final String DATE_TIME_FORMATTER = "dd-MM-yyyy HH:mm:ss";
    public static final String DATE_TIME_FORMATTER_AM_PM = "dd-MM-yyyy hh:mm:ss a";
    public static final String DATE_TIME_FORMATTER_MONTH_DATE_TIME = "MMMM d, y, h:m a";

    public static final DateFormat DATA_TIME_FORMAT = new SimpleDateFormat(DATE_TIME_FORMATTER);
    public static final DateTimeFormatter LOCAL_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER);
    public static final DateTimeFormatter LOCAL_DATE_TIME_FORMATTER_AM_PM = DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER_AM_PM);
    public static final DateTimeFormatter LOCAL_DATE_FORMATTER_YYYY_MM_DD = DateTimeFormatter.ofPattern(DD_MM_YYYY); // use for date to string
    public static final DateTimeFormatter LOCAL_TIME_FORMATTER_AM_PM = DateTimeFormatter.ofPattern("hh:mm:ss a");

    private DateTimeUtil() {
    }

    /**
     * This method used convert string to date
     *
     * @param str String date value
     * @return Date
     */
    public static Date strToDt(String str) {
        Date dt = null;
        try {
            if (str != null) {
                dt = DATA_TIME_FORMAT.parse(str);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dt;
    }

    public static LocalDateTime strToLocalDt(String str) {
        LocalDateTime dt = null;
        try {
            if (str != null) {
                dt = LocalDateTime.parse(str, LOCAL_DATE_TIME_FORMATTER);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dt;
    }

    /**
     * This method used convert date to string
     *
     * @param dt date
     * @return string Date
     */
    public static String dtToStr(Date dt) {
        String str = null;
        if (dt != null) {
            str = DATA_TIME_FORMAT.format(dt);
        }
        return str;
    }

    /**
     * This method used convert date to string
     *
     * @param dt date
     * @return string Date
     */
    public static String localDtToStr(LocalDateTime dt) {
        String str = null;
        if (dt != null) {
            str = LOCAL_DATE_TIME_FORMATTER.format(dt);
        }
        return str;
    }

    /**
     * @param dt
     * @return
     */
    public static String localDtToStrAMPM(LocalDateTime dt) {
        String str = null;
        if (dt != null) {
            str = LOCAL_DATE_TIME_FORMATTER_AM_PM.format(dt);
        }
        return str;
    }

    /**
     * This method used convert date to string
     *
     * @param dt date
     * @return string Date
     */
    public static String localDtToStr(LocalDate dt) {
        String str = null;
        if (dt != null) {
            str = LOCAL_DATE_FORMATTER_YYYY_MM_DD.format(dt);
        }
        return str;
    }

    /**
     * 24 format time validation
     *
     * @param time
     * @return
     */
    public static boolean isValidTime24Format(String time) {
        // Regex to check valid time in 24-hour format.
        String regex = "([01]?[0-9]|2[0-3]):[0-5][0-9]";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the time is empty
        // return false
        if (time == null) {
            return false;
        }

        // Pattern class contains matcher() method
        // to find matching between given time
        // and regular expression.
        Matcher m = p.matcher(time);

        // Return if the time
        // matched the ReGex
        return m.matches();

    }

    /**
     * convert to oracle procedure date
     *
     * @param dateText format: dd-MM-yyyy e.g. 31-12-2023
     * @return dateText format: yyyy-MM-dd HH:mm:ss
     */
    public static String convertToOracleProcDate(String dateText) {
        if (!StringUtils.hasText(dateText)) {
            return null;
        }
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        dateText = outputFormat.format(LocalDate.parse(dateText, inputFormat).atStartOfDay());
        return dateText;
    }

    /**
     * convert to oracle procedure date
     *
     * @param dateText format: dd-MMM-yyyy e.g. 01-JAN-2023
     * @return dateText format: yyyy-MM-dd HH:mm:ss
     */
    public static String convertToOracleProcDate(String dateText, boolean monthInMMM) {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (!StringUtils.hasText(dateText)) {
            return null;
        }

        if (monthInMMM) {
            DateTimeFormatter inputFormat = new DateTimeFormatterBuilder()
                    .parseCaseInsensitive()
                    .append(DateTimeFormatter.ofPattern("dd-MMM-yyyy"))
                    .toFormatter(Locale.getDefault());

            dateText = outputFormat.format(LocalDate.parse(dateText, inputFormat).atStartOfDay());
        }
        return dateText;
    }

    public static String reverseDateFormat(String date) {
        char[] s = date.toCharArray();
        char temp = s[0];
        s[0] = s[3];
        s[3] = temp;
        temp = s[1];
        s[1] = s[4];
        s[4] = temp;

        return new String(s);
    }

    public static String getTodayDate() {
        LocalDateTime todayDate = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER_MONTH_DATE_TIME);
        String todayFormatDateTime = todayDate.format(format);

        return todayFormatDateTime;
    }

    public static String changeDateFormatToDDMMYYYY(String date) throws ParseException {
        SimpleDateFormat outPutFormat = new SimpleDateFormat("dd-MMM-yy");
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        date = outPutFormat.format(inputFormat.parse(date));

        return date;
    }

}
