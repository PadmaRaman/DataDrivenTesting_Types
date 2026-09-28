package com.ai.testing.excelIntg;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ReadExcel {
    public static void main(String[] args) {
        String filePath = "TestData.xlsx";

        //1. Implement logic to read the Excel file and print its contents
        try(FileInputStream fileIn = new FileInputStream(filePath)) {
            //2. Use Apache POI library to read the Excel file
            Workbook workbook = WorkbookFactory.create(fileIn);
            //3. Iterate through the rows and cells to print the contents
            Sheet sheet = workbook.getSheet("TestDataSheet");

            //4. Create a DataFormatter to safely convert all cell types ino Strings
            DataFormatter formatter = new DataFormatter();

            //5. Iterate through the rows and cells to print the contents
            int totalRows = sheet.getLastRowNum();
            System.out.println("Total Rows: " + totalRows);

            //6. Iterate through the rows and cells to print the contents
            for (int i = 0; i <= totalRows; i++) {
                Row row = sheet.getRow(i);
                int totalCells = row.getLastCellNum();
                System.out.println("Total Cells in Row " + i + ": " + totalCells);
                for (int j = 0; j < totalCells; j++) {
                    Cell cell = row.getCell(j);
                    String cellValue = formatter.formatCellValue(cell);
                    System.out.print(cellValue + "\t");
                }
                System.out.println();
            }
        }
            catch (Exception e){
            e.printStackTrace();
        }
        // Implement logic to read the Excel file and print its contents
        // You can use Apache POI library to read the Excel file
    }
}
