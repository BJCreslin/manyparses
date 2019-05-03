package ru.bjcreslin.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.bjcreslin.service.ItemSPService;

/**
 * показывает таблицу с данными СП
 */
@Controller
@AllArgsConstructor
public class ShowItemSP {
    private ItemSPService itemSPService;

    @GetMapping("/showallsp")
    public String action(Model model) {
       model.addAttribute("itemsSP", itemSPService.findAll());
        return "showAllSP";
    }

}
