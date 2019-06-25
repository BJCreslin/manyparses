package ru.bjcreslin.model.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.MathContext;

@Data
@MappedSuperclass
/**
 * Основной класс для сравнения. Основа для DTO классов
 */
public abstract class DetailItem extends Item {
    @Column(name = "multy") //кратность товара
    protected Long multy;

    @Column(name = "price_discount")  //цена со скидкой
    protected BigDecimal priceDiscount;

    @Column(name = "sale")  //В данный момент есть ли распродажа этого товара
    protected Boolean sale;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "waterman_code")
    @EqualsAndHashCode.Exclude
    public WatermanItemDTO watermanItemDTO;  //данные Водяного

    /**
     * дает данные цены с учетом кратности.
     * Если кратность не проставлена, то берет ее равной 1
     */
    public BigDecimal getDiscountPriceWithoutMulty() {
        if ((this.multy == null) || (this.multy.equals(0L))) {
            this.multy = 1L;
        }
        return priceDiscount.divide(BigDecimal.valueOf(this.multy), MathContext.DECIMAL32);

    }

}
