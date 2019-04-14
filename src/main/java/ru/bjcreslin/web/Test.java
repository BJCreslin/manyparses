package ru.bjcreslin.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
    @GetMapping("/test")
    public String one() {
        return "First method:/test";
    }

    @GetMapping("/test/{id}")
    public String two(@PathVariable Long id) {
        return "Second method :"+id.toString();
    }


}
