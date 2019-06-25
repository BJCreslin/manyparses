package ru.bjcreslin.service.analyzes;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import ru.bjcreslin.model.domain.DetailItem;
import ru.bjcreslin.model.domain.Item;
import ru.bjcreslin.model.domain.KitItemDTO;

import java.util.List;

/**
 * Что и как анализируем
 */
public interface AnalyzeService {
    /**
     * Находит все элементы, в котрых цена Waterman дороже
     *
     * @return List Элекментов Item
     */
    List<Item> findAllCheaps();


    default void addColumnNamesToExcellFile(String excellFileSheetName, HSSFSheet excellFileWorkbookSheet) {
        /* Заголовок столбцов*/
        HSSFRow hssfRow = excellFileWorkbookSheet.createRow(0);
        String watrmnCodeColumnNameXlsFile = "Wtrmn code";
        hssfRow.createCell(0).setCellValue(watrmnCodeColumnNameXlsFile);
        String watrmnNameColumnNamelsFile = "Wtrmn name";
        hssfRow.createCell(1).setCellValue(watrmnNameColumnNamelsFile);
        String kitNameColumnNamelsFile = excellFileSheetName + " name";
        hssfRow.createCell(2).setCellValue(kitNameColumnNamelsFile);
        String wtrmnPriceColumnNamelsFile = "Wtrmn price";
        hssfRow.createCell(3).setCellValue(wtrmnPriceColumnNamelsFile);
        String kitPriceColumnNamelsFile = excellFileSheetName + " price";
        hssfRow.createCell(4).setCellValue(kitPriceColumnNamelsFile);
        String groupeColumnNamelsFile = "Groupe";
        hssfRow.createCell(5).setCellValue(groupeColumnNamelsFile);
    }

    default void fillRowInExcellFile(HSSFRow hssfRow, DetailItem detailItem) {
        hssfRow.createCell(0).setCellValue(detailItem.getWatermanItemDTO().getCode());
        hssfRow.createCell(1).setCellValue(detailItem.getWatermanItemDTO().getName());
        hssfRow.createCell(2).setCellValue(detailItem.getName());
        hssfRow.createCell(3).setCellValue(detailItem.getWatermanItemDTO().getPrice().toString());
        hssfRow.createCell(4).setCellValue(detailItem.getPriceDiscount().toString());
        hssfRow.createCell(5).setCellValue(detailItem.getWatermanItemDTO().getGroup());
    }
}
