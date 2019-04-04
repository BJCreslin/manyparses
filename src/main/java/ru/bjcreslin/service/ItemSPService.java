package ru.bjcreslin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bjcreslin.DAO.ItemSP;
import ru.bjcreslin.DAO.ItemSPDAO;

import java.util.List;

@Service
public class ItemSPService {
    @Autowired
    ItemSPDAO itemSPDAO;

    public void save(ItemSP itemSP) {
        itemSPDAO.save(itemSP);

    }

    public List<ItemSP> findAll() {
        return (List<ItemSP>) itemSPDAO.findAll();
    }


}
