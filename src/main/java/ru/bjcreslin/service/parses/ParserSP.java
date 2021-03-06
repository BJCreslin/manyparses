package ru.bjcreslin.service.parses;

import lombok.extern.java.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import ru.bjcreslin.Exceptions.WebParserException;
import ru.bjcreslin.model.domain.StroyparkItemDTO;
import ru.bjcreslin.repository.ItemSPRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@Log
public class ParserSP implements ParserItem {

    ItemSPRepository itemRepository;

    public ParserSP(ItemSPRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    private final static String ITEM_PAGE = "https://stroypark.su/good/";

    @Override
    public StroyparkItemDTO getItemByCode(Long code) throws WebParserException {

        StroyparkItemDTO item = itemRepository.findByCode(code);
        if (item == null) {
            item = new StroyparkItemDTO();
            item.setCode(code);
            item.setDate(LocalDateTime.now());
        } else if (!ParserItem.isNeedToParse(item)) {
            return item;
        }

        item.setCode(code);
        String addressParsingPage = ITEM_PAGE + code.toString();
        Document document;

        item.setCode(code);

        item.setAddress(addressParsingPage);

        try {
            document = Jsoup.connect(addressParsingPage).
                    userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.154 Safari/537.36").
                    get();
        } catch (IOException e) {
            throw new WebParserException(addressParsingPage);
        }


        item.setName(getNameFromDocument(document));

        item.setPriceDiscount(getPriceDiscountFromDocument(document));

        item.setPrice(getPriceFromDocument(document));

        item.setSale(isSale(document));

        item.setCurrency(getCurrencyFromDocument(document));

        item.setMulty(getMultyFromDocument(document));

        item.setDate(LocalDateTime.now());

        log.info("Parsing StroyPArk html: " + item);
        return item;
    }

    private Long getMultyFromDocument(Document document) {
        return 1L;
    }

    private String getCurrencyFromDocument(Document document) {
        return "rub";
    }


    private Boolean isSale(Document document) {
        Element elementItem = document.getElementsByClass("c-content").first();

        return elementItem.text().toLowerCase().contains("РАСПРОДАЖА".toLowerCase());
    }

    /**
     * @param document -html страница
     * @return цена без скидки
     */
    private BigDecimal getPriceFromDocument(Document document) {
        BigDecimal price;
        Element elementItem = document.getElementsByClass("c-content").first();
        Element elementItemWithoutName = elementItem.getElementsByClass("c-product-meta-item").first();

        if (elementItemWithoutName.html().contains("Цена без карты")) {

            price = new BigDecimal(elementItemWithoutName.getElementsByClass("c-product__price-value c-product__price-value--not-prior").
                    first().html().replace("&nbsp;₽", "").
                    replace(" ", ""));

        } else {

            price = new BigDecimal(elementItemWithoutName.getElementsByClass("c-product__price-value").
                    first().html().replace("&nbsp;₽", "").
                    replace(" ", ""));

        }
        return price;
    }

    /**
     * @param document html страница
     * @return цена со скидкой
     */
    private BigDecimal getPriceDiscountFromDocument(Document document) {
         /*
        <div class="c-content">......
         */
        Element elementItem = document.getElementsByClass("c-content").first();
        Element elementItemWithoutName = elementItem.getElementsByClass("c-product-meta-item").first();

        BigDecimal priceDiscount;
        if (elementItemWithoutName.html().contains("Цена без карты")) {


            priceDiscount = new BigDecimal(elementItemWithoutName.getElementsByClass("c-product__price-value o-color--orange").
                    first().html().replace("&nbsp;₽", "").
                    replace(" ", ""));

        } else {
            priceDiscount = new BigDecimal(elementItemWithoutName.getElementsByClass("c-product__price-value").
                    first().html().replace("&nbsp;₽", "").
                    replace(" ", ""));
        }
        return priceDiscount;
    }


    /**
     * @param document html страница
     * @return имя товара
     */
    private String getNameFromDocument(Document document) {
          /*
        <div class="c-content">......
         */
        Element elementItem = document.getElementsByClass("c-content").first();
        /*
        <div class="c-product-title">Радиатор ROYAL THERMO BiLiner 500 4 секции</div>
         */
        return elementItem.getElementsByClass("c-product-title").html();

    }
}
