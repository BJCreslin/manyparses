package ru.bjcreslin.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
public abstract class Item {

    @NotNull
    @Id
    @Column(name = "code") //код товара
    private Long code;

    @Column(name = "name") //Наименование
    private String name;

    @Column(name = "price")  //цена
    private BigDecimal price;

    @Column(name = "address") //Вебадресс
    private String address;

    @Column(name = "date") //дата
    private LocalDateTime date;

    @Column(name = "comment") //комментарий
    private String comment;

    @Column(name = "currency")
    private String currency; // валюта цены -rub или other

}
