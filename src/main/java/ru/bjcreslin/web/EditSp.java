package ru.bjcreslin.web;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.bjcreslin.DAO.ItemStroyparkDAO;
import ru.bjcreslin.service.ItemSPService;

@Log
@Controller
@RequestMapping("/itemsp")
@AllArgsConstructor
public class EditSp {
    ItemSPService itemSPService;



    @GetMapping("{item}")
    public String editItemSP(@PathVariable ItemStroyparkDAO item, Model model) {
        model.addAttribute("edit_sp", item);
        return "edit_sp";
    }

    @PostMapping("edit")
    public String saveSP(@ModelAttribute("edit_sp") ItemStroyparkDAO editItemStroyparkDAO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "edit_sp";
        }
        itemSPService.save(editItemStroyparkDAO);
        return "redirect:/index";
    }

    @GetMapping("/delete/{item}")
    public String delete(@PathVariable ("item") ItemStroyparkDAO itemStroyparkDAO) {
        itemSPService.delete(itemStroyparkDAO);
        return "redirect:/showallsp";
    }

}
