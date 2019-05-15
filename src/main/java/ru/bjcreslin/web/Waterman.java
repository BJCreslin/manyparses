package ru.bjcreslin.web;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.bjcreslin.model.DoubleCode;
import ru.bjcreslin.model.WatermanItemDTO;
import ru.bjcreslin.service.ParserWaterman;
import ru.bjcreslin.service.WatermanItemService;


/**
 * todo: переделать в watermanItem
 */
@Log
@Controller
@RequestMapping("/waterman")
@AllArgsConstructor
public class Waterman implements ItemWeb <WatermanItemDTO>{
    WatermanItemService watermanService;
    private ParserWaterman parsingWaterman;


    @GetMapping("/{item}")
    public String editGet(@PathVariable WatermanItemDTO item, Model model) {
        model.addAttribute("edit_waterman", item);
        return "edit_waterman";
    }
//    @GetMapping("/edit/{item}")
//    public String editGet(@PathVariable("item") WatermanItemDTO item, Model model){
//        model.addAttribute ("edit_waterman",item);
//
//    }

    @PostMapping("/edit")
    public String editPost(@ModelAttribute("edit_waterman") WatermanItemDTO item, BindingResult result,Model model) {
        if (result.hasErrors()) {
            return "edit_waterman";
        }
        watermanService.save(item);
        return "redirect:/waterman/showall";
    }


    @GetMapping("/delete/{item}")
    public String delete(@PathVariable("item") WatermanItemDTO item) {
        watermanService.delete(item);
        return "redirect:/waterman/showall";
    }

    @GetMapping("/reread/{item}")
    public String reread(@PathVariable("item") WatermanItemDTO item) {
        item.setPrice(parsingWaterman.getItemByCode(item.getCode()).getPrice());
        return "redirect:/waterman/showall";
    }


    @Override
    public String addGet(Model model) {
        /**
         * сдклать реализацию
         */
        return null;
    }

    @Override
    public String addPost(DoubleCode doubleCode, Model model) {
        /**
         * сдклать реализацию
         */
        return null;
    }

    @GetMapping("/showall")
    public String showAll(Model model) {
        model.addAttribute("item_waterman", watermanService.findAll());
        model.addAttribute("name","waterman");
        return "showAllWaterman";
    }

}