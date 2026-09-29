package com.ai.testing.excelIntg;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DeleteExcel {
    public static void main(String[] args) {
        String filePath = "TestData.xlsx";

        try (FileInputStream fileIn = new FileInputStream(filePath)) {
            Workbook workbook = WorkbookFactory.create(fileIn);
            // 1. Delete a specific cell
            Sheet sheet = workbook.getSheet("TestDataSheet");
            Row datarow1 = sheet.getRow(1);
            Cell cell = datarow1.getCell(2);
            if ( cell !=null )
            {
                datarow1.removeCell(cell);
            }
            System.out.println("Cell deleted successfully from row 1, column 2.");

            //Delete a specific row
            int rowIndexToDelete = 3; // Index of the row to delete (0-based)
            Row dataRow = sheet.getRow(rowIndexToDelete);

            if (dataRow != null) {
                sheet.removeRow(dataRow);

                int lastRowNum = sheet.getLastRowNum();
                if(rowIndexToDelete < lastRowNum)
                {
                    sheet.shiftRows(rowIndexToDelete + 1, lastRowNum, -1);
                }
            }
            System.out.println("Row deleted successfully at index: " + rowIndexToDelete);

            // Deleting an entire sheet
            int sheetIndexToDelete = workbook.getSheetIndex("TestDataSheet");
            if(sheetIndexToDelete != -1)
                workbook.removeSheetAt(sheetIndexToDelete);// Index of the sheet to delete (0)
            System.out.println("Sheet deleted successfully at index: " + sheetIndexToDelete);

            // Write the updated workbook back to the file
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
                System.out.println("Excel file updated successfully at: " + filePath);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
