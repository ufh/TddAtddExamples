package com.qagile.configurator.excel;

import com.qagile.configurator.domain.Training;
import com.qagile.configurator.domain.TrainingAction;
import com.qagile.configurator.domain.TrainingModule;
import com.qagile.configurator.domain.TrainingTopic;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

/**
 * User: ful
 * Date: 30.04.12
 * Time: 21:59
 */
public class TrainingReaderExcel {

    private static Logger logger = Logger.getLogger("com.qagile.configurator.excel");

    private LanguageSupport ls = LanguageSupportFactory.getLanguageSupport("en");

    private HSSFSheet infoSheet = null;
    private HSSFSheet trainingSheet = null;

    private String language = "en";

    private int COL_MODULE;
    private int COL_TOPIC;
    private int COL_ACTION;
    private int COL_DURATION_ACTION;
    private int COL_DURATION_TOPIC;
    private int COL_DURATION_MOUDLE;
    private int COL_DURATION_TRAINING;
    private int COL_SELECTED;
    private int COL_TARGET_GROUPS;
    private int COL_MODULE_NUMBER;

    private int actualRow = 0;

    public Training read(String fileName) throws IOException, InvalidFormatException {

        HSSFWorkbook myWorkBook = openWorkBook(fileName);

        return readTraining(myWorkBook);
    }

    protected static HSSFWorkbook openWorkBook(String fileName) throws IOException {
        File file = new File(fileName);
        FileInputStream input = new FileInputStream(file);

        /** Create a POIFSFileSystem object**/
        POIFSFileSystem myFileSystem = new POIFSFileSystem(input);

        /** Create a workbook using the File System**/
        return new HSSFWorkbook(myFileSystem);
    }

    /**
     * Reads the info and the whole training from the workbook
     * @param myWorkBook
     * @return
     */
    private Training readTraining(HSSFWorkbook myWorkBook) throws InvalidFormatException {

        Training training = new Training();

        readInfo(myWorkBook, training);

        List missingTitles = readTitleCols(myWorkBook);
        if(missingTitles.size() != 0){
            throw new InvalidFormatException("Missing Titles: " + missingTitles.toString());
        }

        readTitles(training);

        readContent(training);

        return training;
    }


    private void readTitles(Training training) {
        training.setDepthTitle(trainingSheet.getRow(0).getCell(COL_ACTION).getStringCellValue());
        training.setDurationTitle(trainingSheet.getRow(0).getCell(COL_DURATION_ACTION).getStringCellValue());
        training.setModuleTitle(trainingSheet.getRow(0).getCell(COL_MODULE).getStringCellValue());
        training.setModuleNumberTitle(trainingSheet.getRow(0).getCell(COL_MODULE_NUMBER).getStringCellValue());
        training.setSelectedTitle(trainingSheet.getRow(0).getCell(COL_SELECTED).getStringCellValue());
        training.setTargetGroupTitle(trainingSheet.getRow(0).getCell(COL_TARGET_GROUPS).getStringCellValue());
        training.setTopicTitle(trainingSheet.getRow(0).getCell(COL_TOPIC).getStringCellValue());
    }

    /**
     * Reads the titles and stores the column number:
     * - read first line of "Training" sheet
     * - read all cols and store value and number in a map
     * - search for fitting col and set col variables
     *
     * @param myWorkBook
     * @return a list of missing titles
     */
    private List<String> readTitleCols(HSSFWorkbook myWorkBook) {

        Map<String, Integer> cols = new HashMap<String, Integer>();
        List<String> missingTitleList = new ArrayList<String>();

        trainingSheet = myWorkBook.getSheet(ls.NameTrainingSheet());
        Row titleRow = trainingSheet.getRow(0);
        int numOfCells = titleRow.getLastCellNum();
        for (int i=0; i<numOfCells; i++){
            cols.put(titleRow.getCell(i).getStringCellValue(), i);
        }

        COL_MODULE_NUMBER = readInt(cols, ls.ModuleNumberTitle(), missingTitleList);
        COL_MODULE        = readInt(cols, ls.ModuleTitle(), missingTitleList);
        COL_TOPIC         = readInt(cols, ls.TopicTitle(), missingTitleList);
        COL_ACTION = readInt(cols, ls.ActionTitle(), missingTitleList);
        COL_DURATION_ACTION = readInt(cols, ls.DurationActionTitle(),missingTitleList);
        COL_SELECTED      = readInt(cols, ls.SelectedTitle(), missingTitleList);
        COL_TARGET_GROUPS = readInt(cols, ls.TargetGroupsTitle(), missingTitleList);

        return missingTitleList;
    }

