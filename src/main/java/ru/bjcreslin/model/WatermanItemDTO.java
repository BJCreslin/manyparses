package ru.bjcreslin.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "waterman")
public class WatermanItemDTO extends Item {


    @Column(name = "product_group")
    private String group;  // группа товаров

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    private List<StroyparkItemDTO> stroyparkItemList = new ArrayList<>();



    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    private List<KitItemDTO> kitItemList = new ArrayList<>();

    public void addKitItem(KitItemDTO kitItemDTO) {
        kitItemList.add(kitItemDTO);
    }

}
