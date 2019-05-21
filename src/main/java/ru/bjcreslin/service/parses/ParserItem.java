package ru.bjcreslin.service.parses;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
        try {
            return nowTime.minusHours(12L).isAfter(itemTime);
        } catch (Exception e) {
           return true;
        }

    }

    static boolean hasClass(Element element, String className) {
        Elements bodyChildren = new Elements();
        for (Element e : element.getAllElements()) {
            bodyChildren.addAll(e.children());
        }
        return bodyChildren.hasClass(className);

    }

}
