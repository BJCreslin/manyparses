package ru.bjcreslin.service.analyzes;

import lombok.AllArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.stereotype.Service;
import ru.bjcreslin.Exceptions.ErrorCreationTempFile;
import ru.bjcreslin.model.Item;
import ru.bjcreslin.model.KitItemDTO;
import ru.bjcreslin.repository.ItemKitRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.createTempFile;

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
            BigDecimal price = item.getPriceDiscount().divide(BigDecimal.valueOf(item.getMulty(), 2));
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
     * @throws ErrorCreationTempFile
     */
    public HSSFWorkbook saveCheaps(List<Item> kitItemDTOList) {
        HSSFWorkbook excellFileWorkbook;

        excellFileWorkbook = new HSSFWorkbook();
        String excellFileSheetName = "Kit";
        HSSFSheet excellFileWorkbookSheet = excellFileWorkbook.createSheet(excellFileSheetName);

        addColumnNamesToExcellFile(excellFileSheetName, excellFileWorkbookSheet);

        HSSFRow hssfRow;
        /*По всему листу данных заполняем таблицу */
        for (int i = 0; i < kitItemDTOList.size(); i++) {
            KitItemDTO kitItemDTO = (KitItemDTO) kitItemDTOList.get(i);
            hssfRow = excellFileWorkbookSheet.createRow(i + 1);
            hssfRow.createCell(0).setCellValue(kitItemDTO.getWatermanItemDTO().getCode());
            hssfRow.createCell(1).setCellValue(kitItemDTO.getWatermanItemDTO().getName());
            hssfRow.createCell(2).setCellValue(kitItemDTO.getName());
            hssfRow.createCell(3).setCellValue(kitItemDTO.getWatermanItemDTO().getPrice().toString());
            hssfRow.createCell(4).setCellValue(kitItemDTO.getPriceDiscount().toString());
            hssfRow.createCell(5).setCellValue(kitItemDTO.getWatermanItemDTO().getGroup());
        }

        return excellFileWorkbook;
    }


}
