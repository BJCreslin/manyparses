package ru.bjcreslin.web;

import lombok.extern.java.Log;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.bjcreslin.Exceptions.WebParserException;
import ru.bjcreslin.model.DoubleCode;
import ru.bjcreslin.model.domain.StroyparkItemDTO;
import ru.bjcreslin.model.domain.WatermanItemDTO;
import ru.bjcreslin.repository.WatermanItemRepository;
import ru.bjcreslin.service.DateService;
import ru.bjcreslin.service.ItemSPService;
import ru.bjcreslin.service.parses.ParserSP;
import ru.bjcreslin.service.parses.ParserWaterman;

import java.util.List;

/**
 * показывает таблицу с данными СП
 */
@Log
@RequestMapping("/StroyPark")
@Controller
public class SP implements ItemWeb<StroyparkItemDTO> {
    static final String ITEM_NAME = "StroyPark";

    private ItemSPService itemSPService;
    private ParserSP itemSPParser;
    private ParserWaterman watermanItemParserService;
    private WatermanItemRepository watermanItemRepository;

    public SP(ItemSPService itemSPService, ParserSP itemSPParser, ParserWaterman watermanItemParserService,
              WatermanItemRepository watermanItemRepository) {
        this.itemSPService = itemSPService;
        this.itemSPParser = itemSPParser;
        this.watermanItemParserService = watermanItemParserService;
        this.watermanItemRepository = watermanItemRepository;
        this.pageable = PageRequest.of(0, maxElementsOnScreen);
    }

    private Pageable pageable;

    @GetMapping("/additem")
    public String addGet(Model model) {
        model.addAttribute("item", new DoubleCode());
        model.addAttribute("item_name", ITEM_NAME);
        return "additem";
    }

    /**
     * Метод добавляет itemSP и соответствующий ему Waterman item в базу
     *
     * @param doubleCode doublecode
     */
    @PostMapping("/additem")
    public String addPost(@ModelAttribute DoubleCode doubleCode, Model model) {
        try {
            StroyparkItemDTO stroyparkItemDTO = itemSPParser.getItemByCode(doubleCode.getFirstCode());
            WatermanItemDTO watermanItem = watermanItemParserService.getItemByCode(doubleCode.getSecondCode());

            watermanItemRepository.saveAndFlush(watermanItem);
            stroyparkItemDTO.setWatermanItemDTO(watermanItem);

            itemSPService.save(stroyparkItemDTO);

        } catch (WebParserException e) {
            e.printStackTrace();
            log.severe("Date from page" + doubleCode);
        }
        return "redirect:/" + ITEM_NAME + "/showall";
    }


    @GetMapping("/showall")
    public String showAll(Model model) {
        model.addAttribute("itemsSP", itemSPService.findAll(pageable));
        model.addAttribute("item_name", ITEM_NAME);
        return "showAll";
    }

    @GetMapping("/first")
    public String showFirst(Model model) {
        pageable = pageable.first();
        model.addAttribute("itemsSP", itemSPService.findAll(pageable));
        model.addAttribute("item_name", ITEM_NAME);
        return "showAll";
    }

    @GetMapping("/next")
    public String showNext(Model model) {
        pageable = pageable.next();
        model.addAttribute("itemsSP", itemSPService.findAll(pageable));
        model.addAttribute("item_name", ITEM_NAME);
        return "showAll";
    }

    @GetMapping("/prev")
    public String showPrev(Model model) {
        pageable = pageable.previousOrFirst();
        model.addAttribute("itemsSP", itemSPService.findAll(pageable));
        model.addAttribute("item_name", ITEM_NAME);
        return "showAll";
    }

    @GetMapping("/edit/{item}")
    public String editGet(@PathVariable StroyparkItemDTO item, Model model) {
        model.addAttribute("item", item);
        model.addAttribute("item_name", ITEM_NAME);
        return "edit_item";
    }

    @PostMapping("/edit")
    public String editPost(@ModelAttribute("item") StroyparkItemDTO editStroyparkItemDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "edit_item";
        }
        itemSPService.save(editStroyparkItemDTO);
        return "redirect:/index";
    }

    @Override
    @GetMapping("/delete/{item}")
    public String delete(@PathVariable("item") StroyparkItemDTO stroyparkItemDTO) {
        itemSPService.delete(stroyparkItemDTO);
        return "redirect:/" + ITEM_NAME + "/showall";
    }

    @GetMapping("/rereadall")
    public String rereadall() {
        reReadAll();
        return "redirect:/" + ITEM_NAME + "/showall";
    }


    private void reReadAll() {
        List<StroyparkItemDTO> itemDTOList = itemSPService.findAll();
        for (StroyparkItemDTO itemDTO : itemDTOList) {
            StroyparkItemDTO itemDTOtemp = new StroyparkItemDTO();
            try {
                itemDTOtemp = itemSPParser.getItemByCode(itemDTO.getCode());
            } catch (WebParserException e) {
                itemDTO.setPrice(itemDTOtemp.getPrice());
                itemDTO.setPriceDiscount(itemDTOtemp.getPriceDiscount());
                itemDTO.setSale(itemDTOtemp.getSale());
                itemDTO.setMulty(itemDTOtemp.getMulty());
                itemDTO.setCurrency(itemDTOtemp.getCurrency());
                itemDTO.setAddress(itemDTOtemp.getAddress());
                itemDTO.setName(itemDTOtemp.getName());
                itemDTO.setDate(DateService.getCurrentDate());

                WatermanItemDTO watermanItemDTO = itemDTO.getWatermanItemDTO();
                watermanItemDTO.addSPItem(itemDTO);
                watermanItemRepository.saveAndFlush(watermanItemDTO);
                itemSPService.save(itemDTO);
            }
        }
    }
}


