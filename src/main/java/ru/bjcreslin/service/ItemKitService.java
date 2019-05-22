package ru.bjcreslin.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.bjcreslin.model.KitItemDTO;
import ru.bjcreslin.repository.ItemKitRepository;
import ru.bjcreslin.service.parses.ParserKit;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ItemKitService {
    ItemKitRepository repository;

    public void save(KitItemDTO itemDTO) {
        itemDTO.setDate(LocalDateTime.now());
        repository.saveAndFlush(itemDTO);
    }

    public List<KitItemDTO> findAll(Pageable pageable) {

        return repository.findAll(pageable).getContent();
    }

    public List<KitItemDTO> findAll() {
        return repository.findAll();
    }
}
