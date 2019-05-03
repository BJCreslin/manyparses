package ru.bjcreslin.DAO;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "waterman_item")
public class WatermanItemDAO {
    @Id
    @Column(name = "code")
    private Long code; //код товара
    @Column(name = "name")
    private String name; //Наименование
    @Column(name = "address")
    private String address;  //цифровой адресс, который сайт добавляет к http://www.waterman-t.ru/products/, что бы получить старницу продукта
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "product_group")
    private String group;  // группа товаров
    @Column(name = "currency")
    private String currency; // валюта цены -rub или other

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "waterman_item",fetch = FetchType.LAZY)
    private Set<ItemStroyparkDAO> contacts = new HashSet<>();

    @Column(name = "date")
    private String date;

}
