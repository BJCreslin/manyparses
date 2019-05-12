package ru.bjcreslin.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bjcreslin.DAO.ItemSPRepository;
import ru.bjcreslin.model.Item;
import ru.bjcreslin.model.StroyparkItemDTO;
import ru.bjcreslin.web.Analyze;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * сервисный класс анализа СП
 */
@Service
@AllArgsConstructor
public class AnalyzeSPServiceIMPL implements AnalyzeService {
    ItemSPRepository itemSPRepository;

    /**
     * Ищет все данные, у которых цена Водяного, выше цены Стройпарка
     * @return список элементов Стройпарка
     */
    public List<Item> findAllCheaps() {
        List<Item> stroyparkItemDTOList = new ArrayList<>();
        List<StroyparkItemDTO> stroyparkItemDTOListALL = itemSPRepository.findAll();
        for (StroyparkItemDTO stroyparkItemDTO : stroyparkItemDTOListALL) {
            BigDecimal priceSP = stroyparkItemDTO.getPriceDiscount().divide(BigDecimal.valueOf(stroyparkItemDTO.getMulty()));
            BigDecimal priceWtrmn = stroyparkItemDTO.getWatermanItemDTO().getPrice();
            if (priceSP.compareTo(priceWtrmn) < 0) {
                stroyparkItemDTOList.add(stroyparkItemDTO);
            }
        }

        return stroyparkItemDTOList;
    }


}