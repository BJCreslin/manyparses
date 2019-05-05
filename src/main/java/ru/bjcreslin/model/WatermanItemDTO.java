package ru.bjcreslin.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "waterman_item")

public class WatermanItemDTO extends Item {

    @Column(name = "product_group")
    private String group;  // группа товаров


    @OneToMany(cascade = CascadeType.ALL)
    private List<StroyparkItemDTO> stroyparkItemList = new ArrayList<>();


}
