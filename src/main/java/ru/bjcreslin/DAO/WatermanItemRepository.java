package ru.bjcreslin.DAO;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WatermanItemRepository extends CrudRepository<WatermanItemDAO, Long> {
    WatermanItemDAO findByCode(Long code);

    List<WatermanItemDAO> findAll();
}
