package ru.bjcreslin.web;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bjcreslin.model.Item;
import ru.bjcreslin.model.KitItemDTO;
import ru.bjcreslin.service.analyzes.AnalyzeKITServiceIMPL;
import ru.bjcreslin.service.analyzes.AnalyzeSPServiceIMPL;

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
    AnalyzeSPServiceIMPL analyzeSPServiceIMPL;
    AnalyzeKITServiceIMPL analyzeKITServiceIMPL;

    @GetMapping("/cheap_sp")
    public String cheapSP(Model model) {
        List<Item> stroyparkItemDTOList = analyzeSPServiceIMPL.findAllCheaps();
        model.addAttribute("cheaplist", stroyparkItemDTOList);
        model.addAttribute("name","SP");
        return "cheap_analizy";
    }

    @GetMapping("/cheap_kit")
    public String cheapKIT(Model model) {
        List<KitItemDTO> itemList  = analyzeKITServiceIMPL.findAllCheaps();
        model.addAttribute("cheaplist", itemList);
        model.addAttribute("name","Kit");
        return "cheap_analizy";
    }


}
