package ru.bjcreslin.web;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bjcreslin.model.Item;
import ru.bjcreslin.model.KitItemDTO;
import ru.bjcreslin.service.analyzes.AnalyzeKITServiceIMPL;
import ru.bjcreslin.service.analyzes.AnalyzeSPServiceIMPL;

import java.util.List;

@Log
@Controller
@RequestMapping("/analize")
/**
 * todo сделать анализ
 * 1. по цене
 */
public class Analyze {
    public Analyze(AnalyzeSPServiceIMPL analyzeSPServiceIMPL, AnalyzeKITServiceIMPL analyzeKITServiceIMPL) {
        this.analyzeSPServiceIMPL = analyzeSPServiceIMPL;
        this.analyzeKITServiceIMPL = analyzeKITServiceIMPL;
    }

    private AnalyzeSPServiceIMPL analyzeSPServiceIMPL;
    private AnalyzeKITServiceIMPL analyzeKITServiceIMPL;
    private PagedListHolder pagedListHolder;
    private List<Item> stroyparkItemDTOList;

    @GetMapping("/cheap_sp")
    public String cheapSP(Model model) {
        stroyparkItemDTOList = analyzeSPServiceIMPL.findAllCheaps();
        pagedListHolder = new PagedListHolder(stroyparkItemDTOList);
        pagedListHolder.setPageSize(10);
        pagedListHolder.setPage(0);
        model.addAttribute("cheaplist", pagedListHolder.getPageList());
        model.addAttribute("name", "spcheap");
        return "cheap_analizy";
    }

    @GetMapping("/spcheap/next")
    public String cheapSPnext(Model model) {
        pagedListHolder.nextPage();
        model.addAttribute("cheaplist", pagedListHolder.getPageList());
        model.addAttribute("name", "spcheap");
        return "cheap_analizy";
    }

    @GetMapping("/spcheap/first")
    public String cheapSPfirst(Model model) {
        pagedListHolder.setPage(0);
        model.addAttribute("cheaplist", pagedListHolder.getPageList());
        model.addAttribute("name", "spcheap");
        return "cheap_analizy";
    }

    @GetMapping("/spcheap/prev")
    public String cheapSPPrev(Model model) {
        pagedListHolder.previousPage();
        model.addAttribute("cheaplist", pagedListHolder.getPageList());
        model.addAttribute("name", "spcheap");
        return "cheap_analizy";
    }


    @GetMapping("/cheap_kit")
    public String cheapKIT(Model model) {
        List<KitItemDTO> itemList = analyzeKITServiceIMPL.findAllCheaps();
        model.addAttribute("cheaplist", itemList);
        model.addAttribute("name", "Kit");
        return "cheap_analizy";
    }


}
