package ru.bjcreslin.web;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.bjcreslin.model.StroyparkItemDTO;
import ru.bjcreslin.service.ItemSPService;

@Log
@Controller
@RequestMapping("/itemsp")
@AllArgsConstructor
public class EditSp {
    ItemSPService itemSPService;


    @GetMapping("{item}")
    public String editItemSP(@PathVariable StroyparkItemDTO item, Model model) {
        model.addAttribute("edit_sp", item);
        model.addAttribute("item_name", "Stroypark");
        return "edit_item";
    }

    @PostMapping("edit")
    public String saveSP(@ModelAttribute("edit_sp") StroyparkItemDTO editStroyparkItemDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "edit_item";
        }
        itemSPService.save(editStroyparkItemDTO);
        return "redirect:/index";
    }

    @GetMapping("/delete/{item}")
    public String delete(@PathVariable("item") StroyparkItemDTO stroyparkItemDTO) {
        itemSPService.delete(stroyparkItemDTO);
        return "redirect:/showallsp";
    }

}
