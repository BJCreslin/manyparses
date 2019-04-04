package ru.bjcreslin.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import ru.bjcreslin.DAO.ItemSP;
import ru.bjcreslin.model.Item;

import java.io.IOException;
import java.math.BigDecimal;

@Service
public class SPParsingSP {

    public ItemSP parsingItempSP(Item item) throws IOException {
        Document document;

        document = Jsoup.connect(item.getAddress()).
                userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.154 Safari/537.36").
                get();
        Elements elements;

        item.setName(document.getElementsByClass("c-product-title").first().html());
//        System.out.println(document.getElementsByClass("c-product__price-value c-product__price-value--not-prior").first().html().
//                replace("&nbsp;₽", "").replace(" ", "").replace(".", "."));
        item.setPrice(new BigDecimal(document.getElementsByClass("c-product__price-value c-product__price-value--not-prior").first().html().
                replace("&nbsp;₽", "").replace(" ", "").replace(".", ".")));

        item.setPriceDiscount(new BigDecimal(document.getElementsByClass("c-product__price-value o-color--orange").first().html().
                replace("&nbsp;₽", "").replace(" ", "").replace(".", ".")));
        System.out.println(item);

        return itemToSP(item);
    }

    private ItemSP itemToSP(Item item) {
        ItemSP itemSP = new ItemSP();
        itemSP.setAddressSP(item.getAddress());
        itemSP.setCode(item.getCode());
        itemSP.setNameSP(item.getName());
        itemSP.setPriceSP(item.getPrice());
        itemSP.setPriceDiscountSP(item.getPriceDiscount());
        return itemSP;
    }
}
