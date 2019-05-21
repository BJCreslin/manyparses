package ru.bjcreslin.web;

import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bjcreslin.Exceptions.WebParserException;
import ru.bjcreslin.model.KitItemDTO;
import ru.bjcreslin.model.StroyparkItemDTO;
import ru.bjcreslin.model.TrippleCode;
import ru.bjcreslin.model.WatermanItemDTO;
import ru.bjcreslin.service.ItemKitService;
import ru.bjcreslin.service.ItemSPService;
import ru.bjcreslin.service.ItemWtrmnService;
import ru.bjcreslin.service.parses.ParserKit;
import ru.bjcreslin.service.parses.ParserSP;
import ru.bjcreslin.service.parses.ParserWaterman;

@Controller
@RequestMapping("/all")
@Data
public class AddAll {
    ParserKit parserKit;
    ParserWaterman parserWaterman;
    ParserSP parserSP;
    ItemKitService kitService;
    ItemSPService spService;
    ItemWtrmnService wtrmnService;

    public AddAll(ParserKit parserKit, ParserWaterman parserWaterman, ParserSP parserSP,
                  ItemKitService kitService, ItemSPService spService, ItemWtrmnService wtrmnService) {
        this.parserKit = parserKit;
        this.parserWaterman = parserWaterman;
        this.parserSP = parserSP;
        this.kitService = kitService;
        this.spService = spService;
        this.wtrmnService = wtrmnService;
    }

    @GetMapping("/add")
    public String getAddALL(Model model) {
        model.addAttribute("name", "all");
        model.addAttribute("item", new TrippleCode());
        return "addall";

    }

    @PostMapping("/add")
    public String putAddAll(@ModelAttribute TrippleCode trippleCode) {
        KitItemDTO kitItemDTO = null;
        StroyparkItemDTO stroyparkItemDTO = null;
        WatermanItemDTO watermanItemDTO = parserWaterman.getItemByCode(trippleCode.getWatermanCode());
        try {
            kitItemDTO = parserKit.getItemByCode(trippleCode.getKitCode());
        } catch (WebParserException |NullPointerException e) {
            e.printStackTrace();
            kitItemDTO=null;
        }
        try {
            stroyparkItemDTO = parserSP.getItemByCode(trippleCode.getSpCode());
        } catch (WebParserException |NullPointerException e) {
            e.printStackTrace();
            stroyparkItemDTO=null;
        }
        wtrmnService.save(watermanItemDTO);
        if (kitItemDTO != null) {
            kitItemDTO.setWatermanItemDTO(watermanItemDTO);
            kitService.save(kitItemDTO);
        }

        if (stroyparkItemDTO != null) {
            stroyparkItemDTO.setWatermanItemDTO(watermanItemDTO);
            spService.save(stroyparkItemDTO);
        }

        return "redirect:/index";
    }
}
