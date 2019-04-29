package ru.bjcreslin.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bjcreslin.service.WatermanItemService;

/**
 * показывает таблицу с данными СП
 */

@Controller
@RequestMapping("/watermanitem")
@AllArgsConstructor
public class ShowItemWaterman {
    private WatermanItemService itemWatermanService;


    @GetMapping("/showall")
    public String action(Model model) {
        model.addAttribute("item_waterman", itemWatermanService.findAll());
        return "showAllWaterman";
    }

}
