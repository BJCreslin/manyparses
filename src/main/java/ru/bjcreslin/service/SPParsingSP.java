package ru.bjcreslin.service;

import lombok.extern.java.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import ru.bjcreslin.Exceptions.WebPerserException;
import ru.bjcreslin.model.StroyparkItemDTO;

import java.io.IOException;
import java.math.BigDecimal;

@Service
@Log
public class SPParsingSP implements ItemParser {

    public StroyparkItemDTO parsingItempSP(StroyparkItemDTO item) throws IOException {
        Document document;

        document = Jsoup.connect(item.getAddress()).
                userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.154 Safari/537.36").
                get();


        log.info(item.toString());
        return item;
    }

    private final static String ITEM_PAGE = "https://stroypark.su/good/";

    @Override
    public StroyparkItemDTO getItemByCode(Long code) throws WebPerserException {
        StroyparkItemDTO item = new StroyparkItemDTO();
        item.setCode(code);
        String addressParsingPage = ITEM_PAGE + code.toString();
        Document document;

        try {
            document = Jsoup.connect(addressParsingPage).
                    userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.154 Safari/537.36").
                    get();
        } catch (IOException e) {
            throw new WebPerserException(addressParsingPage);
        }


        item.setName(getNameFromDocument(document));

        item.setPriceDiscount(getPriceDiscountFromDocument(document));

        item.setPrice(getPriceFromDocument(document));


        /*
                                            <div class="c-product-meta-item">
                                           ......
                                        </div>
                                        <div class="c-product-meta-item"> Код товара:
                                            <strong> 9000901</strong>
                                        </div>
         */

        Element elementItem = document.getElementsByClass("c-content").first();
        Element elementItemWithoutName = elementItem.getElementsByClass("c-product-meta-item").first();
        Element elementWithCode = elementItemWithoutName.nextElementSibling();
        log.severe(elementWithCode.html());


        return item;
    }

    /**
     *
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
     * @param document  html страница
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
     * @param document  html страница
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
