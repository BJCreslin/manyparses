package ru.bjcreslin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.bjcreslin.model.domain.WatermanItemDTO;

public interface WatermanItemRepository extends JpaRepository<WatermanItemDTO, Long> {
    WatermanItemDTO findByCode(Long code);

   // List<WatermanItemDTO> findAll();

    Page <WatermanItemDTO> findAll(Pageable pageable);
}
