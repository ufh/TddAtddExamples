package com.qagile.configurator.excel;

import com.qagile.configurator.domain.Training;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * User: ful
 * Date: 30.04.12
 * Time: 21:29
 */
public class ReadSheetTest {

//    private String fileName = "src/test/resource/EmptyTrainingTest.xls";
    private String fileName = "EmptyTrainingTest.xls";
    //TODO define pathToFile relativ
    //private String pathToFile = "C:\\Users\\ful\\FUL_privat\\TddAtddExamples\\TrainingConfigurator\\src\\test\\resource";
    private String pathToFile = "";

    private HSSFWorkbook myWorkBook;

    private static Logger logger = Logger.getLogger("Test");


    @Before
    public void openWorkbook() throws IOException {
        File file = new File(pathToFile + fileName);
        FileInputStream input = new FileInputStream(file);

        /** Create a POIFSFileSystem object**/
        POIFSFileSystem myFileSystem = new POIFSFileSystem(input);

        /** Create a workbook using the File System**/
        myWorkBook = new HSSFWorkbook(myFileSystem);

    }

    /**
     * To learn about reading sheets with POI we
     * play around here in a test
     */
    @Test
    public void readTrainingSheetWithPOI() throws IOException, URISyntaxException {


        /** Get the first sheet from workbook**/
        HSSFSheet sheetZero = myWorkBook.getSheet("Info");
        assertEquals("Info", sheetZero.getSheetName());

        HSSFSheet sheetTraining = myWorkBook.getSheet("Training");
        assertEquals("Training", sheetTraining.getSheetName());

        assertEquals(1,sheetZero.getLastRowNum());
        assertEquals(0,sheetTraining.getLastRowNum());
    }

    @Test
    public void readInfoSheetWithPoi(){
        HSSFSheet infoSheet = myWorkBook.getSheet("Info");
        Row firstRow = infoSheet.getRow(0);
        assertNotNull(firstRow);
        Row secondRow = infoSheet.getRow(1);
        assertNotNull(secondRow);

        String versionField = firstRow.getCell(0).getStringCellValue();
        String versionValue = firstRow.getCell(1).getStringCellValue();
        assertEquals("Version:", versionField);
        assertEquals("0.0.1", versionValue);

        String languageField = secondRow.getCell(0).getStringCellValue();
        String languageValue = secondRow.getCell(1).getStringCellValue();
        assertEquals("Language:", languageField);
        assertEquals("en", languageValue);

        assertEquals("0.0.1", myWorkBook.getSheet("Info").getRow(0).getCell(1).getStringCellValue());
    }

    @Test
    public void readTrainingInfo() throws IOException, InvalidFormatException {

        TrainingReaderExcel reader = new TrainingReaderExcel();

        Training training = reader.read(pathToFile + fileName);
        assertTrue(training != null);

        assertEquals("0.0.1", training.version());
        assertEquals("en", training.language());
    }

    @Test
    public void readTrainingTitles() throws IOException, InvalidFormatException {

        TrainingReaderExcel reader = new TrainingReaderExcel();

        Training training = reader.read(pathToFile + fileName);
        assertTrue(training != null);

        assertEquals("#", training.getModuleNumberTitle());
        assertEquals("Module", training.getModuleTitle());
        assertEquals("Topic", training.getTopicTitle());
        assertEquals("Action", training.getDepthTitle());
        assertEquals("Duration Action [min]", training.getDurationTitle());
        assertEquals("Selected", training.getSelectedTitle());
        assertEquals("Target Groups", training.getTargetGroupTitle());
    }

    @Test
    public void readTrainingModules() throws IOException, InvalidFormatException {

        TrainingReaderExcel reader = new TrainingReaderExcel();

        Training training = reader.read(pathToFile + "TrainingWithModules.xls");
        assertTrue(training != null);

        assertEquals(3, training.getModules().size());
        assertEquals("First Module", training.getModules().get("1").getName());
        assertEquals("Second Module", training.getModules().get("2").getName());
        assertEquals("Third Module", training.getModules().get("3").getName());
    }

    @Test
    public void readTrainingModulesAndTopics() throws IOException, InvalidFormatException {

        TrainingReaderExcel reader = new TrainingReaderExcel();

        Training training = reader.read(pathToFile + "TrainingWithModulesAndTopics.xls");
        assertTrue(training != null);

        logger.info(training.toTable());

        assertEquals("Third Module", training.getModules().get("3").getName());
        assertEquals("Second Module", training.getModules().get("2").getName());
        assertEquals("First Module", training.getModules().get("1").getName());

        assertEquals(3, training.getModules().size());
    }



    @Test
    public void testMap(){

        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("one", 1);
        map.put("two", 2);
        assertEquals(new Integer(1), map.get("one"));
        assertEquals(1, map.get("one").intValue());
        assertTrue(null == map.get("seven"));
        assertEquals(null, map.get("seven"));
    }

}
