package ru.bjcreslin.DAO;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "stroypark_item")
@Data
public class ItemStroyparkDAO {
    @NotNull
    @Id
    @Column(name = "code")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "multy")
    private Long multy;

    @Column(name = "price_discount")
    private BigDecimal priceDiscount;

    @Column(name = "address")
    private String address;

    @ManyToOne
    @JoinColumn(name = "waterman_item_id", referencedColumnName = "id")
    private WatermanItemDAO watermanItemDAO;

    @Column(name = "sale")
    private Boolean sale;

    @Column(name = "date")
    private String date;

}
