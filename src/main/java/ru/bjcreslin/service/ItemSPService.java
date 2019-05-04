package ru.bjcreslin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bjcreslin.model.StroyparkItemDTO;
import ru.bjcreslin.DAO.ItemSPRepository;

import java.util.List;

@Service
public class ItemSPService {
    @Autowired
    ItemSPRepository itemSPRepository;

    public void save(StroyparkItemDTO stroyparkItemDTO) {
        this.itemSPRepository.save(stroyparkItemDTO);
    }

    public List<StroyparkItemDTO> findAll() {
        return (List<StroyparkItemDTO>) itemSPRepository.findAll();
    }

    public StroyparkItemDTO findByCode(Long id) {
        //  itemSPRepository.findById(id).
        return itemSPRepository.findByCode(id).get();
    }

    public StroyparkItemDTO findByAddress(String address) {
        return itemSPRepository.findByAddress(address);
    }

    public void delete(StroyparkItemDTO stroyparkItemDTO) {
        itemSPRepository.delete(stroyparkItemDTO);
    }


}
