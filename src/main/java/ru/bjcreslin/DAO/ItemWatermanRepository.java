package ru.bjcreslin.DAO;

import org.springframework.data.repository.CrudRepository;

public interface ItemWatermanRepository extends CrudRepository<ItemWatermanDAO, Long> {
     ItemWatermanDAO findByCode(Long code);
     void save(WatermanItemDAO watermanItemDAO);
}
