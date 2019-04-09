package ru.bjcreslin.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Index {
    @GetMapping({"/index", "/"})
    public String getIndexFile() {
        return "index";
    }


}
