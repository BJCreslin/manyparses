package ru.bjcreslin.DAO;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "item_sp")
@Data
public class ItemSPDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "name_sp")
    private String nameSP;

    @Column(name = "price")
    private BigDecimal priceSP;

    @Column(name = "price_discount_sp")
    private BigDecimal priceDiscountSP;

    @Column(name = "address_sp")
    private String addressSP;

    @Column(name = "code")
    private Long code;

    @Column(name = "sale")
    private Boolean sale;

}
