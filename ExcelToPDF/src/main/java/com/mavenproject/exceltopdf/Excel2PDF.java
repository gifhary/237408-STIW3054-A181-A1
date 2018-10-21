/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavenproject.exceltopdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Gifhary
 */
public class Excel2PDF {

    private static final String INPUT_FILE = "test_file.xlsx";
    private static final List<Format> data = new ArrayList();

    public static void readExcel() {
        try {
            System.out.println("Accessing the file...");
            FileInputStream inputFile = new FileInputStream(INPUT_FILE);

            XSSFWorkbook workbook = new XSSFWorkbook(inputFile);
            XSSFSheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                Cell column1 = row.getCell(0);
                Cell column2 = row.getCell(2);//if i put the value 1, it only contain four null data in the file
                //i don't know why, maybe it would be different with different file
                Cell column3 = row.getCell(3);
                Cell column4 = row.getCell(4);
                Cell column5 = row.getCell(5);
                Cell column6 = row.getCell(6);
                
                String a = String.valueOf(column1);
                String b = String.valueOf(column2);
                String c = String.valueOf(column3);
                String d = String.valueOf(column4);
                String e = String.valueOf(column5);
                String f = String.valueOf(column6);

                data.add(new Format(a, b, c, d, e, f));
            }
            System.out.println("Data has been recorded");
        } catch (IOException e) {
            System.out.println("ERROR : Failed to read file!");
        }
    }

    private static void writePDF() {
        if (data.isEmpty()) {
            System.out.println("ERROR : No data to write, build terminated!");
            System.exit(0); //program will write empty file if not terminated
        }

        String pdfFile = "Output File.pdf";
        Document document = new Document();
        PdfPTable table = new PdfPTable(new float[] { 1, 6, 1, 1, 1, 3 });

        try {
            for (int i = 0; i < data.size(); i++) {
                
                table.addCell(data.get(i).getNumber());
                table.addCell(data.get(i).getName());
                table.addCell(data.get(i).getFideID());
                table.addCell(data.get(i).getFed());
                table.addCell(data.get(i).getRtg());
                table.addCell(data.get(i).getClubCty());
                
            }

            PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
            document.open();
            document.add(table);
            document.close();

            System.out.println(pdfFile + " Is written successfully.");
        } catch (DocumentException | IOException e) {
            System.out.println("ERROR : Failed to write the file!");
        }
    }

    public static void main(String[] args) {
        readExcel();
        writePDF();
    }
}
