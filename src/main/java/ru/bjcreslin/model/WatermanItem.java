package ru.bjcreslin.model;

import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class WatermanItem {
    private String name; //Наименование
    private String address;  //цифровой адресс, который сайт добавляет к http://www.waterman-t.ru/products/, что бы получить старницу продукта
    @NotNull
    @Id
    private Long code; //код товара
    private BigDecimal price; //прайсовая цена
    private String groupe;  // группа товаров
    private String currency; // валюта цены -rub или other
}
