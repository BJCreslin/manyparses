package ru.bjcreslin.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * первая страница
 */
@Controller
public class Index {

    @GetMapping({"","/index"})
    public String getIndexPage(){
        return "index";
    }
}
