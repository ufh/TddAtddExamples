package com.qagile.configurator.excel;

import com.qagile.configurator.domain.Training;
import com.qagile.configurator.domain.TrainingAction;
import com.qagile.configurator.domain.TrainingModule;
import com.qagile.configurator.domain.TrainingTopic;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: ful
 * Date: 22.04.12
 * Time: 21:33
 * To change this template use File | Settings | File Templates.
 */
public class TrainingWriterExcel {

    //TODO should be share by writer and read. Actually any change in order
    //is reset by save....
    private int COL_MODULE_NUMBER   = 0;
    private int COL_MODULE          = 1;
    private int COL_TOPIC           = 2;
    private int COL_ACTION          = 3;
    private int COL_DURATION_ACTION = 4;
    private int COL_SELECTED        = 5;
    private int COL_DURATION_TOPIC  = 6;
    private int COL_DURATION_MOUDLE = 7;
    private int COL_TARGET_GROUPS    = 8;
    private int COL_DURATION_TRAINING = 9 ;


    public final static String VERSION = "0.0.1";
    public final static String LANGUAGE = "en";

    private LanguageSupport ls;

    Sheet infoSheet;
    Sheet trainingSheet;

    private int actualRow = 0;

    /**
     * Creates a new excel-file and creates the title colons.
     * Be careful: if file already exists it will be overridden!!
     * @param filename
     */
    public void newTrainingFile(String filename, String language) throws IOException {

        //create new file
        Workbook wb = new HSSFWorkbook();

        ls = LanguageSupportFactory.getLanguageSupport("en");

        //create info and training sheet
        infoSheet = wb.createSheet(LanguageSupportFactory.getInfoSheetName());
        trainingSheet = wb.createSheet(ls.NameTrainingSheet());

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
        row.createCell(COL_MODULE_NUMBER).setCellValue(ls.ModuleNumberTitle());
        row.createCell(COL_MODULE).setCellValue(ls.ModuleTitle());
        row.createCell(COL_TOPIC).setCellValue(ls.TopicTitle());
        row.createCell(COL_ACTION).setCellValue(ls.ActionTitle());
        row.createCell(COL_DURATION_ACTION).setCellValue(ls.DurationActionTitle());
        row.createCell(COL_SELECTED).setCellValue(ls.SelectedTitle());
        row.createCell(COL_DURATION_TOPIC).setCellValue(ls.DurationTopicTitle());
        row.createCell(COL_DURATION_MOUDLE).setCellValue(ls.DurationModuleTitle());
        row.createCell(COL_TARGET_GROUPS).setCellValue(ls.TargetGroupsTitle());
        row.createCell(COL_DURATION_TRAINING).setCellValue(ls.DurationTrainingTitle());

        save(filename, wb);

    }

    private void save(String filename, Workbook wb) throws IOException {
        // save workbook
        FileOutputStream fileOut = new FileOutputStream(filename);
        wb.write(fileOut);
        fileOut.close();
    }

    public void newTrainingFile(String filename, Training training) throws IOException {

        // create new training file
        newTrainingFile(filename, training.language());

        // and reopen it
        Workbook wb = TrainingReaderExcel.openWorkBook(filename);
        trainingSheet = wb.getSheet(ls.NameTrainingSheet());

        // create new rows and fill them with modules
        Iterator<String> it = training.getModules().keySet().iterator();
        //Iterator<TrainingModule> it = training.getModules().iterator();
        while(it.hasNext()){
           writeModule(training.getModules().get(it.next()));
        }

        // write and close
        save(filename, wb);
    }

    private void writeModule(TrainingModule module) {

        Row row = trainingSheet.createRow(++actualRow);
        row.createCell(COL_MODULE_NUMBER).setCellValue(module.getNumber());
        row.createCell(COL_MODULE).setCellValue(module.getName());

        //for all topics write topics
        Iterator<TrainingTopic> it = module.getTopics().iterator();
        while(it.hasNext()){
            row = trainingSheet.createRow(++actualRow);
            writeTopic(it.next(), row);
        }
    }

    private void writeTopic(TrainingTopic topic, Row row) {

        row.createCell(COL_TOPIC).setCellValue(topic.name);
        Iterator<TrainingAction> it = topic.getActions().iterator();
        if(!it.hasNext()) { return; }
        TrainingAction action = it.next();
        writeAction(action, row);

        while(it.hasNext()){
            row = trainingSheet.createRow(++actualRow);
            writeAction(it.next(), row);
        }

    }

    private void writeAction(TrainingAction action, Row row){
        row.createCell(COL_ACTION).setCellValue(action.depth);
        row.createCell(COL_DURATION_ACTION).setCellValue(action.duration);
        row.createCell(COL_TARGET_GROUPS).setCellValue(action.targetGroup);
        row.createCell(COL_SELECTED).setCellValue(action.selected);

    }
}
