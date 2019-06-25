package ru.bjcreslin.service;

import ru.bjcreslin.model.domain.Item;

import java.time.LocalDateTime;

/**
 * функциональный класс работы со временем
 */
public class DateService {
    private static Long minHoursToParse = 12L;

    /**
     * @return текущая дата
     */
    public static LocalDateTime getCurrentDate() {
        return LocalDateTime.now();
    }

    /**
     * Возвращает true, если с даты в item прошло более minHoursToParse часов
     *
     * @param item
     * @return да или нет
     */
    public static boolean isTimeToParse(Item item) {
        if (item == null) {
            return true;
        }

        LocalDateTime itemTime;
        try {
            itemTime = item.getDate();
            LocalDateTime nowTime = LocalDateTime.now();
            return nowTime.minusHours(minHoursToParse).isAfter(itemTime);
        } catch (NullPointerException ex) {
            return true;
        }


    }
}

