package ru.bjcreslin.service.analyzes;

import lombok.AllArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;
import ru.bjcreslin.model.domain.DetailItem;
import ru.bjcreslin.model.domain.Item;
import ru.bjcreslin.model.domain.KitItemDTO;
import ru.bjcreslin.repository.ItemKitRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * сервисный класс анализа СП
 */
@Service
@AllArgsConstructor
public class AnalyzeKITServiceIMPL implements AnalyzeService {


    private ItemKitRepository repository;

    /**
     * Ищет все данные, у которых цена Водяного, выше цены Кит
     *
     * @return список элементов Кит
     */
    public List<Item> findAllCheaps() {
        List<Item> itemDTOList = new ArrayList<>();
        List<KitItemDTO> itemDTOS = repository.findAll();
        for (KitItemDTO item : itemDTOS) {
            BigDecimal price = item.getDiscountPriceWithoutMulty();
            BigDecimal priceWtrmn = item.getWatermanItemDTO().getPrice();
            if (price.compareTo(priceWtrmn) < 0) {
                itemDTOList.add(item);
            }
        }
        return itemDTOList;
    }

    /**
     * todo: Сделать автоматическую ширину столбца
     *
     * @param kitItemDTOList список Кит Итемов
     * @return файл ексель
     */
    public HSSFWorkbook saveCheaps(List<DetailItem> kitItemDTOList) {
        HSSFWorkbook excellFileWorkbook;

        excellFileWorkbook = new HSSFWorkbook();
        String excellFileSheetName = "Kit";
        HSSFSheet excellFileWorkbookSheet = excellFileWorkbook.createSheet(excellFileSheetName);

        addColumnNamesToExcellFile(excellFileSheetName, excellFileWorkbookSheet);

        HSSFRow hssfRow;
        /*По всему листу данных заполняем таблицу */
        for (int i = 0; i < kitItemDTOList.size(); i++) {
            hssfRow = excellFileWorkbookSheet.createRow(i + 1);
            fillRowInExcellFile(hssfRow, kitItemDTOList.get(i));
        }
        excellFileWorkbookSheet.autoSizeColumn(1);
        return excellFileWorkbook;
    }


}
