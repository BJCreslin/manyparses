package ru.bjcreslin.service;

import lombok.extern.java.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import ru.bjcreslin.Exceptions.WebParserException;
import ru.bjcreslin.model.KitItemDTO;

import java.io.IOException;
import java.math.BigDecimal;


/**
 * парсинг сайта https://tomsk.kit-teplo.ru
 * Внимание! Сайт часто выдает java.net.ConnectException . В Хроме в это время, всё работает.
 * Не знаю в чем дело.
 */
@Service
@Log
public class ParserKit implements ParserItem {
    private final static String KITITEM_FIND_ADDRESS = "https://tomsk.kit-teplo.ru/search/?q=";
    private final static String KIT_ADDRESS = "https://tomsk.kit-teplo.ru";

    public KitItemDTO getItemByCode(Long code) throws WebParserException {
        KitItemDTO kitItemDTO = new KitItemDTO();
        kitItemDTO.setCode(code);
        String addressParsingPage = KITITEM_FIND_ADDRESS + code.toString();

        try {
            Document document = Jsoup.connect(addressParsingPage).
                    userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.154 Safari/537.36").
                    get();

/*
<div class="product newproduct">

 */
            Element element = document.getElementsByClass("product newproduct").first();

            kitItemDTO.setName(getProduct__name(element));

            kitItemDTO.setPrice(getPrice(element));

            kitItemDTO.setCurrency(getCurrency(element));

            kitItemDTO.setAddress(getAddress(element));

            kitItemDTO.setPriceDiscount(getPriceDiscount(element));

            kitItemDTO.setMulty(getMulty());

            kitItemDTO.setSale(getSale());

            log.info(kitItemDTO.toString());

        } catch (IOException e) {
            e.printStackTrace();
            log.severe("KitParserItem, didn't parse " + code);
            throw new WebParserException(addressParsingPage);
        }

        return kitItemDTO;
    }


    /*
   <a id="" title="Купить" data-toggle="modal" data-target="#cartData" class="product__link btn " href="#" onclick="addtocart('84646','bx_60783_quantity',this);itemAddedModal('Радиатор алюминиевый ROMMER Optima 500/80 1 секция','402');" rel="nofollow">
 */
    private BigDecimal getPriceDiscount(Element element) {
        return new BigDecimal(element.getElementsByClass("product__link btn ").attr("onclick").
                replaceFirst("^a.+itemAddedModal\\('", "").
                replaceFirst("^.+(',')+", "").
                replaceFirst("('\\);)+$", ""));
    }

    private Boolean getSale() {
        /*
        Считаем, что распродаж нет
         */
        return false;
    }

    private Long getMulty() {
        /*
        Вроде везде по 1 штуке
         */
        return 1L;
    }


    /*
            <div class="product newproduct">
                                       <span class="product__article">Арт. </span>
                                       <a href="/catalog/radiatory12/alyuminievye68/radiatory_rommer301/rommer_vysota_500_mm312/
                                       radiator_alyuminievyy_rommer_optima_500_80_sektsiya/" class="product__img-wrap">
            */
    private String getAddress(Element element) {
        return KIT_ADDRESS + element.select("a").attr("href");
    }


    /* <span class="rub">a</span> */
    private String getCurrency(Element element) {
        //     return element.hasClass("rub") ? "rub" : "other";- не работает
        /**
         * todo сделать метод. пока вставил заглушку
         */
        return "rub";
    }

    /*
               <div class="product__price product__price--red">389.4
                                                   <span class="rub">a</span>
                                               </div>
                */
    private BigDecimal getPrice(Element element) {
        return new BigDecimal(element.getElementsByClass("product__price product__price--red").first().ownText());
    }

    /*
 <a href="/catalog/radiatory12/alyuminievye68/radiatory_rommer301/rommer_vysota_500_mm312/radiator_alyuminievyy_rommer_optima_500_80_sektsiya/"
  class="product__name">Радиатор алюминиевый ROMMER Optima 500/80 1 секция</a>
 */
    private String getProduct__name(Element element) {
        return element.getElementsByClass("product__name").html();
    }
}
