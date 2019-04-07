package ru.bjcreslin.DAO;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name="item_waterman")
public class ItemWatermanDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id")
    private Long id;

    @Column(name="code")
    private Long code;

    @Column(name="name")
    private String name;

    @Column(name="price")
    private BigDecimal price;

    @Column(name="address")
    private String address;

    @Column(name="group")
    private String group;
}
