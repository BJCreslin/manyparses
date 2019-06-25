package ru.bjcreslin.service.analyzes;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.CellType;
import ru.bjcreslin.model.domain.DetailItem;
import ru.bjcreslin.model.domain.Item;

import java.util.List;

/**
 * Что и как анализируем
 */
public interface AnalyzeService {
    /**
     * Находит все элементы, у котрых цена Waterman дороже
     *
     * @return List Элекментов Item
     */
    List<Item> findAllCheaps();

    /**
     * Добавляет названия столбцов в эксель файл
     *
     * @param excellFileSheetName     навание текущих данных
     * @param excellFileWorkbookSheet Книга Excell
     */
    default void addColumnNamesToExcellFile(String excellFileSheetName, HSSFSheet excellFileWorkbookSheet) {
        /* Заголовок столбцов*/
        HSSFCell hssfCell;
        HSSFRow hssfRow = excellFileWorkbookSheet.createRow(0);

        String watrmnCodeColumnNameXlsFile = "Wtrmn code";
        hssfCell = hssfRow.createCell(0);
        hssfCell.setCellType(CellType.STRING);
        hssfCell.setCellValue(watrmnCodeColumnNameXlsFile);

        String watrmnNameColumnNamelsFile = "Wtrmn name";
        hssfCell = hssfRow.createCell(1);
        hssfCell.setCellType(CellType.STRING);
        hssfCell.setCellValue(watrmnNameColumnNamelsFile);

        String kitNameColumnNamelsFile = excellFileSheetName + " name";
        hssfCell = hssfRow.createCell(2);
        hssfCell.setCellType(CellType.STRING);
        hssfCell.setCellValue(kitNameColumnNamelsFile);

        String wtrmnPriceColumnNamelsFile = "Wtrmn price";
        hssfCell = hssfRow.createCell(3);
        hssfCell.setCellType(CellType.STRING);
        hssfCell.setCellValue(wtrmnPriceColumnNamelsFile);

        String kitPriceColumnNamelsFile = excellFileSheetName + " price";
        hssfCell = hssfRow.createCell(4);
        hssfCell.setCellType(CellType.STRING);
        hssfCell.setCellValue(kitPriceColumnNamelsFile);

        String groupeColumnNamelsFile = "Groupe";
        hssfCell = hssfRow.createCell(5);
        hssfCell.setCellType(CellType.STRING);
        hssfCell.setCellValue(groupeColumnNamelsFile);
    }

    /**
     * Заполняет строчку excell файла данными DetailItem
     *
     * @param hssfRow    заполняемая строка
     * @param detailItem данные
     */
    default void fillRowInExcellFile(HSSFRow hssfRow, DetailItem detailItem) {

        HSSFCell hssfCell = hssfRow.createCell(0);
        hssfCell.setCellType(CellType.NUMERIC);
        hssfCell.setCellValue(Long.parseLong(detailItem.getWatermanItemDTO().getCode().toString()));

        hssfCell = hssfRow.createCell(1);
        hssfCell.setCellType(CellType.STRING);
        hssfCell.setCellValue(detailItem.getWatermanItemDTO().getName());

        hssfCell = hssfRow.createCell(2);
        hssfCell.setCellType(CellType.STRING);
        hssfCell.setCellValue(detailItem.getName());

        hssfCell = hssfRow.createCell(3);
        hssfCell.setCellType(CellType.NUMERIC);
        hssfCell.setCellValue(detailItem.getWatermanItemDTO().getPrice().doubleValue());

        hssfCell = hssfRow.createCell(4);
        hssfCell.setCellType(CellType.NUMERIC);
        hssfCell.setCellValue(detailItem.getDiscountPriceWithoutMulty().doubleValue());

        hssfCell = hssfRow.createCell(5);
        hssfCell.setCellType(CellType.STRING);
        hssfCell.setCellValue(detailItem.getWatermanItemDTO().getGroup());
    }
}
