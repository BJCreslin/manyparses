package ru.bjcreslin.DAO;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ItemSPRepository extends CrudRepository<ItemSPDAO,Long> {
    Optional<ItemSPDAO> findById(Long id);
    ItemSPDAO findByAddressSP(String address);

    @Override
    void delete(ItemSPDAO itemSPDAO);
}
