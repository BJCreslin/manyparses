package ru.bjcreslin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bjcreslin.DAO.WatermanItemDAO;
import ru.bjcreslin.DAO.WatermanItemRepository;
import ru.bjcreslin.model.WatermanItem;

import java.util.Date;

@Service
public class WatermanItemService {
    @Autowired
    WatermanItemRepository watermanItemRepository;

    public WatermanItemDAO findByCode(Long code) {
        return watermanItemRepository.findByCode(code);
    }

    public void save(WatermanItem watermanItem) {
        WatermanItemDAO watermanItemDAO = findByCode(watermanItem.getCode());
        if (watermanItemDAO == null) {
            watermanItemDAO = new WatermanItemDAO();
        }
        watermanItemDAO.setAddress(watermanItem.getAddress());
        watermanItemDAO.setCurrency(watermanItem.getCurrency());
        watermanItemDAO.setGroup(watermanItem.getGroupe());
        watermanItemDAO.setName(watermanItem.getName());
        watermanItemDAO.setPrice(watermanItem.getPrice());
        watermanItemDAO.setDate(new Date());
        watermanItemRepository.save(watermanItemDAO);

    }
}
