package ru.bjcreslin.web;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.bjcreslin.DAO.ItemSPDAO;
import ru.bjcreslin.DAO.ItemWatermanDAO;
import ru.bjcreslin.service.ItemSPService;
import ru.bjcreslin.service.ItemWatermanService;

@Log
@Controller
@RequestMapping("/itemwaterman")
public class EditItemWaterman {
    @Autowired
    ItemWatermanService watermanService;



    @GetMapping("/{item}")
    public String editItemSP(@PathVariable ItemWatermanDAO item, Model model) {
        model.addAttribute("edit_waterman", item);
        return "edit_waterman";
    }

    @PostMapping("edit")
    public String saveSP(@ModelAttribute("edit_waterman") ItemWatermanDAO item, BindingResult result) {
        if (result.hasErrors()) {
            return "edit_waterman";
        }
        watermanService.save(item);
        return "redirect:/index";
    }

    @GetMapping("/delete/{item}")
    public String delete(@PathVariable ("item")ItemWatermanDAO item) {
        watermanService.delete(item);
        return "redirect:/showall";
    }

}
