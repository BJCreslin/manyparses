package ru.bjcreslin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bjcreslin.DAO.WatermanItemDAO;
import ru.bjcreslin.DAO.WatermanItemRepository;

@Service
public class WatermanItemService {
    @Autowired
    WatermanItemRepository watermanItemRepository;

    public WatermanItemDAO findById(Long code){
        return watermanItemRepository.findByCode(code);
    }
}
