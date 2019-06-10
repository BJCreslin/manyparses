package ru.bjcreslin.service.analyzes;

import lombok.AllArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.stereotype.Service;
import ru.bjcreslin.Exceptions.ErrorCreationTempFile;
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
public class AnalyzeKITServiceIMPL {


    private ItemKitRepository repository;

    /**
     * Ищет все данные, у которых цена Водяного, выше цены Кит
     *
     * @return список элементов Кит
     */
    public List<KitItemDTO> findAllCheaps() {
        List<KitItemDTO> itemDTOList = new ArrayList<>();
        List<KitItemDTO> itemDTOS = repository.findAll();
        for (KitItemDTO item : itemDTOS) {
            BigDecimal price = item.getPriceDiscount().divide(BigDecimal.valueOf(item.getMulty()));
            BigDecimal priceWtrmn = item.getWatermanItemDTO().getPrice();
            if (price.compareTo(priceWtrmn) < 0) {
                itemDTOList.add(item);
            }
        }
        return itemDTOList;
    }

    /**
     * TODO: Доделать функцию сохранения excell файла
     *
     * @param kitItemDTOList список Кит Итемов
     * @return файл ексель
     * @throws ErrorCreationTempFile
     */
    public HSSFWorkbook saveCheaps(List<KitItemDTO> kitItemDTOList)  {
        HSSFWorkbook excellFileWorkbook;

            excellFileWorkbook = new HSSFWorkbook();
        String excellFileSheetName = "Kit";
        HSSFSheet excellFileWorkbookSheet = excellFileWorkbook.createSheet(excellFileSheetName);
        /* Заголовок столбцов*/
        HSSFRow hssfRow = excellFileWorkbookSheet.createRow(0);
        String watrmnCodeColumnNameXlsFile = "Wtrmn code";
        hssfRow.createCell(0).setCellValue(watrmnCodeColumnNameXlsFile);
        String watrmnNameColumnNamelsFile = "Wtrmn name";
        hssfRow.createCell(1).setCellValue(watrmnNameColumnNamelsFile);
        String kitNameColumnNamelsFile = "Kit name";
        hssfRow.createCell(2).setCellValue(kitNameColumnNamelsFile);
        String wtrmnPriceColumnNamelsFile = "Wtrmn price";
        hssfRow.createCell(3).setCellValue(wtrmnPriceColumnNamelsFile);
        String kitPriceColumnNamelsFile = "Kit price";
        hssfRow.createCell(4).setCellValue(kitPriceColumnNamelsFile);
        String groupeColumnNamelsFile = "Groupe";
        hssfRow.createCell(5).setCellValue(groupeColumnNamelsFile);

        /*По всему листу данных заполняем таблицу */
        for (int i = 0; i < kitItemDTOList.size(); i++) {
            KitItemDTO kitItemDTO = kitItemDTOList.get(i);
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
