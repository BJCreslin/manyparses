package ru.bjcreslin.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;


@Data
@Entity
@Table(name = "kit")
public class KitItemDTO extends Item {

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
        return "KitItemDTO{" +
                "multy=" + multy +
                ", priceDiscount=" + priceDiscount +
                ", sale=" + sale +
                ", watermanItemDTO=" + watermanItemDTO +
                "} " + super.toString();
    }
}