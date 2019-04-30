package ru.bjcreslin.service;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import ru.bjcreslin.DAO.WatermanItemDAO;
import ru.bjcreslin.DAO.WatermanItemRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Log
public class WatermanItemService {
    WatermanItemRepository watermanItemRepository;

    public WatermanItemDAO findByCode(Long code) {
        return watermanItemRepository.findByCode(code);
    }

    public void save(WatermanItemDAO watermanItem) {
        Long code = watermanItem.getCode();
        WatermanItemDAO watermanItemDAO = findByCode(code);
        if (watermanItemDAO == null) {
            watermanItemDAO = new WatermanItemDAO();
            watermanItemDAO.setCode(code);
        }

        watermanItemRepository.save(watermanItemDAO);

    }

    public List<WatermanItemDAO> findAll() {
        List<WatermanItemDAO> watermanItemDAOList = watermanItemRepository.findAll();
        log.info("WatermanItem findAll:" + watermanItemDAOList.size());
        return watermanItemDAOList;
    }

    public void delete(WatermanItemDAO item) {
        watermanItemRepository.delete(item);
    }


}
