package ru.bjcreslin.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bjcreslin.DAO.ItemSP;
import ru.bjcreslin.model.Item;
import ru.bjcreslin.service.ItemSPService;
import ru.bjcreslin.service.SPParsingSP;

import java.io.IOException;

@Controller
public class AddItemSP {
    @Autowired
    ItemSPService itemSPService;
    @Autowired
    SPParsingSP spParsingSP;

    @GetMapping("/additemsp")
    public String action(Model model) {
        //model.addAttribute("myid","12");
        model.addAttribute("item", new Item());
        return "additemsp";
    }

    @PostMapping("/additemsp")
    public String pidUserSubmit(@ModelAttribute Item item, Model model) {
        model.addAttribute("item", item);


        try {
            ItemSP itemSP = spParsingSP.parsingItempSP(item);
            System.out.println(itemSP);
            itemSPService.save(itemSP);
        } catch (IOException e) {
            // e.printStackTrace();
        }

        return "redirect:/showallsp";
    }

}
