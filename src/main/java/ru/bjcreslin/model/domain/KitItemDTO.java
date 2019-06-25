package ru.bjcreslin.model.domain;

import lombok.Data;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.Entity;
import javax.persistence.Table;


@Transactional
@Data
@Entity
@Table(name = "kit")
/**
 * Класс для товаров Kit
 *
 */
public class KitItemDTO extends DetailItem {

    @Override
    public String toString() {
        return "KitItemDTO{" +
                "multy=" + multy +
                ", priceDiscount=" + priceDiscount +
                ", sale=" + sale +
                ", watermanItemDTO=" + watermanItemDTO +
                "} " + super.toString();
    }
}
