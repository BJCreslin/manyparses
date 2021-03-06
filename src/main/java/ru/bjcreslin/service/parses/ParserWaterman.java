package ru.bjcreslin.service.parses;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import ru.bjcreslin.model.domain.WatermanItemDTO;
import ru.bjcreslin.repository.WatermanItemRepository;

import java.io.IOException;
import java.math.BigDecimal;

@Service
@Log
@AllArgsConstructor
public class ParserWaterman implements ParserItem {
    //private final static String WATERMAN_ADDRESS = "http://www.waterman-t.ru/";
    private final static String WATERMAN_FIND_PAGE = "http://www.waterman-t.ru/search/result?q=";
    private final static String ITEM_PAGE = "http://www.waterman-t.ru/products/";


    WatermanItemRepository itemRepository;

    /**
     * Возвращает товар по заданному коду
     *
     * @param code -code from Base
     * @return WatermanItem;
     */
    public WatermanItemDTO getItemByCode(Long code) {

        WatermanItemDTO item = itemRepository.findByCode(code);
        try {
            if ((item == null) | (item.getAddress().isEmpty()) | (item.getName().isEmpty()) | (item.getPrice() == null)) {
                item = new WatermanItemDTO();
                item.setCode(code);

            } else if (!ParserItem.isNeedToParse(item)) {
                return item;
            }
        } catch (Exception e) {
            item = new WatermanItemDTO();
            item.setCode(code);

        }
        try {
            String addressParsingPage = WATERMAN_FIND_PAGE + code.toString();
            Document document = Jsoup.connect(addressParsingPage).
                    userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.154 Safari/537.36").
                    get();
            // <div class="search--nav">
            Elements element = document.getElementsByClass("search--nav");
            //<a class="search--item products--item" data-code="88b44d61-8c05-11e8-a8cc-001e6727034e" href="/products/88b44d61-8c05-11e8-a8cc-001e6727034e">
            Element elementForAddress = element.first().getElementsByClass("search--item products--item").first();
            String itemAdress = elementForAddress.attr("data-code");

            String AddressItemPage = ITEM_PAGE + itemAdress;

            item.setAddress(AddressItemPage);
            Document itemDocument = Jsoup.connect(AddressItemPage).
                    userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.154 Safari/537.36").
                    get();

            addItemGroupeFromHTMLToItem(item, itemDocument);
            // <tr class="_product-config--item" data-sku="2 124">
            for (Element elementsItem : itemDocument.getElementsByClass("_product-config--item")) {
                String codeElements = getCodeElementFromHTML(elementsItem);
                if (codeElements.equals(code.toString())) {

                    for (Element element1 : elementsItem.getElementsByTag("td")) {
                        addItemNameFromHTMLToItem(item, element1);
                        addItemCurrencyFromHTMLToItem(item, element1);
                    }
                    addItemPriceFromHTMLToItem(item, elementsItem);
                }
            }

            log.info(item.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return item;
    }

    private void addItemGroupeFromHTMLToItem(WatermanItemDTO item, Document itemDocument) {
        /*
         <div class="breadcrumbs">
        <a href="/" class="breadcrumb--text">
            <span>Главная</span>
        </a>
        <span class="breadcrumb--separator">•</span>
        <a href="/catalog/categories/b867b845-8bed-11e8-a8cc-001e6727034e" class="breadcrumb--text">
            <span>Инструменты, герметики, расходные материалы</span>
        </a>
        <span class="breadcrumb--separator">•</span>
        <a href="/catalog/category/ff77c587-8bfe-11e8-a8cc-001e6727034e" class="breadcrumb--text">
            <span>Уплотнительные материалы, прокладки</span>
        </a>
    </div>
         */
        String group = itemDocument.getElementsByClass("breadcrumb--text").last().html().
                replace("<span>", "").replace("</span>", "").trim();
        item.setGroup(group);
    }

    private void addItemPriceFromHTMLToItem(WatermanItemDTO item, Element elementsItem) {
        //<span class="js-actualPrice">49</span>
        String price = elementsItem.getElementsByClass("js-actualPrice").html();
        item.setPrice(new BigDecimal(price));
    }

    private void addItemCurrencyFromHTMLToItem(WatermanItemDTO item, Element element1) {
        // <td data-th="Цена, руб">
        if (element1.attr("data-th").equals("Цена, руб")) {
            item.setCurrency("Rub");
        } else {
            item.setCurrency("Other");
        }
    }

    private void addItemNameFromHTMLToItem(WatermanItemDTO item, Element element1) {
        // <td data-th="Наименование">Лен (Италия)  50гр.</td>
        if (element1.attr("data-th").equals("Наименование")) {
            item.setName(element1.html());
        }
    }

    private String getCodeElementFromHTML(Element elementsItem) {
        // <tr class="_product-config--item" data-sku="2 124">
        return elementsItem.attr("data-sku").trim().replace(" ", "");
    }
}
