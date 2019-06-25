package ru.bjcreslin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.bjcreslin.model.domain.StroyparkItemDTO;

public interface ItemSPRepository extends JpaRepository<StroyparkItemDTO,Long> {
    StroyparkItemDTO findByCode(Long id);
    StroyparkItemDTO findByAddress(String address);

    Page<StroyparkItemDTO> findAll(Pageable pageable);

    @Override
    void delete(StroyparkItemDTO stroyparkItemDTO);
}
