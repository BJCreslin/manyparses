package ru.bjcreslin.delete;

import org.springframework.data.repository.CrudRepository;

public interface ItemWatermanRepository extends CrudRepository<ItemWatermanDAO, Long> {
     ItemWatermanDAO findByCode(Long code);

}
