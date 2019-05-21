package ru.bjcreslin.service;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.bjcreslin.model.WatermanItemDTO;
import ru.bjcreslin.repository.WatermanItemRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Log
public class ItemWtrmnService {
    WatermanItemRepository watermanItemRepository;

    public WatermanItemDTO findByCode(Long code) {
        return watermanItemRepository.findByCode(code);
    }

    public void save(WatermanItemDTO watermanItem) {
        watermanItem.setDate(LocalDateTime.now());
        watermanItemRepository.saveAndFlush(watermanItem);

    }

    public List<WatermanItemDTO> findAll() {
        return watermanItemRepository.findAll();
    }

    public List<WatermanItemDTO> findAll(Pageable pageable) {
        return watermanItemRepository.findAll(pageable).getContent();

    }

    public void delete(WatermanItemDTO item) {
        watermanItemRepository.delete(item);
    }


}
