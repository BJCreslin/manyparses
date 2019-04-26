package ru.bjcreslin.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.bjcreslin.DAO.ItemSPDAO;
import ru.bjcreslin.model.ItemSP;
import ru.bjcreslin.model.WatermanItem;
import ru.bjcreslin.service.ItemSPService;
import ru.bjcreslin.service.SPParsingSP;
import ru.bjcreslin.service.WatermanItemParserService;
import ru.bjcreslin.service.WatermanItemService;

import java.io.IOException;

/**
 * Добавляем элемент СП.
 * так же код элемента Водяного
 * если код есть, то ничего не делаем
 * если кода нет, то добавяляем в таблицу
 */
@Controller
public class AddItemSP {
    @Autowired
    ItemSPService itemSPService;
    @Autowired
    SPParsingSP spParsingSP;
    @Autowired
    WatermanItemService watermanItemService;
    @Autowired
    WatermanItemParserService watermanItemParserService;

    @GetMapping("/additemsp")
    public String action(Model model) {
        model.addAttribute("item", new ItemSP());
        return "additemsp";
    }

    /**
     * @param item
     * @param model
     * @return
     */
    @PostMapping("/additemsp")
    public String pidUserSubmit(@ModelAttribute ItemSP item, Model model) {
        model.addAttribute("item", item);

        try {
            ItemSPDAO itemSPDAO = spParsingSP.parsingItempSP(item);
            itemSPService.save(itemSPDAO);

            WatermanItem watermanItem = watermanItemParserService.getWatermanItemByCode(itemSPDAO.getCode());
            watermanItemService.save(watermanItem);
        } catch (IOException e) {
            // e.printStackTrace();
        }

        return "redirect:/showallsp";
    }

}
