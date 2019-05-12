package ru.bjcreslin.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bjcreslin.model.StroyparkItemDTO;
import ru.bjcreslin.model.WatermanItemDTO;
import ru.bjcreslin.service.AnalyzeSP;
import ru.bjcreslin.service.ItemSPService;
import ru.bjcreslin.service.WatermanItemService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Log
@Controller
@RequestMapping("/analize")
/**
 * todo сделать анализ
 * 1. по цене
 */
@AllArgsConstructor
public class Analyze {
    AnalyzeSP analyzeSP;

    @GetMapping("/cheap_sp")
    public String cheap(Model model) {
        List<StroyparkItemDTO> stroyparkItemDTOList = analyzeSP.findAllCheaps();
        model.addAttribute("cheaplist", stroyparkItemDTOList);
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
