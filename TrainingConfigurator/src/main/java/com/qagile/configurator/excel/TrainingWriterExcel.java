package com.qagile.configurator.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: ful
 * Date: 22.04.12
 * Time: 21:33
 * To change this template use File | Settings | File Templates.
 */
public class TrainingWriterExcel {

    public final static String VERSION = "0.0.1";
    public final static String LANGUAGE = "en";
    
    /**
     * Creates a new excel-file and creates the title colons.
     * Be careful: if file already exists it will be overridden!!
     * @param filename
     */
    public void newTrainingFile(String filename) throws IOException {

        //create new file
        Workbook wb = new HSSFWorkbook();


        //create info and training sheet
        Sheet infoSheet = wb.createSheet(getNameInfoSheet());
        Sheet trainingSheet = wb.createSheet(getNameTrainingSheet());

        //add version info
        // Create a row and put some cells in it. Rows are 0 based.
        Row row = infoSheet.createRow(0);
        // Create a cell and put a value in it.
        row.createCell(0).setCellValue("Version:");
        row.createCell(1).setCellValue(this.VERSION);

        row = infoSheet.createRow(1);
        row.createCell(0).setCellValue("Language:");
        row.createCell(1).setCellValue(this.LANGUAGE);

        //add training titles
        row = trainingSheet.createRow(0);
        row.createCell(0).setCellValue(getModulTitle(this.LANGUAGE));
        row.createCell(1).setCellValue(getTopicTitle(this.LANGUAGE));
        row.createCell(2).setCellValue(getDepthTitle(this.LANGUAGE));
        row.createCell(3).setCellValue(getDurationTitle(this.LANGUAGE));
        row.createCell(4).setCellValue(getTargetGroupsTitle(this.LANGUAGE));

        // save workbook
        FileOutputStream fileOut = new FileOutputStream(filename);
        wb.write(fileOut);
        fileOut.close();

    }

    private String getNameTrainingSheet() {
        return "training";
    }

    private String getModulTitle(String lang) {
        return "modul";
    }

    private String getTopicTitle(String lang) {
        return "topic";
    }
    private String getDepthTitle(String lang) {
        return "depth";
    }
    private String getDurationTitle(String lang) {
        return "duration";
    }
    private String getTargetGroupsTitle(String lang) {
        return "target groups";
    }
    private String getNameInfoSheet() {
        return "info";
    }


}
