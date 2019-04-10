package ru.bjcreslin.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bjcreslin.DAO.ItemSPDAO;
import ru.bjcreslin.DAO.ItemWatermanDAO;
import ru.bjcreslin.model.ItemSP;
import ru.bjcreslin.service.ItemSPService;
import ru.bjcreslin.service.ItemWatermanService;
import ru.bjcreslin.service.ParsingWaterman;
import ru.bjcreslin.service.SPParsingSP;

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
    ItemWatermanService itemWatermanService;
    @Autowired
    ParsingWaterman parsingWaterman;

    @GetMapping("/additemsp")
    public String action(Model model) {
        //model.addAttribute("myid","12");
        model.addAttribute("item", new ItemSP());
        return "additemsp";
    }

    @PostMapping("/additemsp")
    public String pidUserSubmit(@ModelAttribute ItemSP item, Model model) {
        model.addAttribute("item", item);


        try {
            ItemSPDAO itemSPDAO = spParsingSP.parsingItempSP(item);
            itemSPService.save(itemSPDAO);

            ItemWatermanDAO itemWatermanDAO = itemWatermanService.findByCode(item.getCode());
            if(itemWatermanDAO==null){
            itemWatermanDAO = parsingWaterman.action(item.getCode());}

            itemWatermanService.save(itemWatermanDAO);

        } catch (IOException e) {
            // e.printStackTrace();
        }

        return "redirect:/showallsp";
    }

}
