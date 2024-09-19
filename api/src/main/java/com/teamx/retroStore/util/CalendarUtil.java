package com.teamx.retroStore.util;

import com.teamx.retroStore.exception.impl.AppsCommonErrorCode;
import com.teamx.retroStore.exception.impl.AppsException;
import com.teamx.retroStore.exception.impl.AppsRuntimeException;
import org.apache.commons.lang3.StringUtils;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class CalendarUtil {

    public static final String DEFAULT_DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm";

    public static final String DEFAULT_DATE_TIME_12_FORMAT = "dd/MM/yyyy hh:mm a";

    public static final String DEFAULT_DATE_TIME_FORMAT_SECONDS = "dd/MM/yyyy HH:mm:ss";

    public static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy";

    public static final String DEFAULT_DATE_FORMAT_TO_COMPARE = "yyyy/MM/dd";

    public static final String MYSQL_DATE_FORMAT = "yyyy-MM-dd";

    public static final String FINACEL_DATE_FROMAT = "dd-MM-yyyy";

    public static final String MYSQL_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";

    public static final String MYSQL_DATE_TIME_FORMAT_SECONDS = "yyyy-MM-dd HH:mm:ss";

    public static final String DEFAULT_TIME_FORMAT = "HH:mm";

    public static final String DEFAULT_TIME_12_FORMAT = "hh:mm a";

    public static final String FRIMI_DATE_TIME_FORMAT_SECONDS = "dd-MMM-yyyy HH:mm:ss";

    public static Date getParsedDate(String date, String dateFormat) throws AppsException {

        Date parsedDate;
        try {
            SimpleDateFormat format = new SimpleDateFormat(dateFormat);
            parsedDate = format.parse(date);
            return parsedDate;
        } catch (ParseException e) {
            throw new AppsException(AppsCommonErrorCode.APPS_EXCEPTION_INVALID_DATE_FORMAT);
        }

    }

    public static Date getParsedDateFromStringDate(String date) throws AppsException {
        Date parsedDate;
        try {
            SimpleDateFormat format = new SimpleDateFormat();
            parsedDate = format.parse(date);
            return parsedDate;
        } catch (ParseException e) {
            throw new AppsException(AppsCommonErrorCode.APPS_EXCEPTION_INVALID_DATE_FORMAT);
        }
    }

    public static Date getDefaultParsedDateTime(String date) throws AppsException {
        return getParsedDate(date, DEFAULT_DATE_TIME_FORMAT);
    }

    public static Date getDefaultParsedDateTimeWithSeconds(String date) throws AppsException {
        return getParsedDate(date, DEFAULT_DATE_TIME_FORMAT_SECONDS);
    }

    public static Date getMySqlParsedDateTimeSecond(String date) throws AppsException {
        return getParsedDate(date, MYSQL_DATE_TIME_FORMAT_SECONDS);
    }


    public static Date getMySqlParsedDateTime(String date) throws AppsException {
        return getParsedDate(date, MYSQL_DATE_TIME_FORMAT);
    }

    public static Date getMySqlParsedDate(String date) throws AppsException {
        return getParsedDate(date, MYSQL_DATE_FORMAT);
    }

    public static Date getFinacelParsedDate(String date) throws AppsException {
        return getParsedDate(date, FINACEL_DATE_FROMAT);
    }

    public static Date getDefaultParsedDateTimeMaskNull(String date) throws AppsException {
        return date != null ? getParsedDate(date, DEFAULT_DATE_TIME_FORMAT) : null;
    }

    public static Date getParsedDateTime(String date) throws AppsException {
        return getParsedDate(date, DEFAULT_DATE_TIME_FORMAT_SECONDS);
    }

    public static Date getDefaultParsedDateOnly(String date) throws AppsException {
        return getParsedDate(date, DEFAULT_DATE_FORMAT);
    }

    public static Date addYears(Date date, int numberOfYears) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, numberOfYears);

        return calendar.getTime();
    }

    public static Date addMonths(Date date, int numberOfMonths) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, numberOfMonths);

        return calendar.getTime();
    }

    public static Date addDate(Date date, int numberOfDates) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, numberOfDates);

        return calendar.getTime();
    }

    public static long getDiffInDays(Date firstDate, Date secondDate) {
        long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return diff;
    }

    public static Date addMinutes(Date date, int numberOfMinutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, numberOfMinutes);

        return calendar.getTime();
    }

    public static Date addSeconds(Date date, int numberOfSeconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, numberOfSeconds);

        return calendar.getTime();
    }

    public static Date minusMinutes(Date date, int numberOfMinutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, (-1) * numberOfMinutes);

        return calendar.getTime();
    }

    public static Date minusMonths(Date date, int numberOfMonths) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, (-1) * numberOfMonths);

        return calendar.getTime();
    }

    public static String getDefaultDateTimeFormat() {
        return DEFAULT_DATE_TIME_FORMAT;
    }

    public static String getDefaultDateFormat() {
        return DEFAULT_DATE_FORMAT;
    }

    public static String getDefaultTimeFormat() {
        return DEFAULT_TIME_FORMAT;
    }

    public static String getDefaultFormattedDateOnly(Date date) {
        return getFormattedDate(date, DEFAULT_DATE_FORMAT);
    }

    public static String getDefaultFormattedDateOnlyForCompare(Date date) {
        return getFormattedDate(date, DEFAULT_DATE_FORMAT_TO_COMPARE);
    }

    public static String getDefaultFormattedDateMaskNull(Date date) {
        if (date != null) {
            return CalendarUtil.getDefaultFormattedDateOnly(date);
        }
        return null;
    }

    public static String getDefaultFormattedDateTimeMaskNull(Date date) {
        if (date != null) {
            return CalendarUtil.getDefaultFormattedDateTime(date);
        }
        return null;
    }

    public static String getDefaultFormattedTimeOnly(Date date) {
        return getFormattedDate(date, DEFAULT_TIME_FORMAT);
    }

    public static String getDefaultFormattedTimeOnlyFromString(String timeStr) {
        return getFormattedString(timeStr, DEFAULT_TIME_FORMAT);
    }

    public static String getDefaultFormatted12TimeOnly(Date date) {
        return getFormattedDate(date, DEFAULT_TIME_12_FORMAT);
    }

    public static String getDefaultFormatted12TimeOnlyFromString(String timeStr) {
        return getFormattedString(timeStr, DEFAULT_TIME_12_FORMAT);
    }

    public static String getDefaultFormattedDateTime(Date date) {
        return getFormattedDate(date, DEFAULT_DATE_TIME_FORMAT);
    }

    public static String getDefaultFormatted12DateTime(Date date) {
        return getFormattedDate(date, DEFAULT_DATE_TIME_12_FORMAT);
    }

    public static String getDefaultFormattedDateTimeWithSeconds(Date date) {
        return getFormattedDate(date, DEFAULT_DATE_TIME_FORMAT_SECONDS);
    }

    public static String getFrimiFormattedDateTimeWithSeconds(Date date) {
        return getFormattedDate(date, FRIMI_DATE_TIME_FORMAT_SECONDS);
    }

    public static String getDefaultFormattedTimeMaskNull(Date date) {
        if (date != null) {
            return CalendarUtil.getDefaultFormattedTimeOnly(date);
        }
        return null;
    }

    public static String getFormattedDateMaskNull(Date date, String dateFormat) {
        if (date != null) {
            SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
            return formatter.format(date);
        } else {
            return null;
        }
    }


    public static String getFormattedDate(Date date, String dateFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        return formatter.format(date);
    }

    public static String getFormattedString(String timeStr, String timeFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(timeFormat);
        return formatter.format(timeStr);
    }

    public static Date getParsedStartDateTime(String date) throws AppsException {
        date = date.trim().concat(" 00:00:00");
        return getParsedDateTime(date);
    }


    public static Date getParsedDateAppendTime(String date, String timehhmm) throws AppsException {
        if (StringUtils.isNotBlank(timehhmm)) {

            date = date.trim().concat(" ").concat(timehhmm).concat(":00");
        } else {
            date = date.trim().concat(" 00:00:00");
        }
        return getParsedDateTime(date);
    }

    public static Date getParsedEndDateTime(String date) throws AppsException {
        date = date.trim().concat(" 23:59:59");
        return getParsedDateTime(date);
    }

    public static Date getParsed060000(String date) throws AppsException {
        date = date.trim().concat(" 06:00:00");
        return getParsedDateTime(date);
    }

    public static Date getParsed055959(String date) throws AppsException {
        date = date.trim().concat(" 05:59:59");
        return getParsedDateTime(date);
    }

    public static XMLGregorianCalendar toXMLGregorianCalendar(Date date) throws AppsException {
        GregorianCalendar gCalendar = new GregorianCalendar();
        gCalendar.setTime(date);
        XMLGregorianCalendar xmlCalendar = null;

        try {
            xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);
            xmlCalendar.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
        } catch (DatatypeConfigurationException ex) {
            throw new AppsException(AppsCommonErrorCode.APPS_INTERNAL_SERVER_ERROR);
        }

        return xmlCalendar;
    }

    public static String getMysqlDateOnlyString(Date date) {
        if (date == null) {
            return null;
        }
        return getFormattedDate(date, MYSQL_DATE_FORMAT);
    }

    public static String getMysqlDateTimeString(Date date) {
        if (date == null) {
            return null;
        }
        return getFormattedDate(date, MYSQL_DATE_TIME_FORMAT);
    }


    public static Date getDateOnly(Date date) {
        return getDateOnly(date, MYSQL_DATE_FORMAT);

    }

    public static Date getDefaultParsedDateOnly(Date date) {
        return getDateOnly(date, DEFAULT_DATE_FORMAT);

    }

    public static Date getDateOnly(Date date, String format) {
        if (date == null) {
            return null;
        }
        DateFormat formatter = new SimpleDateFormat(format);
        Date todayWithZeroTime = null;
        try {
            todayWithZeroTime = formatter.parse(formatter.format(date));
        } catch (ParseException e) {
            throw new AppsRuntimeException(AppsCommonErrorCode.APPS_EXCEPTION_INVALID_DATE_FORMAT);
        }

        return todayWithZeroTime;
    }

    public static Date getCombinedDateTime(Date date, String time) throws AppsException {
        String formattedDate = CalendarUtil.getDefaultFormattedDateOnly(date);
        formattedDate = formattedDate + " " + time;
        return CalendarUtil.getDefaultParsedDateTime(formattedDate);
    }

    public static String getParsedDateTimeWithSeconds(Date date) throws AppsException {
        return CalendarUtil.getDefaultFormattedDateTimeWithSeconds(date);
    }

    public static Date getCombinedDateTimeSeconds(Date date, String time) throws AppsException {
        String formattedDate = CalendarUtil.getDefaultFormattedDateOnly(date);
        formattedDate = formattedDate + " " + time;
        return CalendarUtil.getDefaultParsedDateTimeWithSeconds(formattedDate);
    }

    public static String extractTime(Date date) {
        return CalendarUtil.getDefaultFormattedDateTime(date).substring(11, 16);
    }

    public static int getDay(Date date) {

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public static int getMonth(Date date) {

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH);
    }

    public static int getYear(Date date) {

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public static int getDate(Date date) {

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }

    public static boolean isBeforeOrEqual(Date checkDate, Date checkAgainstDate) {
        boolean status = false;
        GregorianCalendar checkDateCalendar = getCalendar(checkDate);
        GregorianCalendar checkAgainstDateCalendar = getCalendar(checkAgainstDate);

        if (checkDateCalendar.equals(checkAgainstDateCalendar) || checkDate.before(checkAgainstDate)) {
            status = true;
        }
        return status;
    }


    public static boolean isBefore(Date checkDate, Date checkAgainstDate) {
        boolean status = false;

//        if (checkDate.before(checkAgainstDate)) {
//            status = true;
//        }
        //TODO need to check and replace
        GregorianCalendar gCheckDate = getCalendar(checkDate);
        GregorianCalendar gCheckAgainstDate = getCalendar(checkAgainstDate);
        if (gCheckDate.before(gCheckAgainstDate)) {
            status = true;
        }
        return status;
    }

    public static boolean isBetween(Date parseDate, Date fromDate, Date toDate) {

        boolean status = false;
        GregorianCalendar compareDate = getCalendar(parseDate);
        GregorianCalendar dateFrom = getCalendar(fromDate);
        GregorianCalendar dateTo = getCalendar(toDate);

        if (compareDate.after(dateFrom) && compareDate.before(dateTo)) {
            status = true;
        }
        return status;
    }

    public static boolean isBetweenOrEqual(Date parseDate, Date fromDate, Date toDate) {

        boolean status = false;
        GregorianCalendar compareDate = getCalendar(parseDate);
        GregorianCalendar dateFrom = getCalendar(fromDate);
        GregorianCalendar dateTo = getCalendar(toDate);


        if (compareDate.equals(dateFrom) || compareDate.equals(dateTo) || (compareDate.after(
                dateFrom) && compareDate.before(dateTo))) {
            status = true;
        }
        if (dateFrom.after(dateTo)) {
            status = false;
        }
        return status;
    }

    public static boolean isEqual(Date date1, Date date2) {
        GregorianCalendar dateFrom = getCalendar(date1);
        GregorianCalendar dateTo = getCalendar(date2);

        return dateFrom.equals(dateTo);
    }

    private static GregorianCalendar getCalendar(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar;
    }

    public static Integer getMinutesFromTimeStr(String timeStr) {
        Integer minutes = 0;

        if (timeStr != null) {
            String[] split = StringUtils.split(timeStr, ":");
            minutes = Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
        }

        return minutes;
    }

    public static String getNextDateString(Integer currentDate, Integer deliveryDay) {
        int numberOfDaysPerWeek = 7;
        Date date = new Date();
        int dayDiff = (numberOfDaysPerWeek + (deliveryDay - currentDate)) % numberOfDaysPerWeek;

        if (dayDiff <= 0) {
            dayDiff = dayDiff + numberOfDaysPerWeek;
        }
        Date nextDeliveryDate = CalendarUtil.addDate(date, dayDiff);
        return CalendarUtil.getDefaultFormattedDateOnly(nextDeliveryDate);
    }

    public static Map<String, Date> getFinancialYearForDate(Date date) throws AppsException {
        int month = CalendarUtil.getMonth(date);
        int year = CalendarUtil.getYear(date);

        String fromDateStr;
        String toDateStr;

        if (month > 2) {
            fromDateStr = "01".concat("/04").concat("/" + year);
            toDateStr = "31".concat("/03").concat("/" + (year + 1));
        } else {
            fromDateStr = "01".concat("/04").concat("/" + (year - 1));
            toDateStr = "31".concat("/03").concat("/" + (year));
        }

        Date fromDate = getParsedStartDateTime(fromDateStr);
        Date toDate = getParsedStartDateTime(toDateStr);

        Map<String, Date> dateMap = new HashMap<>();
        dateMap.put("statDate", fromDate);
        dateMap.put("endDate", toDate);
        return dateMap;
    }


    public static Integer getWeekOfMonth(Date date) throws AppsException {

        int day = getDate(date);
        int month = getMonth(date);
        int year = getYear(date);

        String monthFirstDayStr = "01".concat("/" + (month + 1)).concat("/" + year);
        Date monthFirstDay = getParsedStartDateTime(monthFirstDayStr);

        GregorianCalendar calendar = getCalendar(monthFirstDay);

        Integer weekCount = 1;
        for (int d = 1; d <= day; d++) {
            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY && d != 1) {
                weekCount++;
            }
            calendar.add(Calendar.DATE, 1);
        }
        return weekCount;
    }

    public static int getHourFromDate(String timeStr) {
        Integer hours = 0;

        if (timeStr != null) {
            String[] split = StringUtils.split(timeStr, ":");
            hours = Integer.parseInt(split[0]);
        }

        return hours;
    }

    public static int getMinutesFromDate(String timeStr) {
        Integer minutes = 0;

        if (timeStr != null) {
            String[] split = StringUtils.split(timeStr, ":");
            minutes = Integer.parseInt(split[1]);
        }

        return minutes;
    }

    public static int getSecondsFromDate(String timeStr) {
        Integer seconds = 0;

        if (timeStr != null) {
            String[] split = StringUtils.split(timeStr, ":");
            seconds = Integer.parseInt(split[2]);
        }

        return seconds;
    }

    public static String extractTimeWithSeconds(Date date) {
        return CalendarUtil.getDefaultFormattedDateTimeWithSeconds(date).substring(11, 19);
    }

    public static boolean isWeekday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        return dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY;
    }
}
