package ru.bjcreslin.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bjcreslin.model.domain.StroyparkItemDTO;
import ru.bjcreslin.repository.ItemSPRepository;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Service
public class ItemSPService {
    private ItemSPRepository itemSPRepository;

    public ItemSPService(ItemSPRepository itemSPRepository) {
        this.itemSPRepository = itemSPRepository;
    }

    public List<StroyparkItemDTO> findAll(Pageable pageable) {
        return itemSPRepository.findAll(pageable).getContent();
    }

    public void save(StroyparkItemDTO stroyparkItemDTO) {
        stroyparkItemDTO.setDate(LocalDateTime.now());
        itemSPRepository.saveAndFlush(stroyparkItemDTO);
    }

    public List<StroyparkItemDTO> findAll() {
        return itemSPRepository.findAll();
    }

    public StroyparkItemDTO findByCode(Long id) {
        return itemSPRepository.findByCode(id);
    }

    public StroyparkItemDTO findByAddress(String address) {
        return itemSPRepository.findByAddress(address);
    }

    public void delete(StroyparkItemDTO stroyparkItemDTO) {
        itemSPRepository.delete(stroyparkItemDTO);
    }


}
