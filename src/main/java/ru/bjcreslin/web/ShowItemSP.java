package ru.bjcreslin.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bjcreslin.service.ItemSPService;

/**
 * показывает таблицу с данными СП
 */
@Controller
public class ShowItemSP {
    @Autowired
    private ItemSPService itemSPService;

    @GetMapping("/showallsp")
    public String action(Model model) {
       model.addAttribute("itemsSP", itemSPService.findAll());
        return "showAllSP";
    }

}
