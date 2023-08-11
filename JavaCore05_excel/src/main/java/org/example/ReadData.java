package org.example;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.IOException;
import java.io.InputStream;

public class ReadData {
    private static Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
        Workbook workbook = null;
        if(excelFilePath.endsWith("xlsx")){
            workbook = new XSSFWorkbook(inputStream); //new XSSFWorkbook(inputStream)
        }else if(excelFilePath.endsWith("xls")){
            workbook = new HSSFWorkbook(inputStream);
        }else{
            System.out.println("File không đúng định dạng");
        }
        return workbook;
    }

    private static
}
