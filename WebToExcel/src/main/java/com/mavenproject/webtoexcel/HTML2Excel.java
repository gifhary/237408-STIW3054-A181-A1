/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavenproject.webtoexcel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Gifhary
 */
public class HTML2Excel {

    private static List<Format> dataRecord = new ArrayList();
    private static String URL = "https://ms.wikipedia.org/wiki/Malaysia";

    public static void collectWebTableData() {
        try {
            System.out.println("Accessing " + URL + "...");

            Document source = Jsoup.connect(URL).get();
            Element table = source.select("table").get(5);
            Elements rows = table.select("tr");

            for (Element row : rows) {

                Elements data1 = row.select("th");
                Elements data2 = row.select("td");

                String column1 = data1.text();
                String column2 = data2.text();

                dataRecord.add(new Format(column1, column2));
            }

            System.out.println("Table data has been collected successfully.");
            System.out.println();

        } catch (IOException e) {
            System.out.println("ERROR : Failed to access " + URL);
        }
    }

    public static void writeToXLSFile() {

        if (dataRecord.isEmpty()) {
            System.out.println("ERROR : No data to write, build terminated.");
            System.exit(0);
        }

        String excelFile = "Output File.xlsx";

        System.out.println("Writing the " + excelFile + "...");

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Malaysia");
        try {
            for (int i = 0; i < dataRecord.size(); i++) {
                XSSFRow row = sheet.createRow(i);

                XSSFCell cell1 = row.createCell(0);
                cell1.setCellValue(dataRecord.get(i).getHeader());
                XSSFCell cell2 = row.createCell(1);
                cell2.setCellValue(dataRecord.get(i).getData());
            }

            FileOutputStream outputFile = new FileOutputStream(excelFile);
            workbook.write(outputFile);
            outputFile.flush();
            outputFile.close();
            System.out.println(excelFile + " Is written successfully.");
        } catch (IOException e) {
            System.out.println("ERROR : Failed to write the file!");
        }
    }

    public static void main(String[] args) {
        collectWebTableData();
        writeToXLSFile();
    }
}