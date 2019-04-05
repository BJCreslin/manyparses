package ru.bjcreslin.service;

import lombok.extern.java.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import ru.bjcreslin.DAO.ItemSP;
import ru.bjcreslin.model.Item;

import java.io.IOException;
import java.math.BigDecimal;

@Service
@Log
public class SPParsingSP {

    public ItemSP parsingItempSP(Item item) throws IOException {
        Document document;

        document = Jsoup.connect(item.getAddress()).
                userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.154 Safari/537.36").
                get();
        Element elementItem = document.getElementsByClass("c-content").first();

        item.setName(elementItem.getElementsByClass("c-product-title").html());

        Element elementItemWithoutName = elementItem.getElementsByClass("c-product-meta-item").first();


        if (elementItemWithoutName.html().contains("Цена без карты")) {

            item.setPrice(new BigDecimal(elementItemWithoutName.getElementsByClass("c-product__price-value c-product__price-value--not-prior").
                    first().html().replace("&nbsp;₽", "").
                    replace(" ", "")));

            item.setPriceDiscount(new BigDecimal(elementItemWithoutName.getElementsByClass("c-product__price-value o-color--orange").
                    first().html().replace("&nbsp;₽", "").
                    replace(" ", "")));

        } else {
            item.setPriceDiscount(new BigDecimal(elementItemWithoutName.getElementsByClass("c-product__price-value").
                    first().html().replace("&nbsp;₽", "").
                    replace(" ", "")));

            item.setPrice(item.getPriceDiscount());

        }

        log.info(item.toString());
        return itemToSP(item);
    }

    private ItemSP itemToSP(Item item) {
        ItemSP itemSP = new ItemSP();
        itemSP.setAddressSP(item.getAddress());
        itemSP.setCode(item.getCode());
        itemSP.setNameSP(item.getName());
        itemSP.setPriceSP(item.getPrice());
        itemSP.setPriceDiscountSP(item.getPriceDiscount());
        if (item.getPriceDiscount().equals(item.getPrice())) {
            itemSP.setSale(true);
        } else {
            itemSP.setSale(false);
        }
        return itemSP;
    }



}
