package ru.bjcreslin.service.analyzes;

import lombok.AllArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFName;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.FileSystemUtils;
import ru.bjcreslin.Exceptions.ErrorCreationTempFile;
import ru.bjcreslin.model.KitItemDTO;
import ru.bjcreslin.repository.ItemKitRepository;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
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
    private static String excellFileSheetName = "Kit";
    private static String prefix = "KitWaterman";
    private static String suffix = "xls";
    private static String tempPAth = "\\resources\\static\\temp";

    private ItemKitRepository repository;

    /**
     *
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
     * @param kitItemDTOList
     * @return
     * @throws ErrorCreationTempFile
     */
    public Path saveCheaps(List<KitItemDTO> kitItemDTOList) throws ErrorCreationTempFile {
        Path excellFile;
        HSSFWorkbook excellFileWorkbook = null;
        try {
            excellFile = createTempFile(prefix, suffix);

            excellFileWorkbook = new HSSFWorkbook(POIFSFileSystem.create(excellFile.toFile()));
        } catch (IOException e) {
            throw new ErrorCreationTempFile();
        }
        HSSFSheet excellFileWorkbookSheet = excellFileWorkbook.createSheet(excellFileSheetName);
        /* Заголовок столбцов*/
        HSSFRow hssfRow = excellFileWorkbookSheet.createRow(0);
        hssfRow.createCell(0).setCellValue("Wtrmn code");
        hssfRow.createCell(1).setCellValue("Wtrmn name");
        hssfRow.createCell(2).setCellValue("Kit name");
        hssfRow.createCell(3).setCellValue("Wtrmn price");
        hssfRow.createCell(4).setCellValue("Kit price");
        hssfRow.createCell(5).setCellValue("Groupe");

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

        return excellFile;
    }


}
