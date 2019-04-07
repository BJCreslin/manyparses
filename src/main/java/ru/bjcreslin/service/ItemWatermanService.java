package ru.bjcreslin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bjcreslin.DAO.ItemWatermanDAO;
import ru.bjcreslin.DAO.ItemWatermanRepository;

import java.util.List;

@Service
public class ItemWatermanService {
    @Autowired
    ItemWatermanRepository itemWatermanRepository;

    public List<ItemWatermanDAO> findAll() {
        return (List<ItemWatermanDAO>) itemWatermanRepository.findAll();
    }

    public void save(ItemWatermanDAO itemWatermanDAO) {
        itemWatermanRepository.save(itemWatermanDAO);
    }

    public ItemWatermanDAO findByCode(Long code) {
        return itemWatermanRepository.findByCode(code);
    }

}
