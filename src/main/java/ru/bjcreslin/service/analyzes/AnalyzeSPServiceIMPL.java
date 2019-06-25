package ru.bjcreslin.service.analyzes;

import lombok.AllArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;
import ru.bjcreslin.Exceptions.ErrorCreationTempFile;
import ru.bjcreslin.model.domain.Item;
import ru.bjcreslin.model.domain.StroyparkItemDTO;
import ru.bjcreslin.repository.ItemSPRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * сервисный класс анализа СП
 */
@Service
@AllArgsConstructor
public class AnalyzeSPServiceIMPL implements AnalyzeService {
    ItemSPRepository itemSPRepository;

    /**
     * Ищет все данные, у которых цена Водяного, выше цены Стройпарка
     *
     * @return список элементов Стройпарка
     */
    public List<Item> findAllCheaps() {
        List<Item> stroyparkItemDTOList = new ArrayList<>();
        List<StroyparkItemDTO> stroyparkItemDTOListALL = itemSPRepository.findAll();
        for (StroyparkItemDTO stroyparkItemDTO : stroyparkItemDTOListALL) {
            BigDecimal priceSP = stroyparkItemDTO.getPriceDiscount().divide(BigDecimal.valueOf(stroyparkItemDTO.getMulty()));
            BigDecimal priceWtrmn = stroyparkItemDTO.getWatermanItemDTO().getPrice();
            try {
                if (priceSP.compareTo(priceWtrmn) < 0) {
                    stroyparkItemDTOList.add(stroyparkItemDTO);
                }
            } catch (Exception e) {
                continue;
            }
        }

        return stroyparkItemDTOList;
    }

    /**
     * todo: Сделать автоматическую ширину столбца
     *
     * @param stroyparkItemDTOS список СтройпаркИтемов
     * @return файл ексель
     * @throws ErrorCreationTempFile
     */
    public HSSFWorkbook saveCheaps(List<Item> stroyparkItemDTOS) {
        HSSFWorkbook excellFileWorkbook;

        excellFileWorkbook = new HSSFWorkbook();
        String excellFileSheetName = "Stroypark";

        HSSFSheet excellFileWorkbookSheet = excellFileWorkbook.createSheet(excellFileSheetName);
        addColumnNamesToExcellFile(excellFileSheetName, excellFileWorkbookSheet);
        HSSFRow hssfRow;

        /*По всему листу данных заполняем таблицу */
        for (int i = 0; i < stroyparkItemDTOS.size(); i++) {
            StroyparkItemDTO stroyparkItemDTO = (StroyparkItemDTO) stroyparkItemDTOS.get(i);
            hssfRow = excellFileWorkbookSheet.createRow(i + 1);
            hssfRow.createCell(0).setCellValue(stroyparkItemDTO.getWatermanItemDTO().getCode());
            hssfRow.createCell(1).setCellValue(stroyparkItemDTO.getWatermanItemDTO().getName());
            hssfRow.createCell(2).setCellValue(stroyparkItemDTO.getName());
            hssfRow.createCell(3).setCellValue(stroyparkItemDTO.getWatermanItemDTO().getPrice().toString());
            hssfRow.createCell(4).setCellValue(stroyparkItemDTO.getPriceDiscount().toString());
            hssfRow.createCell(5).setCellValue(stroyparkItemDTO.getWatermanItemDTO().getGroup());
        }

        return excellFileWorkbook;
    }




}
