package ru.bjcreslin.service.analyzes;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import ru.bjcreslin.model.Item;

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
}
