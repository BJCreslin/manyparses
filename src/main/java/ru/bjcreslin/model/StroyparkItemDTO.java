package ru.bjcreslin.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "stroypark")
@Data
    public class StroyparkItemDTO extends Item {

    @Column(name = "multy")
    private Long multy;

    @Column(name = "price_discount")
    private BigDecimal priceDiscount;

    @Column(name = "sale")
    private Boolean sale;

    @ManyToOne
    @JoinColumn(name = "waterman_code")
//    @Access(AccessType.PROPERTY)
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
