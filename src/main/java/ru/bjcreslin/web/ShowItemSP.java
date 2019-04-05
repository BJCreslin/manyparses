package ru.bjcreslin.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.bjcreslin.service.ItemSPService;

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
