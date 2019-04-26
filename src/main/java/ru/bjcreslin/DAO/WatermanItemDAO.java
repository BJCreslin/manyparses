package ru.bjcreslin.DAO;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

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
    private Long code; //код товара
    @Column(name = "price")
    private BigDecimal price; //прайсовая цена
    @Column(name = "group")
    private String group;  // группа товаров
    @Column(name = "currency")
    private String currency; // валюта цены -rub или other
    @Column(name = "date")
    private String date;
}
