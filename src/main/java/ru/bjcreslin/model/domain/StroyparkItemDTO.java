package ru.bjcreslin.model.domain;

import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.Table;

@Transactional
@Entity
@Table(name = "stroypark")
@Data
public class StroyparkItemDTO extends DetailItem {

    @Override
    public String toString() {
        return "StroyparkItemDTO{" +
                "multy=" + multy +
                ", priceDiscount=" + priceDiscount +
                ", sale=" + sale +
                ", watermanItemDTO=" + watermanItemDTO +
                "} " + super.toString();
    }
}
