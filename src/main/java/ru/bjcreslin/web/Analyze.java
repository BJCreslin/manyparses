package ru.bjcreslin.web;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bjcreslin.model.Item;
import ru.bjcreslin.model.KitItemDTO;
import ru.bjcreslin.service.analyzes.AnalyzeKITServiceIMPL;
import ru.bjcreslin.service.analyzes.AnalyzeSPServiceIMPL;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
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
        kitItemDTOList = new ArrayList<>();
    }

    @Autowired
    private ServletContext servletContext;

    private AnalyzeSPServiceIMPL analyzeSPServiceIMPL;
    private AnalyzeKITServiceIMPL analyzeKITServiceIMPL;
    private PagedListHolder pagedListHolder;
    private List<Item> stroyparkItemDTOList;
    List<KitItemDTO> kitItemDTOList;

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
        kitItemDTOList = analyzeKITServiceIMPL.findAllCheaps();
        model.addAttribute("cheaplist", kitItemDTOList);
        model.addAttribute("name", "Kit");
        return "cheap_analizy";
    }

    @GetMapping("/kitExcell")
    public void downloadFile1(HttpServletRequest request,
                              HttpServletResponse response) throws IOException {

        response.setContentType("application/xls ; charset=UTF-8");
        response.setHeader("Content-disposition", "attachment; filename=data.xls");
        ServletOutputStream outt = response.getOutputStream();
        outt.write(analyzeKITServiceIMPL.saveCheaps(kitItemDTOList).getBytes());
        outt.flush();
        outt.close();
    }


}
