package com.obem.utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils implements AutoCloseable {

    private final XSSFWorkbook workbook;
    private final DataFormatter formatter = new DataFormatter();

    public ExcelUtils(String path) throws IOException {
        workbook = new XSSFWorkbook(new FileInputStream(path));
    }

    public int getRowCount(String sheetName) {
        return workbook.getSheet(sheetName).getPhysicalNumberOfRows();
    }

    public String getCellData(String sheetName, int row, int column) {
        return formatter.formatCellValue(
                workbook.getSheet(sheetName).getRow(row).getCell(column));
    }

    @Override
    public void close() throws IOException {
        workbook.close();
    }
}
