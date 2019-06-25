package ru.bjcreslin.model.domain;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "waterman")
/**
 * Класс для товаров Waterman
 *
 */
public class WatermanItemDTO extends Item {

    @Column(name = "product_group")
    private String group;  // группа товаров


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StroyparkItemDTO> stroyparkItemList = new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    @Transient
    private List<KitItemDTO> kitItemList = new ArrayList<>();


    public void addKitItem(KitItemDTO kitItemDTO) {
        kitItemList.add(kitItemDTO);
    }

    public void addSPItem(StroyparkItemDTO stroyparkItemDTO) {
        stroyparkItemList.add(stroyparkItemDTO);
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public List<StroyparkItemDTO> getStroyparkItemList() {
        return stroyparkItemList;
    }

    public List<KitItemDTO> getKitItemList() {
        return kitItemList;
    }

    @Override
    public String toString() {
        return "WatermanItemDTO{" +
                "group='" + group + '\'' +
                ", stroyparkItemList=" + stroyparkItemList +
                ", kitItemList=" + kitItemList +
                "} " + super.toString();
    }
}
