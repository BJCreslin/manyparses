package ru.bjcreslin.web;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.bjcreslin.model.WatermanItemDTO;
import ru.bjcreslin.service.WatermanItemParserService;
import ru.bjcreslin.service.WatermanItemService;


/**
 * todo: переделать в watermanItem
 */
@Log
@Controller
@RequestMapping("/itemwaterman")
@AllArgsConstructor
public class EditItemWaterman {
    WatermanItemService watermanService;
    private WatermanItemParserService parsingWaterman;


    @GetMapping("/{item}")
    public String editItemSP(@PathVariable WatermanItemDTO item, Model model) {
        model.addAttribute("edit_waterman", item);
        return "edit_waterman";
    }

    @PostMapping("edit")
    public String saveSP(@ModelAttribute("edit_waterman") WatermanItemDTO item, BindingResult result) {
        if (result.hasErrors()) {
            return "edit_waterman";
        }
        watermanService.save(item);
        return "redirect:/itemwaterman/showall";
    }

    @GetMapping("/delete/{item}")
    public String delete(@PathVariable("item") WatermanItemDTO item) {
        watermanService.delete(item);
        return "redirect:/itemwaterman/showall";
    }

    @GetMapping("/reread/{item}")
    public String reread(@PathVariable("item") WatermanItemDTO item) {
        item.setPrice(parsingWaterman.getItemByCode(item.getId()).getPrice());
        return "redirect:/itemwaterman/showall";
    }


    @GetMapping("/showall")
    public String action(Model model) {
        model.addAttribute("item_waterman", watermanService.findAll());
        return "showAllWaterman";
    }

}