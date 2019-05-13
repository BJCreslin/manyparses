package ru.bjcreslin.web;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.bjcreslin.model.DoubleCode;
import ru.bjcreslin.model.Item;

public interface ItemWeb {
    @GetMapping("/additem")
    String addGet(Model model);

    @PostMapping("/additem")
    String addPost(@ModelAttribute DoubleCode doubleCode, Model model);

    @GetMapping("/showall")
    String showAll(Model model);

    @GetMapping("{item}")
    String editGet(@PathVariable Item item, Model model);

    @PostMapping("/edit")
    String editPost(@ModelAttribute("edit_sp") Item item, BindingResult result, Model model);

    @GetMapping("/delete/{item}")
    String delete(@PathVariable("item") Item item);

}
