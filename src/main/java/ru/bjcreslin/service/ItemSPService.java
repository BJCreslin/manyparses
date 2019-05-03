package ru.bjcreslin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bjcreslin.DAO.ItemStroyparkDAO;
import ru.bjcreslin.DAO.ItemSPRepository;

import java.util.List;

@Service
public class ItemSPService {
    @Autowired
    ItemSPRepository itemSPRepository;

    public void save(ItemStroyparkDAO itemStroyparkDAO) {
        this.itemSPRepository.save(itemStroyparkDAO);
    }

    public List<ItemStroyparkDAO> findAll() {
        return (List<ItemStroyparkDAO>) itemSPRepository.findAll();
    }

    public ItemStroyparkDAO findById(Long id) {
        //  itemSPRepository.findById(id).
        return itemSPRepository.findById(id).get();
    }

    public ItemStroyparkDAO findByAddressSP(String address) {
        return itemSPRepository.findByAddressSP(address);
    }

    public void delete(ItemStroyparkDAO itemStroyparkDAO) {
        itemSPRepository.delete(itemStroyparkDAO);
    }


}
