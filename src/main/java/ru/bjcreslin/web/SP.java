package ru.bjcreslin.web;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.bjcreslin.DAO.WatermanItemRepository;
import ru.bjcreslin.Exceptions.WebParserException;
import ru.bjcreslin.model.DoubleCode;
import ru.bjcreslin.model.StroyparkItemDTO;
import ru.bjcreslin.model.WatermanItemDTO;
import ru.bjcreslin.service.ItemSPService;
import ru.bjcreslin.service.ParserSP;
import ru.bjcreslin.service.ParserWaterman;
import ru.bjcreslin.service.WatermanItemService;

/**
 * показывает таблицу с данными СП
 */
@Log
@RequestMapping("/sp")
@Controller
@AllArgsConstructor
public class SP implements ItemWeb<StroyparkItemDTO> {
    private ItemSPService itemSPService;

    private ParserSP itemSPParser;
    private WatermanItemService watermanItemService;
    private ParserWaterman watermanItemParserService;
    private WatermanItemRepository watermanItemRepository;

    @GetMapping("/additem")
    public String addGet(Model model) {
        model.addAttribute("item", new DoubleCode());
        model.addAttribute("name", "StroyPark");
        return "additem";
    }

    /**
     * Метод добавляет itemSP и соответствующий ему Waterman item в базу
     *
     * @param doubleCode doublecode
     * @param model
     * @return
     */
    @PostMapping("/additem")
    public String addPost(@ModelAttribute DoubleCode doubleCode, Model model) {
        // model.addAttribute("item", doubleCode);
        try {
            log.severe("Date from page" + doubleCode);
            StroyparkItemDTO stroyparkItemDTO = itemSPParser.getItemByCode(doubleCode.getFirstCode());
            WatermanItemDTO watermanItem = watermanItemParserService.getItemByCode(doubleCode.getSecondCode());

            watermanItemRepository.saveAndFlush(watermanItem);
            stroyparkItemDTO.setWatermanItemDTO(watermanItem);

            itemSPService.save(stroyparkItemDTO);


        } catch (WebParserException e) {
            e.printStackTrace();
        }
        return "redirect:/sp/showall";
    }


    @GetMapping("/showall")
    public String showAll(Model model) {
        model.addAttribute("itemsSP", itemSPService.findAll());
        model.addAttribute("name", "sp");
        return "showAll";
    }


    @GetMapping("/edit/{item}")
    public String editGet(@PathVariable StroyparkItemDTO item, Model model) {
        model.addAttribute("item", item);
        model.addAttribute("item_name", "Stroypark");
        return "edit_item";
    }

    @PostMapping("/edit")
    public String editPost(@ModelAttribute("edit_sp") StroyparkItemDTO editStroyparkItemDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "edit_item";
        }
        itemSPService.save(editStroyparkItemDTO);
        return "redirect:/index";
    }

    @Override
    @GetMapping("/delete/{item}")
    public String delete(@PathVariable("item") StroyparkItemDTO stroyparkItemDTO) {
        itemSPService.delete( stroyparkItemDTO);
        return "redirect:/sp/showall";
    }


}


