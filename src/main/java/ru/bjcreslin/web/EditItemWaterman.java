package ru.bjcreslin.web;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.bjcreslin.delete.ItemWatermanDAO;
import ru.bjcreslin.delete.ItemWatermanService;
import ru.bjcreslin.delete.ParsingWaterman;
import ru.bjcreslin.model.WatermanItem;


/**
 * todo: переделать в watermanItem
 */
@Log
@Controller
@RequestMapping("/itemwaterman")
@AllArgsConstructor
public class EditItemWaterman {
    ItemWatermanService watermanService;
    private ParsingWaterman parsingWaterman;


    @GetMapping("/{item}")
    public String editItemSP(@PathVariable WatermanItem item, Model model) {
        model.addAttribute("edit_waterman", item);
        return "edit_waterman";
    }

    @PostMapping("edit")
    public String saveSP(@ModelAttribute("edit_waterman") ItemWatermanDAO item, BindingResult result) {
        if (result.hasErrors()) {
            return "edit_waterman";
        }
        watermanService.save(item);
        return "redirect:/showall";
    }

    @GetMapping("/delete/{item}")
    public String delete(@PathVariable("item") ItemWatermanDAO item) {
        watermanService.delete(item);
        return "redirect:/showall";
    }

    @GetMapping("/reread/{item}")
    public String reread(@PathVariable("item") ItemWatermanDAO item) {
        item.setPrice(parsingWaterman.getPrice(item.getId()));
        return "redirect:/watermanitem/showall";
    }

}