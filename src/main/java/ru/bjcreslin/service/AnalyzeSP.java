package ru.bjcreslin.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bjcreslin.DAO.ItemSPRepository;
import ru.bjcreslin.model.StroyparkItemDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AnalyzeSP {
    ItemSPRepository itemSPRepository;


    public List<StroyparkItemDTO> findAllCheaps() {
        List<StroyparkItemDTO> stroyparkItemDTOList = new ArrayList<>();
        List<StroyparkItemDTO> stroyparkItemDTOListALL = itemSPRepository.findAll();
        for (StroyparkItemDTO stroyparkItemDTO : stroyparkItemDTOListALL) {
            BigDecimal priceSP = stroyparkItemDTO.getPriceDiscount().divide(BigDecimal.valueOf(stroyparkItemDTO.getMulty()));
            BigDecimal priceWtrmn = stroyparkItemDTO.getWatermanItemDTO().getPrice();
            if (priceSP.compareTo(priceWtrmn) < 0) {
                stroyparkItemDTOList.add(stroyparkItemDTO);
            }
        }

//        stroyparkItemDTOList = stroyparkItemDTOListALL;
        return stroyparkItemDTOList;
    }


}
