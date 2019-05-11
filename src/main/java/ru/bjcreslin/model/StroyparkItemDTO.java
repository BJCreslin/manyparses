package ru.bjcreslin.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "stroypark")
@Data
    public class StroyparkItemDTO extends Item {

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


    @Column(name = "multy")
    private Long multy;

    @Column(name = "price_discount")
    private BigDecimal priceDiscount;


    @Column(name = "sale")
    private Boolean sale;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = WatermanItemDTO.class)
    @JoinColumn(name = "waterman_code")
    @EqualsAndHashCode.Exclude
    private WatermanItemDTO watermanItemDTO;

    @Override
    public String toString() {
        return "StroyparkItemDTO{" +
                "multy=" + multy +
                ", priceDiscount=" + priceDiscount +
                ", sale=" + sale +
                ", watermanItemDTO=" + watermanItemDTO +
                "} " + super.toString();
    }
}
