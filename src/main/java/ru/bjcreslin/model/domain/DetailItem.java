package ru.bjcreslin.model.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@MappedSuperclass
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

}
