package ru.bjcreslin.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bjcreslin.model.WatermanItemDTO;

import java.util.List;

public interface WatermanItemRepository extends JpaRepository<WatermanItemDTO, Long> {
    WatermanItemDTO findByCode(Long code);

    List<WatermanItemDTO> findAll();
}
