package com.ai.testing.excelIntg;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtils {

    private String filePath;

    private ExcelUtils(String filePath) {
        this.filePath = filePath;
    }

    // Get a Row count from the Excel file
    public int getRowCount(String sheetName) throws IOException {
        try (FileInputStream fileIn = new FileInputStream(filePath)) {
            Workbook workbook = WorkbookFactory.create(fileIn);
            Sheet sheet = workbook.getSheet(sheetName);
            return (sheet != null) ? sheet.getLastRowNum() + 1 : 0; // Adding 1 because row index starts from 0
        }
    }

    // Get Cell Data as String
    public String getCellData(String sheetName, int rowNum, int colNum) throws IOException {
        try (FileInputStream fileIn = new FileInputStream(filePath)) {
            Workbook workbook = WorkbookFactory.create(fileIn);
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet != null && rowNum < sheet.getLastRowNum() + 1) {
                return sheet.getRow(rowNum).getCell(colNum).toString();
            } else {
                return null;
            }
        }
    }

    //Set Cell Data as String
    public void setCellData(String sheetName, int rowNum, int colNum, String data) throws IOException {
        try (FileInputStream fileIn = new FileInputStream(filePath)) {
            Workbook workbook = WorkbookFactory.create(fileIn);
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet != null) {
                if (rowNum >= sheet.getLastRowNum() + 1) {
                    sheet.createRow(rowNum);
                }
                sheet.getRow(rowNum).createCell(colNum).setCellValue(data);
                // Write the updated workbook back to the file
                try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                    workbook.write(fileOut);
                }
            }
        }
    }

    // Update Cell Data as String
    public void updateCellData(String sheetName, int rowNum, int colNum, String newData) throws IOException {
        try (FileInputStream fileIn = new FileInputStream(filePath)) {
            Workbook workbook = WorkbookFactory.create(fileIn);
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet != null && rowNum < sheet.getLastRowNum() + 1) {
                sheet.getRow(rowNum).getCell(colNum).setCellValue(newData);
                // Write the updated workbook back to the file
                try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                    workbook.write(fileOut);
                }
            }
        }
    }

}
