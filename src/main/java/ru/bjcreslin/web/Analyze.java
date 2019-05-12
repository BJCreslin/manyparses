package ru.bjcreslin.web;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bjcreslin.model.Item;
import ru.bjcreslin.model.StroyparkItemDTO;
import ru.bjcreslin.service.AnalyzeSPServiceIMPL;

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

    @GetMapping("/cheap_sp")
    public String cheap(Model model) {
        List<Item> stroyparkItemDTOList = analyzeSPServiceIMPL.findAllCheaps();
        model.addAttribute("cheaplist", stroyparkItemDTOList);
        return "analyze_sp";
    }

}
