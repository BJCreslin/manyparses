package ru.bjcreslin.DAO;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "waterman_item")
public class WatermanItemDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name; //Наименование
    @Column(name = "address")
    private String address;  //цифровой адресс, который сайт добавляет к http://www.waterman-t.ru/products/, что бы получить старницу продукта
    @Column(name = "code")
    @NotNull
    private Long code; //код товара
    @Column(name = "price")
    private BigDecimal price; //прайсовая цена
    @Column(name = "groupitem")
    private String group;  // группа товаров
    @Column(name = "currency")
    private String currency; // валюта цены -rub или other
    @Column(name = "date")
    private String date;
}
