package ru.bjcreslin.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@MappedSuperclass
@Data
public abstract class Item  {
    @NotNull
    @Id
    @Column(name = "code") //код товара
    private Long code;

    @Column(name = "name") //Наименование
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "address")
    private String address;

    @Column(name = "date")
    private Long date;

    @Column(name="comment")
    private String comment;

    @Column(name = "currency")
    private String currency; // валюта цены -rub или other

}
