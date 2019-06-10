package ru.bjcreslin.web;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.bjcreslin.Exceptions.ErrorCreationTempFile;
import ru.bjcreslin.model.Item;
import ru.bjcreslin.model.KitItemDTO;
import ru.bjcreslin.service.analyzes.AnalyzeKITServiceIMPL;
import ru.bjcreslin.service.analyzes.AnalyzeSPServiceIMPL;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileInputStream;
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
    private static final String DEFAULT_FILE_NAME = "wtrmn-kit.zip";

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
    public ResponseEntity<InputStreamResource> downloadFile1(
            @RequestParam(defaultValue = DEFAULT_FILE_NAME) String fileName) throws IOException {

        MediaType mediaType = getMediaTypeForFileName(this.servletContext, fileName);
        System.out.println("fileName: " + fileName);
        System.out.println("mediaType: " + mediaType);
        FileInputStream file;
        try {

            file = new FileInputStream(analyzeKITServiceIMPL.saveCheaps(kitItemDTOList).getBytes());

            InputStreamResource resource = new InputStreamResource(file);

            return ResponseEntity.ok()
                    // Content-Disposition
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + DEFAULT_FILE_NAME)
                    // Content-Type
                    .contentType(mediaType)
                    // Contet-Length
                    .contentLength(file.available()) //
                    .body(resource);
        } catch (ErrorCreationTempFile errorCreationTempFile) {
            errorCreationTempFile.printStackTrace();
        }
        return null;
    }

    private MediaType getMediaTypeForFileName(ServletContext servletContext, String fileName) {
        // application/pdf
        // application/xml
        // image/gif, ...
        String mineType = servletContext.getMimeType(fileName);
        try {
            MediaType mediaType = MediaType.parseMediaType(mineType);
            return mediaType;
        } catch (Exception e) {
            return MediaType.APPLICATION_OCTET_STREAM;
        }
    }

}
