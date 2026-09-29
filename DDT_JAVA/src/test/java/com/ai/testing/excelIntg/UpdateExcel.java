package com.ai.testing.excelIntg;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class UpdateExcel {
    public static void main(String[] args) {
        String filePath = "TestData.xlsx";
        // Implement logic to update the Excel file
        // You can use Apache POI library to update the Excel file
        try(FileInputStream fileIn = new FileInputStream(filePath)) {
            Workbook workbook = WorkbookFactory.create(fileIn);
            Sheet sheet = workbook.getSheet("TestDataSheet");
            // Update the value of a specific cell
            Row row = sheet.getRow(1);
            Cell cell = row.getCell(1);
            cell.setCellValue(35); // Update age to 35

            //Add a new column header
            Row headerRow = sheet.getRow(0);
            Cell newHeaderCell = headerRow.createCell(3);
            newHeaderCell.setCellValue("Country");

            // Add a new data cell in the new column for the first data row
            Row datarow1 = sheet.getRow(1);
            datarow1.createCell(3).setCellValue("USA");
            Row datarow2 = sheet.getRow(2);
            datarow2.createCell(3).setCellValue("Germany");
            Row datarow3 = sheet.createRow(3);
            datarow3.createCell(0).setCellValue("Maria Rossi");
            datarow3.createCell(1).setCellValue(33);
            datarow3.createCell(2).setCellValue("Frankfurt");
            datarow3.createCell(3).setCellValue("Italy");

            //Create new row at last
            int lastRowNum = sheet.getLastRowNum();
            Row newRow = sheet.createRow(lastRowNum + 1);
            newRow.createCell(0).setCellValue("Alice Smith");
            newRow.createCell(1).setCellValue(28);
            newRow.createCell(2).setCellValue("Los Angeles");
            newRow.createCell(3).setCellValue("Canada");

            // Write the updated workbook back to the file
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
                System.out.println("Excel file updated successfully at: " + filePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
