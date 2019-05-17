package ru.bjcreslin.web;

import lombok.extern.java.Log;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.bjcreslin.model.DoubleCode;
import ru.bjcreslin.model.WatermanItemDTO;
import ru.bjcreslin.service.ItemWtrmnService;
import ru.bjcreslin.service.parses.ParserWaterman;


/**
 * todo: переделать в watermanItem
 */
@Log
@Controller
@RequestMapping("/waterman")
public class Waterman implements ItemWeb<WatermanItemDTO> {
    private ItemWtrmnService watermanService;
    private ParserWaterman parsingWaterman;

    public Waterman(ItemWtrmnService watermanService, ParserWaterman parsingWaterman) {
        this.watermanService = watermanService;
        this.parsingWaterman = parsingWaterman;
        int maxElementsOnScreen = 10;
        this.pageable = PageRequest.of(0, maxElementsOnScreen).first();
    }

    private Pageable pageable;

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
    public String editPost(@ModelAttribute("edit_waterman") WatermanItemDTO item, BindingResult result, Model model) {
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

        model.addAttribute("item_waterman", watermanService.findAll(pageable));
        model.addAttribute("name", "waterman");
        return "showAllWaterman";
    }

    @GetMapping("/first")
    public String showFirst(Model model) {
        pageable = pageable.first();
        model.addAttribute("item_waterman", watermanService.findAll(pageable));
        model.addAttribute("name", "waterman");
        return "showAllWaterman";
    }

    @GetMapping("/prev")
    public String showPrev(Model model) {
        pageable = pageable.previousOrFirst();
        model.addAttribute("item_waterman", watermanService.findAll(pageable));
        model.addAttribute("name", "waterman");
        return "showAllWaterman";
    }

    @GetMapping("/next")
    public String showNext(Model model) {
        pageable = pageable.next();
        model.addAttribute("item_waterman", watermanService.findAll(pageable));
        model.addAttribute("name", "waterman");
        return "showAllWaterman";
    }

}