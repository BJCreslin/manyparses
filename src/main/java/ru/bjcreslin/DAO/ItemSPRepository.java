package ru.bjcreslin.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bjcreslin.model.StroyparkItemDTO;

import java.util.Optional;

public interface ItemSPRepository extends JpaRepository<StroyparkItemDTO,Long> {
    Optional<StroyparkItemDTO> findByCode(Long id);
    StroyparkItemDTO findByAddress(String address);

    @Override
    void delete(StroyparkItemDTO stroyparkItemDTO);
}
