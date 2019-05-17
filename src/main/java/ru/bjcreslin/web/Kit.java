package ru.bjcreslin.web;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.bjcreslin.repository.ItemKitRepository;
import ru.bjcreslin.repository.WatermanItemRepository;
import ru.bjcreslin.Exceptions.WebParserException;
import ru.bjcreslin.model.DoubleCode;
import ru.bjcreslin.model.KitItemDTO;
import ru.bjcreslin.model.WatermanItemDTO;
import ru.bjcreslin.service.ItemKitService;
import ru.bjcreslin.service.parses.ParserKit;
import ru.bjcreslin.service.parses.ParserWaterman;

/**
 * показывает таблицу с данными СП
 */
@Log
@RequestMapping("/Kit")
@Controller
@AllArgsConstructor
public class Kit implements ItemWeb<KitItemDTO> {
    static final String ITEM_NAME = "Kit";

    private ItemKitRepository itemRepository;
    private ParserKit parserItem;
    private ParserWaterman watermanItemParserService;
    private WatermanItemRepository watermanItemRepository;
    private ItemKitService itemService;

    @GetMapping("/additem")
    public String addGet(Model model) {
        model.addAttribute("item", new DoubleCode());
        model.addAttribute("item_name", ITEM_NAME);
        return "additem";
    }

    /**
     * Метод добавляет Kit и соответствующий ему Waterman item в базу
     *
     * @param doubleCode doublecode
     * @param model
     * @return
     */
    @PostMapping("/additem")
    public String addPost(@ModelAttribute DoubleCode doubleCode, Model model) {
        try {
            KitItemDTO itemDTO = parserItem.getItemByCode(doubleCode.getFirstCode());
            WatermanItemDTO watermanItem = watermanItemParserService.getItemByCode(doubleCode.getSecondCode());

            watermanItemRepository.saveAndFlush(watermanItem);
            itemDTO.setWatermanItemDTO(watermanItem);

            itemService.save(itemDTO);

        } catch (WebParserException e) {
            e.printStackTrace();
            log.severe("Date from page" + doubleCode);
        }
        return "redirect:/" + ITEM_NAME + "/showall";
    }


    @GetMapping("/showall")
    public String showAll(Model model) {
        model.addAttribute("itemsSP", itemRepository.findAll());
        model.addAttribute("item_name", ITEM_NAME);
        return "showAll";
    }


    @GetMapping("/edit/{item}")
    public String editGet(@PathVariable KitItemDTO item, Model model) {
        model.addAttribute("item", item);
        model.addAttribute("item_name", ITEM_NAME);
        return "edit_item";
    }

    @PostMapping("/edit")
    public String editPost(@ModelAttribute("item") KitItemDTO itemDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "edit_item";
        }

        itemService.save(itemDTO);
        return "redirect:/index";
    }

    @Override
    @GetMapping("/delete/{item}")
    public String delete(@PathVariable("item") KitItemDTO itemDTO) {
        itemRepository.delete(itemDTO);
        return "redirect:/" + ITEM_NAME + "/showall";
    }


}


