package ru.bjcreslin.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "waterman")

public class WatermanItemDTO extends Item {
    @NotNull
    @Id
    @Column(name = "id") //код товара
    private Long id;

    @Column(name = "name") //Наименование
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "address")
    private String address;

    @Column(name = "date")
    private Long date;

    @Column(name = "comment")
    private String comment;

    @Column(name = "currency")
    private String currency; // валюта цены -rub или other


    @Column(name = "product_group")
    private String group;  // группа товаров


    @OneToMany(targetEntity = ru.bjcreslin.model.StroyparkItemDTO.class, mappedBy = "waterman",
            cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            orphanRemoval = true)
    private Set<StroyparkItemDTO> stroyparkItemList = new HashSet<>();


}
