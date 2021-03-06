package ru.bjcreslin.web;

import lombok.extern.java.Log;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bjcreslin.model.domain.DetailItem;
import ru.bjcreslin.service.analyzes.AnalyzeKITServiceIMPL;
import ru.bjcreslin.service.analyzes.AnalyzeSPServiceIMPL;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Log
@Controller
@RequestMapping("/analize")

public class Analyze {
    public Analyze(AnalyzeSPServiceIMPL analyzeSPServiceIMPL, AnalyzeKITServiceIMPL analyzeKITServiceIMPL) {
        this.analyzeSPServiceIMPL = analyzeSPServiceIMPL;
        this.analyzeKITServiceIMPL = analyzeKITServiceIMPL;
        this.kitItemDTOList = new ArrayList<>();
    }


    private AnalyzeSPServiceIMPL analyzeSPServiceIMPL;
    private AnalyzeKITServiceIMPL analyzeKITServiceIMPL;
    private PagedListHolder pagedListHolder;
    private List<DetailItem> stroyparkItemDTOList = new ArrayList<>();
    private List<DetailItem> kitItemDTOList;

    /* *************************************************************************************
     *                                   SP section                                         *
     ****************************************************************************************/

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

    @GetMapping("/SPExcell")
    public void downloadFileExcellSP(HttpServletResponse response) {
        response.setContentType("application/vnd.ms-excel; charset=UTF-8");
        response.setHeader("Content-disposition", "attachment; filename=data.xls");
        try (ServletOutputStream outt = response.getOutputStream()) {
            outt.write(analyzeSPServiceIMPL.saveCheaps(stroyparkItemDTOList).getBytes());
            outt.flush();
        } catch (IOException e) {
            log.severe("Failed to save Kit cheap excell file");
        }
    }

    /* *************************************************************************************
     *                                   Kit section                                         *
     ****************************************************************************************/


    @GetMapping("/cheap_kit")
    public String cheapKIT(Model model) {
        kitItemDTOList = analyzeKITServiceIMPL.findAllCheaps();
        pagedListHolder = new PagedListHolder(kitItemDTOList);
        pagedListHolder.setPageSize(10);
        pagedListHolder.setPage(0);
        model.addAttribute("cheaplist", pagedListHolder.getPageList());
        model.addAttribute("name", "Kit");
        return "cheap_analizy";
    }


    @GetMapping("/kitExcell")
    public void downloadFileExcellKit(HttpServletResponse response) {
        response.setContentType("application/vnd.ms-excel; charset=UTF-8");
        response.setHeader("Content-disposition", "attachment; filename=data.xls");
        try (ServletOutputStream outt = response.getOutputStream()) {
            outt.write(analyzeSPServiceIMPL.saveCheaps(stroyparkItemDTOList).getBytes());
            outt.flush();
        } catch (IOException e) {
            log.severe("Failed to save Kit cheap excell file");
        }
    }

    @GetMapping("/Kit/next")
    public String cheapKitNext(Model model) {
        pagedListHolder.nextPage();
        model.addAttribute("cheaplist", pagedListHolder.getPageList());
        model.addAttribute("name", "Kit");
        return "cheap_analizy";
    }

    @GetMapping("/Kit/first")
    public String cheapKitFirst(Model model) {
        pagedListHolder.setPage(0);
        model.addAttribute("cheaplist", pagedListHolder.getPageList());
        model.addAttribute("name", "Kit");
        return "cheap_analizy";
    }

    @GetMapping("/Kit/prev")
    public String cheapKitPrev(Model model) {
        pagedListHolder.previousPage();
        model.addAttribute("cheaplist", pagedListHolder.getPageList());
        model.addAttribute("name", "Kit");
        return "cheap_analizy";
    }


}
