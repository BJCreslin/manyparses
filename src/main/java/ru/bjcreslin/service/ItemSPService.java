package ru.bjcreslin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bjcreslin.DAO.ItemSPDAO;
import ru.bjcreslin.DAO.ItemSPRepository;

import java.util.List;

@Service
public class ItemSPService {
    @Autowired
    ItemSPRepository itemSPDAO;

    public void save(ItemSPDAO itemSPDAO) {
        this.itemSPDAO.save(itemSPDAO);

    }

    public List<ItemSPDAO> findAll() {
        return (List<ItemSPDAO>) itemSPDAO.findAll();
    }


}
