package ru.bjcreslin.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EditSp {
    @GetMapping("edit_sp")
    public String editItemSP(Model model) {
        return "edit_sp";
    }
}
