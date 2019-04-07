package ru.bjcreslin.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public abstract class Item implements Serializable {


    private String name;

    private BigDecimal price;

    private String address;

    private Long code;

}
