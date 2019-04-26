package ru.bjcreslin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bjcreslin.DAO.WatermanItemDAO;
import ru.bjcreslin.DAO.WatermanItemRepository;
import ru.bjcreslin.model.WatermanItem;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
        watermanItemDAO.setDate(new Date().toString());
        watermanItemRepository.save(watermanItemDAO);

    }

    public List<WatermanItem> findAll() {
        List<WatermanItem> watermanItemList;
        List<WatermanItemDAO> watermanItemDAOList = (List<WatermanItemDAO>) watermanItemRepository.findAll();

       watermanItemList= watermanItemDAOList.stream().map(this::DaoToItem).collect(Collectors.toList());
        return watermanItemList;
    }

    private WatermanItem DaoToItem(WatermanItemDAO watermanItemDAO) {
        WatermanItem watermanItem = new WatermanItem();
        watermanItem.setAddress(watermanItemDAO.getAddress());
        watermanItem.setCode(watermanItemDAO.getCode());
        watermanItem.setCurrency(watermanItemDAO.getCurrency());
        watermanItem.setGroupe(watermanItemDAO.getGroup());
        watermanItem.setName(watermanItemDAO.getName());
        watermanItem.setPrice(watermanItemDAO.getPrice());
        return watermanItem;
    }

}