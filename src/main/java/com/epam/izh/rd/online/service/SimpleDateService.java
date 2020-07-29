package com.epam.izh.rd.online.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;

public class SimpleDateService implements DateService {

    /**
     * Метод парсит дату в строку
     *
     * @param localDate дата
     * @return строка с форматом день-месяц-год(01-01-1970)
     */
    @Override
    public String parseDate(LocalDate localDate) {
        DateTimeFormatter dTime = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String date = localDate.format(dTime);
        return date;
    }

    /**
     * Метод парсит строку в дату
     *
     * @param string строка в формате год-месяц-день часы:минуты (1970-01-01 00:00)
     * @return дата и время
     */
    @Override
    public LocalDateTime parseString(String string) {
        DateTimeFormatter dTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(string, dTimeFormat);
        return localDateTime;
    }

    /**
     * Метод конвертирует дату в строку с заданным форматом
     *
     * @param localDate исходная дата
     * @param formatter формат даты
     * @return полученная строка
     */
    @Override
    public String convertToCustomFormat(LocalDate localDate, DateTimeFormatter formatter) {
        return localDate.format(formatter);
    }

    /**
     * Метод получает следующий високосный год
     *
     * @return високосный год
     */
    @Override
    public long getNextLeapYear() {
        Year year = Year.now();
        boolean isLeapYear = year.isLeap();
        if(isLeapYear == true) {
            return Long.valueOf(String.valueOf(year));
        }
        long leapYear = Long.valueOf(String.valueOf(year));
        while(isLeapYear != true) {
            isLeapYear = Year.isLeap(++leapYear);
        }
        return leapYear;
    }

    /**
     * Метод считает число секунд в заданном году
     *
     * @return число секунд
     */
    @Override
    public long getSecondsInYear(int year) {
        boolean isLeapYear = Year.isLeap(year);
        if(isLeapYear == true) {
            return 366 * 24 * 60 * 60;
        }
        return 365 * 24 * 60 * 60;
    }


}
