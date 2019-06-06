package ru.bjcreslin.model;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "waterman")
public class WatermanItemDTO extends Item {


    @Column(name = "product_group")
    private String group;  // группа товаров


    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
//    @EqualsAndHashCode.Exclude
    private List<StroyparkItemDTO> stroyparkItemList = new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    @Transient
    private List<KitItemDTO> kitItemList = new ArrayList<>();


    public void addKitItem(KitItemDTO kitItemDTO) {
        kitItemList.add(kitItemDTO);
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

    public void setStroyparkItemList(List<StroyparkItemDTO> stroyparkItemList) {
        this.stroyparkItemList = stroyparkItemList;
    }

    public List<KitItemDTO> getKitItemList() {
        return kitItemList;
    }

    public void setKitItemList(List<KitItemDTO> kitItemList) {
        this.kitItemList = kitItemList;
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
