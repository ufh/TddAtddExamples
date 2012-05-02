package com.qagile.configurator.excel;

import com.qagile.configurator.domain.Training;
import com.qagile.configurator.domain.TrainingAction;
import com.qagile.configurator.domain.TrainingModule;
import com.qagile.configurator.domain.TrainingTopic;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * User: ful
 * Date: 22.04.12
 * Time: 21:29
 */
public class CreateSheetTest {

    /**
     * In this first simple test a default training template is generated.
     * As we can't read a training yet, we can just check, if a file
     * is created
     *
     * @throws IOException
     */
    @Test
    public void testCreateSheet() throws IOException {
        
        String filename = "EmptyTrainingTest.xls";
        TrainingWriterExcel writer = new TrainingWriterExcel();
        
        writer.newTrainingFile(filename, "en");

        BufferedReader input =  new BufferedReader(new FileReader(filename));
        assert(input != null);

        input.close();

    }

    @Test
    public void createSheetWithModules() throws IOException {

        String filename = "TrainingWithModules.xls";

        TrainingWriterExcel writer = new TrainingWriterExcel();

        Training training = new Training();
        training.setLanguage("en");

        TrainingModule module1 = new TrainingModule("1", "First Module");
        training.addModule(module1);

        TrainingModule module2 = new TrainingModule("2", "Second Module");
        training.addModule(module2);

        TrainingModule module3 = new TrainingModule("3", "Third Module");
        training.addModule(module3);

        writer.newTrainingFile(filename, training);

        BufferedReader input =  new BufferedReader(new FileReader(filename));
        assert(input != null);

        input.close();

    }

    @Test
    public void createSheetWithModulesAndTopics() throws IOException {

        String filename = "TrainingWithModulesAndTopics.xls";

        TrainingWriterExcel writer = new TrainingWriterExcel();

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
        module1.getTopics().add(topic);

        topic = new TrainingTopic();
        topic.name  = "";
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

        writer.newTrainingFile(filename, training);

        BufferedReader input =  new BufferedReader(new FileReader(filename));
        assert(input != null);

        input.close();

    }

}
