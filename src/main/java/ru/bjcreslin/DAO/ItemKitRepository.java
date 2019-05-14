package ru.bjcreslin.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bjcreslin.model.KitItemDTO;

public interface ItemKitRepository extends JpaRepository<KitItemDTO, Long> {
    KitItemDTO findByCode(Long code);

    @Override
    void delete(KitItemDTO kitItemDTO);
}
