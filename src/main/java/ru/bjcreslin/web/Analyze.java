package ru.bjcreslin.web;

import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bjcreslin.DAO.ItemSPDAO;
import ru.bjcreslin.DAO.ItemWatermanDAO;
import ru.bjcreslin.service.ItemSPService;
import ru.bjcreslin.service.ItemWatermanService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Log
@Controller
@RequestMapping("/analize")
public class Analyze {
    @Autowired
    private ItemSPService itemSPService;
    @Autowired
    private ItemWatermanService itemWatermanService;

    @GetMapping("/cheap_sp")
    public String cheap(Model model) {
        List<ItemSPDAO> itemSPDAOList = itemSPService.findAll();
        List<doubleClass> doubleClassList = new ArrayList<>();
        for (ItemSPDAO item : itemSPDAOList) {
            ItemWatermanDAO itemWatermanDAO = itemWatermanService.findByCode(item.getCode());
            if (itemWatermanDAO != null) {
                if (itemWatermanDAO.getPrice().compareTo(item.getPriceDiscountSP()) > 0) {
                    doubleClassList.add(new doubleClass(item.getId(), itemWatermanDAO.getCode(),
                            item.getNameSP(), itemWatermanDAO.getName(),
                            item.getAddressSP(), item.getPriceDiscountSP(), itemWatermanDAO.getPrice()));
                }
            }
        }
        model.addAttribute("cheaplist", doubleClassList);
        return "analyze_sp";
    }

    @Data
    public class doubleClass {
        Long id;

        public doubleClass(Long id, Long code, String nameSp, String nameWaterman, String addressSP, BigDecimal priceSp, BigDecimal priceWaterman) {
            this.id = id;
            this.code = code;
            this.nameSP = nameSp;
            this.nameWaterman = nameWaterman;
            this.addressSP = addressSP;
            this.priceSp = priceSp;
            this.priceWaterman = priceWaterman;
        }

        Long code;
        String nameSP;
        String nameWaterman;
        String addressSP;
        BigDecimal priceSp;
        BigDecimal priceWaterman;

    }
}