    private int readInt(Map<String, Integer> map, String key, List missingList){

        if (map.get(key) == null) {
            missingList.add(key);
            return -1;
        }else{
            return map.get(key).intValue();
        }
    }

    /**
     * read info about the version and the language
     * @param myWorkBook
     */
    private void readInfo(HSSFWorkbook myWorkBook, Training training) {

        infoSheet = myWorkBook.getSheet(LanguageSupportFactory.getInfoSheetName());

        training.setVersion(infoSheet.getRow(0).getCell(1).getStringCellValue());
        language = infoSheet.getRow(1).getCell(1).getStringCellValue();
        training.setLanguage(language);

    }

    private void readContent(Training training){

        actualRow = 1;
        while (actualRow <= trainingSheet.getLastRowNum()){
            if(rowIsModule(actualRow)){
                training.addModule(readModule(actualRow));
            }
            actualRow++;
        }
    }

    private TrainingModule readModule(int row){

        TrainingModule module = new TrainingModule();
        module.setNumber(trainingSheet.getRow(row).getCell(COL_MODULE_NUMBER).getStringCellValue());
        module.setName(trainingSheet.getRow(row).getCell(COL_MODULE).getStringCellValue());
        // is next line a module then we are finished
        logger.info("Module: " + module.getName());
        if (rowIsModule(row+1) || (row+1)> trainingSheet.getLastRowNum()){
            return module;
        }
        // else read topics and actions...
        actualRow++;
        module.setTopics(readTopics());
        return module;
    }

    private List<TrainingTopic> readTopics() {

        List<TrainingTopic> topics = new ArrayList<TrainingTopic>();
        topics.add(readTopic());
        if(rowIsTopic(actualRow+1)){
            actualRow++;
           topics.add(readTopic());
        }
        return topics;
    }

    private TrainingTopic readTopic(){
        TrainingTopic topic = new TrainingTopic();
        topic.name = trainingSheet.getRow(actualRow).getCell(COL_TOPIC).getStringCellValue();
        logger.info("  Topic: " + topic.name);
        topic.actions = readActions();
        return topic;
    }

    private List<TrainingAction> readActions() {
        List<TrainingAction> actions = new ArrayList<TrainingAction>();

        actions.add(readAction());
        int nextRow = actualRow+1;
        while(!rowIsTopic(nextRow) && rowIsAction(nextRow)){
            actualRow++;
            actions.add(readAction());
            nextRow = actualRow+1;
        }
        return actions;
    }

    private TrainingAction readAction() {
        TrainingAction action = new TrainingAction();
        action.depth = trainingSheet.getRow(actualRow).getCell(COL_ACTION).getStringCellValue();
        action.duration = trainingSheet.getRow(actualRow).getCell(COL_DURATION_ACTION).getStringCellValue();
        action.selected = trainingSheet.getRow(actualRow).getCell(COL_SELECTED).getStringCellValue();
        action.targetGroup = trainingSheet.getRow(actualRow).getCell(COL_TARGET_GROUPS).getStringCellValue();
        logger.info("    Action: " + action.depth);
        return action;
    }

    private boolean rowIsAction(int row) {
        if(trainingSheet.getRow(row) == null){
            return false;
        }
        return trainingSheet.getRow(row).getCell(COL_ACTION) != null;
    }

    private boolean rowIsTopic(int row) {
        if(trainingSheet.getRow(row) == null){
            return false;
        }
        return trainingSheet.getRow(row).getCell(COL_TOPIC) != null;
    }


    private boolean rowIsModule(int row) {
        if(trainingSheet.getRow(row) == null){
            return false;
        }
        return trainingSheet.getRow(row).getCell(COL_MODULE_NUMBER) != null;
    }
}
