package ru.bjcreslin.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.bjcreslin.model.StroyparkItemDTO;
import ru.bjcreslin.repository.ItemSPRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ItemSPService {
    ItemSPRepository itemSPRepository;

    public ItemSPService(ItemSPRepository itemSPRepository) {
        this.itemSPRepository = itemSPRepository;
    }

    public List<StroyparkItemDTO> findAll(Pageable pageable) {
        return itemSPRepository.findAll(pageable).getContent();
    }

    public void save(StroyparkItemDTO stroyparkItemDTO) {
        stroyparkItemDTO.setDate(LocalDateTime.now());
        itemSPRepository.save(stroyparkItemDTO);
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
