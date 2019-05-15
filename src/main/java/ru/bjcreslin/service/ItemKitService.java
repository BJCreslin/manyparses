package ru.bjcreslin.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bjcreslin.model.KitItemDTO;
import ru.bjcreslin.repository.ItemKitRepository;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ItemKitService {
    ItemKitRepository repository;

    public void save(KitItemDTO itemDTO) {
        itemDTO.setDate(LocalDateTime.now());
        repository.save(itemDTO);
    }

}
