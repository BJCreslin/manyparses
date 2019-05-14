package ru.bjcreslin.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

/**
 * первая страница
 */
@Controller
public class Index {

    @GetMapping({"","/index"})
    public String getIndexPage(HttpSession session){
        session.setAttribute("SP_name",SP.ITEM_NAME);
        //model.addAttribute(,SP.ITEM_NAME);
        return "index";
    }
}
