package ru.bjcreslin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bjcreslin.DAO.ItemSPDAO;
import ru.bjcreslin.DAO.ItemSPRepository;

import java.util.List;

@Service
public class ItemSPService {
    @Autowired
    ItemSPRepository itemSPRepository;

    public void save(ItemSPDAO itemSPDAO) {
        this.itemSPRepository.save(itemSPDAO);
    }

    public List<ItemSPDAO> findAll() {
        return (List<ItemSPDAO>) itemSPRepository.findAll();
    }

    public ItemSPDAO findById(Long id) {
        //  itemSPRepository.findById(id).
        return itemSPRepository.findById(id).get();
    }

    public ItemSPDAO findByAddressSP(String address) {
        return itemSPRepository.findByAddressSP(address);
    }

    public void delete(ItemSPDAO itemSPDAO) {
        itemSPRepository.delete(itemSPDAO);
    }


}
