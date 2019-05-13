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
