package ru.bjcreslin.web;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.bjcreslin.model.DoubleCode;
import ru.bjcreslin.model.domain.DetailItem;
import ru.bjcreslin.model.domain.Item;
import ru.bjcreslin.service.DateService;

public interface ItemWeb<t extends Item> {
    int maxElementsOnScreen = 10;

    @GetMapping("/additem")
    String addGet(Model model);

    @PostMapping("/additem")
    String addPost(@ModelAttribute DoubleCode doubleCode, Model model);

    @GetMapping("/showall")
    String showAll(Model model);

    @GetMapping("{item}")
    String editGet(@PathVariable t item, Model model);

    @PostMapping("/edit")
    String editPost(@ModelAttribute("edit_sp") t item, BindingResult result, Model model);

    @GetMapping("/delete/{item}")
    String delete(@PathVariable("item") t item);

    /**
     * Переопределяет поля между двумя объеками данных
     * @param temp DetailItem объект донор
     * @param current DetailItem объект плучатель
     */
    default void fromDTOTempToDTO(DetailItem temp, DetailItem current) {
        current.setPrice(temp.getPrice());
        current.setPriceDiscount(temp.getPriceDiscount());
        current.setSale(temp.getSale());
        current.setMulty(temp.getMulty());
        current.setCurrency(temp.getCurrency());
        current.setAddress(temp.getAddress());
        current.setName(temp.getName());
        current.setDate(DateService.getCurrentDate());
    }

}
