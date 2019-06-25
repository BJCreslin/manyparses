package ru.bjcreslin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.bjcreslin.model.domain.KitItemDTO;

public interface ItemKitRepository extends JpaRepository<KitItemDTO, Long> {
    KitItemDTO findByCode(Long code);

    @Override
    void delete(KitItemDTO kitItemDTO);

    Page <KitItemDTO> findAll(Pageable pageable);
}
