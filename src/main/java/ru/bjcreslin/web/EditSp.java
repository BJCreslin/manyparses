package ru.bjcreslin.web;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.bjcreslin.DAO.ItemSPDAO;
import ru.bjcreslin.service.ItemSPService;

@Log
@Controller
@RequestMapping("/itemsp")
public class EditSp {
    @Autowired
    ItemSPService itemSPService;

//    @GetMapping
//    public String ed() {
//
//        System.out.println("iam here");
//        return "index";
//    }


    @GetMapping("{item}")
    public String editItemSP(@PathVariable ItemSPDAO item, Model model) {
        model.addAttribute("edit_sp", item);
        return "edit_sp";
    }

    @PostMapping("edit")
    public String saveSP(@ModelAttribute("edit_sp") ItemSPDAO editItemSPDAO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "edit_sp";
        }
//        ItemSPDAO itemSPDAO = itemSPService.findByAddressSP(editItemSPDAO.getAddressSP());
//        if (itemSPDAO != null) {
//            itemSPDAO.setSale(editItemSPDAO.getSale());
//            itemSPDAO.setCode(editItemSPDAO.getCode());
//            itemSPDAO.setNameSP(editItemSPDAO.getNameSP());
//            itemSPDAO.setPriceDiscountSP(editItemSPDAO.getPriceDiscountSP());
//            itemSPDAO.setPriceSP(editItemSPDAO.getPriceSP());
//            itemSPDAO.setSale(editItemSPDAO.getSale());
//        }
//        {
//            itemSPService.save(editItemSPDAO);
//        }
        itemSPService.save(editItemSPDAO);
        return "redirect:/index";
    }

    @DeleteMapping("{item}")
    public String delete(@PathVariable ItemSPDAO deleteItem) {
        itemSPService.delete(deleteItem);
        return "redirect:/index";
    }

}
