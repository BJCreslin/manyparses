package ru.bjcreslin.service;

import java.util.Date;

public class DateService {

    public static Long getCurrentDate() {
        Date date = new Date();
        return date.getTime();

    }
}
