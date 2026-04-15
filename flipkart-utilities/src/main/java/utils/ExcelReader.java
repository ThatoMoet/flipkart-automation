package utils;

import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ExcelReader {
    private static final Logger logger = LoggerHelper.logger(ExcelReader.class);
    public static List<Map<String, String>> readTestData(String sheetName){
        List<Map<String, String>> results = new ArrayList<>();

        try ( FileInputStream in = new FileInputStream("testdata/TestData.xlsx");
             Workbook workbook = new XSSFWorkbook(in))
       {
           Sheet sheet = workbook.getSheet(sheetName);

           Row headerRow = sheet.getRow(0);
           Map<String, Integer> headers = new HashMap<>();
           for (Cell cell : headerRow) {
               headers.put(cell.getStringCellValue(), cell.getColumnIndex());
           }
           int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();

           for (int i=1; i<= rowCount; i++ ){
               Row currentRow = sheet.getRow(i);
               int reqIndex = headers.get("Execution Required");
               Cell reqCell = currentRow.getCell(reqIndex);
               String cellVal = reqCell.getStringCellValue();
               if (cellVal.equalsIgnoreCase("yes")){
                   Map<String, String> rowData = new HashMap<>();
                   for (Map.Entry<String, Integer> header : headers.entrySet()) {
                       Cell cell = currentRow.getCell(header.getValue());
                       String cellValue = (cell != null) ? cell.getStringCellValue() : "";
                       rowData.put(header.getKey(), cellValue);
                   }
                   results.add(rowData);               }
           }

       }catch (
            IOException e){
           logger.error("Failed to read test file", e);
           return Collections.emptyList();
       }
        return results;

    }
}
