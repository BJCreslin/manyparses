package ru.bjcreslin.DAO;

import org.springframework.data.repository.CrudRepository;
import ru.bjcreslin.model.StroyparkItemDTO;

import java.util.Optional;

public interface ItemSPRepository extends CrudRepository<StroyparkItemDTO,Long> {
    Optional<StroyparkItemDTO> findByCode(Long id);
    StroyparkItemDTO findByAddress(String address);

    @Override
    void delete(StroyparkItemDTO stroyparkItemDTO);
}
