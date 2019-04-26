package ru.bjcreslin.DAO;

import org.springframework.data.repository.CrudRepository;

public interface WatermanItemRepository extends CrudRepository<WatermanItemDAO, Long> {
    WatermanItemDAO findByCode(Long code);
}
