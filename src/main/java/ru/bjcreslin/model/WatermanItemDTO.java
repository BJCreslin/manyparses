package ru.bjcreslin.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "waterman")
public class WatermanItemDTO extends Item {
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


    @Column(name = "product_group")
    private String group;  // группа товаров


   @OneToMany ( cascade = CascadeType.ALL, fetch = FetchType.EAGER,
    orphanRemoval = true)
   @EqualsAndHashCode.Exclude
    private List<StroyparkItemDTO> stroyparkItemList = new ArrayList<>();


}
