package ru.bjcreslin.delete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void delete(ItemWatermanDAO item){
        itemWatermanRepository.delete(item);
    }

}
