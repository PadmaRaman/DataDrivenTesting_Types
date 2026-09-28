package com.ai.testing.excelIntg;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class CreateExcel {
    public static void main(String[] args) {
        String filePath = "TestData.xlsx";
        // 1. Instantiate a new workbook
        try (Workbook workbook = new XSSFWorkbook()) {
            //2. Create a new sheet in the workbook
            Sheet sheet = workbook.createSheet("TestDataSheet");
            //3. Create a header row in the sheet
            Row headerRow = sheet.createRow(0);
            //4. Create header cells in theheader row
            Cell headerCell1 = headerRow.createCell(0);
            headerCell1.setCellValue("Name");

            Cell headerCell2 = headerRow.createCell(1);
            headerCell2.setCellValue("Age");

            Cell headerCell3 = headerRow.createCell(2);
            headerCell3.setCellValue("City");

            //5.Create a Data row in the sheet
            Row dataRow1 = sheet.createRow(1);
            //6. Create data cells in the data row
            dataRow1.createCell(0).setCellValue("John Doe");
            dataRow1.createCell(1).setCellValue(30);
            dataRow1.createCell(2).setCellValue("New York");

            Row dataRow2 = sheet.createRow(2);
            //6. Create data cells in the data row
            dataRow2.createCell(0).setCellValue("John Doe");
            dataRow2.createCell(1).setCellValue(30);
            dataRow2.createCell(2).setCellValue("New York");

            //7. Write the workbook to a file
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
                System.out.println("Excel file created successfully at: " + filePath);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
