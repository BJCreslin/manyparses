package ru.bjcreslin.service;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import ru.bjcreslin.DAO.WatermanItemDAO;
import ru.bjcreslin.DAO.WatermanItemRepository;
import ru.bjcreslin.model.WatermanItem;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Log
public class WatermanItemService {
    WatermanItemRepository watermanItemRepository;

    public WatermanItemDAO findByCode(Long code) {
        return watermanItemRepository.findByCode(code);
    }

    public void save(WatermanItem watermanItem) {
        Long code = watermanItem.getCode();
        WatermanItemDAO watermanItemDAO = findByCode(code);
        if (watermanItemDAO == null) {
            watermanItemDAO = new WatermanItemDAO();
            watermanItemDAO.setCode(code);
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
        log.info("WatermanItem findAll:" + watermanItemDAOList.size());
        watermanItemList = watermanItemDAOList.stream().map(this::DaoToItem).collect(Collectors.toList());
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
