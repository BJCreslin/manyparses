package ru.bjcreslin.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Item implements Serializable {

    private Long id;

    private String name;

    private BigDecimal price;

    private BigDecimal priceDiscount;

    private String address;

    private Long code;

}
