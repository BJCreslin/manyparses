package ru.bjcreslin.web;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.bjcreslin.DAO.ItemSPDAO;
import ru.bjcreslin.service.ItemSPService;

@Log
@Controller
public class EditSp {
    @Autowired
    ItemSPService itemSPService;

    @GetMapping("/edit_sp{id}")
    public String editItemSP(@PathVariable("id") Long id, Model model) {
try{        log.info(id.toString());}
catch (Exception ex){
    id=17L;
}
        model.addAttribute("edit_sp",itemSPService.findById(id));
        return "edit_sp";
    }

    @PostMapping("/edit_sp")
    public  String saveSP(@ModelAttribute("edit_sp")ItemSPDAO editItemSPDAO, BindingResult result,Model model){
        if(result.hasErrors()){
            return "edit_sp";
        }
        itemSPService.save(editItemSPDAO);
        return "index";
    }
}
