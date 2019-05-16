package ru.bjcreslin.service;

import ru.bjcreslin.model.Item;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * функциональный класс работы со временем
 */
public class DateService {

    public static Long getCurrentDate() {
        Date date = new Date();
        return date.getTime();

    }

    public static boolean isTimeToParse(Item item){
        if (item == null) {return true;}

        LocalDateTime itemTime;
        try {
            itemTime = item.getDate();
            LocalDateTime nowTime = LocalDateTime.now();
            return nowTime.minusHours(12L).isAfter(itemTime);
        } catch (NullPointerException ex) {
            return true;
        }


        }
    }

