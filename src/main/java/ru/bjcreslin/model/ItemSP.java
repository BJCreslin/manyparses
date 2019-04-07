package ru.bjcreslin.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemSP extends Item {
    private BigDecimal priceDiscount;
}
