package ru.bjcreslin.service;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import ru.bjcreslin.model.WatermanItemDTO;
import ru.bjcreslin.repository.WatermanItemRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Log
public class WatermanItemService {
    WatermanItemRepository watermanItemRepository;

    public WatermanItemDTO findByCode(Long code) {
        return watermanItemRepository.findByCode(code);
    }

    public void save(WatermanItemDTO watermanItem) {
        Long code = watermanItem.getCode();
        WatermanItemDTO watermanItemDTO = findByCode(code);
        if (watermanItemDTO == null) {
            watermanItemDTO = new WatermanItemDTO();
            watermanItemDTO.setCode(code);
        }

        watermanItemRepository.save(watermanItemDTO);

    }

    public List<WatermanItemDTO> findAll() {
        List<WatermanItemDTO> watermanItemDTOList = watermanItemRepository.findAll();
        log.info("WatermanItem findAll:" + watermanItemDTOList.size());
        return watermanItemDTOList;
    }

    public void delete(WatermanItemDTO item) {
        watermanItemRepository.delete(item);
    }


}
