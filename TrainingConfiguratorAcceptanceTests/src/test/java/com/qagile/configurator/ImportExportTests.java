package com.qagile.configurator;

import com.qagile.configurator.domain.Training;
import com.qagile.configurator.domain.TrainingAction;
import com.qagile.configurator.domain.TrainingModule;
import com.qagile.configurator.domain.TrainingTopic;
import com.qagile.configurator.excel.InvalidFormatException;
import com.qagile.configurator.excel.TrainingReaderExcel;
import com.qagile.configurator.excel.TrainingWriterExcel;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * User: ful
 * Date: 11.05.12
 * Time: 11:09
 */
public class ImportExportTests {

    String path = "";
    String fileName = "ComplexTraining.xls";

    Logger logger = Logger.getLogger("com.qagile.configurator");

    /**
     * Creates a new Training and exports it to excel. Reads in the
     * newly created sheet and check the entries.
     * @throws IOException
     */
    @Test
    public void ExportImportTest() throws IOException, InvalidFormatException {

        TrainingWriterExcel writer = new TrainingWriterExcel();

        Training expectedTraining = createComplexTraining();

        writer.newTrainingFile(fileName, expectedTraining);

        TrainingReaderExcel reader = new TrainingReaderExcel();

        Training readTraining = reader.read(path + fileName);

        compare(expectedTraining, readTraining);

    }

    private void compare(Training expectedTraining, Training actualTraining) {

        TrainingModule expectedModule = expectedTraining.getModuleById("1");
        TrainingModule actualModule   = actualTraining.getModuleById("1");
        compareModules(expectedModule, actualModule);

        expectedModule = expectedTraining.getModuleById("2");
        actualModule   = actualTraining.getModuleById("2");
        compareModules(expectedModule, actualModule);

        expectedModule = expectedTraining.getModuleById("3");
        actualModule   = actualTraining.getModuleById("3");
        compareModules(expectedModule, actualModule);

    }

    private void compareModules(TrainingModule expected, TrainingModule actual) {
        assertEquals(expected.getName(), actual.getName());
        List<TrainingTopic> expectedTopics = expected.getTopics();
        List<TrainingTopic> actualTopics   = actual.getTopics();
        compareTopicList(expectedTopics, actualTopics);

    }

    /**
     * We could compare the list directly, but then we would not see
     * which topic does not be equal
     * @param expected
     * @param actual
     */
    private void compareTopicList(List<TrainingTopic> expected, List<TrainingTopic> actual) {
        assertEquals(expected.size(), actual.size());
        for(TrainingTopic topic : expected){

            assertTrue("Topic not found: " + topic.name, actual.contains(topic));
            TrainingTopic actualTopic = actual.get(actual.indexOf(topic));
            compareTopics(topic, actualTopic);
        }
    }

    private void compareTopics(TrainingTopic expectedTopic, TrainingTopic actualTopic) {

        assertEquals(expectedTopic.name, actualTopic.name);
        assertEquals(expectedTopic.actions.size(), actualTopic.actions.size());
        assertEquals(expectedTopic.actions, actualTopic.actions);
    }


    private Training createComplexTraining() {
        Training training = new Training();
        training.setLanguage("en");

        TrainingModule module1 = new TrainingModule("1", "First Module");
        TrainingTopic topic = new TrainingTopic();
        topic.name  = "topic 1/1";
        TrainingAction action = new TrainingAction();
        action.depth = "slides";
        action.duration = "15";
        action.targetGroup = "QA";
        action.selected = "x";
        topic.getActions().add(action);

        action = new TrainingAction();
        action.depth = "demo";
        action.duration = "10";
        action.targetGroup = "QA";
        topic.getActions().add(action);
        module1.getTopics().add(topic);

        topic = new TrainingTopic();
        topic.name  = "topic 1/2";
        action = new TrainingAction();
        action.depth = "demo";
        action.duration = "10";
        action.targetGroup = "Dev";
        action.selected = "x";
        topic.getActions().add(action);
        module1.getTopics().add(topic);

        training.addModule(module1);

        TrainingModule module2 = new TrainingModule("2", "Second Module");
        topic = new TrainingTopic();
        topic.name  = "topic 2/1";
        action = new TrainingAction();
        action.depth = "demo";
        action.duration = "10";
        action.targetGroup = "Dev";
        action.selected = "x";
        topic.getActions().add(action);
        module2.getTopics().add(topic);

        training.addModule(module2);

        TrainingModule module3 = new TrainingModule("3", "Third Module");
        training.addModule(module3);
        return training;
    }

}
