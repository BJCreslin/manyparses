package ru.bjcreslin.web;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.bjcreslin.repository.WatermanItemRepository;
import ru.bjcreslin.Exceptions.WebParserException;
import ru.bjcreslin.model.DoubleCode;
import ru.bjcreslin.model.StroyparkItemDTO;
import ru.bjcreslin.model.WatermanItemDTO;
import ru.bjcreslin.service.ItemSPService;
import ru.bjcreslin.service.parses.ParserSP;
import ru.bjcreslin.service.parses.ParserWaterman;

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
        model.addAttribute("itemsSP", itemSPService.findAll(pageable.first()));
        model.addAttribute("item_name", ITEM_NAME);
        return "showAll";
    }

    @GetMapping("/next")
    /***
     * todo: Почему то не работате кнопка
     */
    public String showNext(Model model) {
        model.addAttribute("itemsSP", itemSPService.findAll(pageable.next()));
        model.addAttribute("item_name", ITEM_NAME);
        return "showAll";
    }

    @GetMapping("/prev")
    public String showPrev(Model model) {
        model.addAttribute("itemsSP", itemSPService.findAll(pageable.previousOrFirst()));
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


}


