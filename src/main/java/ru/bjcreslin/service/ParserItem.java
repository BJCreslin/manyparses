package ru.bjcreslin.service;

import ru.bjcreslin.Exceptions.WebParserException;
import ru.bjcreslin.model.Item;

import java.time.LocalDateTime;

public interface ParserItem {

    Item getItemByCode(Long code) throws WebParserException;
    //public Item getItemByaddress(String address);

    /**
     * Вычисляет, прошло ли 12 часов с предыдущего парсинга элемента?
     *
     * @param item
     * @return прошло ли 12 часов с предыдущего парсинга элемента?
     */
    static boolean isNeedToParse(Item item) {
        LocalDateTime itemTime;
        try {
            itemTime = item.getDate();
        } catch (NullPointerException ex) {
            return true;
        }
        LocalDateTime nowTime = LocalDateTime.now();
        return nowTime.minusHours(12L).isAfter(itemTime);

    }

}
