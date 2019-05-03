package ru.bjcreslin.DAO;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ItemSPRepository extends CrudRepository<ItemStroyparkDAO,Long> {
    Optional<ItemStroyparkDAO> findById(Long id);
    ItemStroyparkDAO findByAddressSP(String address);

    @Override
    void delete(ItemStroyparkDAO itemStroyparkDAO);
}
