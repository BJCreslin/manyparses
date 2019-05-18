package ru.bjcreslin.service.analyzes;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bjcreslin.model.KitItemDTO;
import ru.bjcreslin.repository.ItemKitRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * сервисный класс анализа СП
 */
@Service
@AllArgsConstructor
public class AnalyzeKITServiceIMPL  {
    ItemKitRepository repository;

    /**
     * Ищет все данные, у которых цена Водяного, выше цены Кит
     *
     * @return список элементов Кит
     */
    public List<KitItemDTO> findAllCheaps() {
        List<KitItemDTO> itemDTOList = new ArrayList<>();
        List<KitItemDTO> itemDTOS = repository.findAll();
        for (KitItemDTO item : itemDTOS) {
            BigDecimal price = item.getPriceDiscount().divide(BigDecimal.valueOf(item.getMulty()));
            BigDecimal priceWtrmn = item.getWatermanItemDTO().getPrice();
            if (price.compareTo(priceWtrmn) < 0) {
                itemDTOList.add(item);
            }
        }
        return itemDTOList;
    }


}