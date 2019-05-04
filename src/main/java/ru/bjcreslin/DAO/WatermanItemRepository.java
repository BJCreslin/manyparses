package ru.bjcreslin.DAO;

import org.springframework.data.repository.CrudRepository;
import ru.bjcreslin.model.WatermanItemDTO;

import java.util.List;

public interface WatermanItemRepository extends CrudRepository<WatermanItemDTO, Long> {
    WatermanItemDTO findByCode(Long code);

    List<WatermanItemDTO> findAll();
}
