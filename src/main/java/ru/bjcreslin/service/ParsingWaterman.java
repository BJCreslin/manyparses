package ru.bjcreslin.service;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import ru.bjcreslin.DAO.ItemWatermanDAO;
import ru.bjcreslin.model.ItemWaterman;

import java.io.IOException;
import java.math.BigDecimal;

@Service
public class ParsingWaterman {

    public ItemWaterman pageParser(Long code) throws IOException {
        ItemWaterman itemWaterman = new ItemWaterman();
        String address = "http://xn--b1aeppdc1j.xn--p1ai/search/result?q=" + code;
        Document document = Jsoup.connect(address).
                userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.154 Safari/537.36").
                get();

        System.out.println(document.html());
        String name = document.getElementsByClass("hl--light").text();
        BigDecimal price = new BigDecimal(document.getElementsByClass("product-wrap").
                first().text().replace(" ", "").
                replace("\u20BDДобавитьвкорзину", ""));
        itemWaterman.setAddress(address);
        itemWaterman.setCode(code);
        itemWaterman.setName(name);
        itemWaterman.setPrice(price);
        return itemWaterman;
    }

    public ItemWatermanDAO action(Long code) throws IOException {
        ItemWaterman itemWaterman = pageParser(code);
        ItemWatermanDAO itemWatermanDAO = new ItemWatermanDAO();
        itemWatermanDAO.setAddress(itemWaterman.getAddress());
        itemWatermanDAO.setCode(itemWaterman.getCode());
        itemWatermanDAO.setName(itemWaterman.getName());
        itemWatermanDAO.setPrice(itemWaterman.getPrice());
        return itemWatermanDAO;
    }


}
