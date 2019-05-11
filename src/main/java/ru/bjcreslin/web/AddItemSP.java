package ru.bjcreslin.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.bjcreslin.DAO.WatermanItemRepository;
import ru.bjcreslin.Exceptions.WebPerserException;
import ru.bjcreslin.model.DoubleCode;
import ru.bjcreslin.model.StroyparkItemDTO;
import ru.bjcreslin.model.WatermanItemDTO;
import ru.bjcreslin.service.*;

import java.io.IOException;

/**
 * Добавляем элемент СП.
 * так же код элемента Водяного
 * если код есть, то ничего не делаем
 * если кода нет, то добавяляем в таблицу
 */
@Log
@Controller
@AllArgsConstructor
public class AddItemSP {
    ItemSPService itemSPService;
    SPParsingSP spParsingSP;
    WatermanItemService watermanItemService;
    WatermanItemParserService watermanItemParserService;
    WatermanItemRepository watermanItemRepository;

    @GetMapping("/additemsp")
    public String action(Model model) {
        model.addAttribute("item", new DoubleCode());
        return "additemsp";
    }

    /**
     * Метод добавляет itemSP и соответствующий ему Waterman item в базу
     *
     * @param DoubleCode doublecode
     * @param model
     * @return
     */
    @PostMapping("/additemsp")
    public String pidUserSubmit(@ModelAttribute DoubleCode doubleCode, Model model) {
        // model.addAttribute("item", doubleCode);
        try {
            log.severe("Date from page" + doubleCode);
            StroyparkItemDTO stroyparkItemDTO = spParsingSP.getItemByCode(doubleCode.getFirstCode());

            WatermanItemDTO watermanItem = watermanItemService.findByCode(doubleCode.getSecondCode());
            watermanItem = watermanItemParserService.getItemByCode(doubleCode.getSecondCode());

//            if (watermanItem == null) {
//                watermanItem = watermanItemParserService.getItemByCode(doubleCode.getSecondCode());
//            } else {
//                Long time = DateService.getCurrentDate();
//                if ((time - watermanItem.getDate()) > 1000 * 60 * 60 * 24) {
//                    watermanItem = watermanItemParserService.getItemByCode(doubleCode.getSecondCode());
//                }
//            }

//            watermanItem.getStroyparkItemList().add(stroyparkItemDTO);
         //   watermanItemService.save(watermanItem);
            watermanItemRepository.saveAndFlush(watermanItem);
            stroyparkItemDTO.setWatermanItemDTO(watermanItem);

            itemSPService.save(stroyparkItemDTO);


        } catch (WebPerserException e) {
            e.printStackTrace();
        }

        return "redirect:/showallsp";
    }

}
