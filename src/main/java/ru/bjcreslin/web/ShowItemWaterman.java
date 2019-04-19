package ru.bjcreslin.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bjcreslin.service.ItemSPService;
import ru.bjcreslin.service.ItemWatermanService;
import ru.bjcreslin.service.ParsingWaterman;

/**
 * показывает таблицу с данными СП
 */
@Controller
@RequestMapping("/watermanitem")
public class ShowItemWaterman {
    @Autowired
    private ItemWatermanService itemWatermanService;



    @GetMapping("/showall")
    public String action(Model model) {
        model.addAttribute("item_waterman", itemWatermanService.findAll());
        return "showAllWaterman";
    }

}
