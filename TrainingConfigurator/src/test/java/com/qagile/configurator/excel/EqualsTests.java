package com.qagile.configurator.excel;

import com.qagile.configurator.domain.TrainingAction;
import com.qagile.configurator.domain.TrainingTopic;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * User: ful
 * Date: 14.05.12
 * Time: 22:54
 *
 * Tests the overridden equals methods for comparison of the domain objects
 */
public class EqualsTests {

    @Test
    public void actionEqualsTest(){

        // positive case
        TrainingAction firstAction = createTestAction("demo", "2.5", "false", "QA");
        TrainingAction secondAction = createTestAction("demo", "2.5", "false", "QA");
        assertTrue(secondAction.equals(firstAction));

        // negative cases
        firstAction = createTestAction("demo", "2.5", "false", "QA");
        secondAction = createTestAction("demoXXX", "2.5", "false", "QA");
        assertFalse(secondAction.equals(firstAction));

        firstAction = createTestAction("demo", "2.5", "false", "QA");
        secondAction = createTestAction("demo", "2.5XXX", "false", "QA");
        assertFalse(secondAction.equals(firstAction));

        firstAction = createTestAction("demo", "2.5", "false", "QA");
        secondAction = createTestAction("demo", "2.5", "falseXXX", "QA");
        assertFalse(secondAction.equals(firstAction));

        firstAction = createTestAction("demo", "2.5", "false", "QA");
        secondAction = createTestAction("demo", "2.5", "false", "QAxxxx");
        assertFalse(secondAction.equals(firstAction));

    }

    @Test
    public void actionListEqualsTest(){

        List<TrainingAction> firstActionList = new ArrayList<TrainingAction>();
        firstActionList.add(createTestAction("#1", "2.5", "false", "QA"));
        firstActionList.add(createTestAction("#2", "2.5", "false", "QA"));
        firstActionList.add(createTestAction("#3", "2.5", "false", "QA"));
        firstActionList.add(createTestAction("#4", "2.5", "false", "QA"));

        List<TrainingAction> secondActionList = new ArrayList<TrainingAction>();
        secondActionList.add(createTestAction("#1", "2.5", "false", "QA"));
        secondActionList.add(createTestAction("#2", "2.5", "false", "QA"));
        secondActionList.add(createTestAction("#3", "2.5", "false", "QA"));
        secondActionList.add(createTestAction("#4", "2.5", "false", "QA"));

        for (TrainingAction action : firstActionList){
            assertTrue("Action not found: " + action.depth, secondActionList.contains(action));
            assertEquals("action: " + action.depth, action, secondActionList.get(secondActionList.indexOf(action)));
        }

        // try simpler check
        assertEquals(firstActionList, secondActionList);
    }

    @Test
    public void testTrainingTopicEquals(){

        List<TrainingAction> firstActionList = new ArrayList<TrainingAction>();
        firstActionList.add(createTestAction("#1", "2.5", "false", "QA"));
        firstActionList.add(createTestAction("#2", "2.5", "false", "QA"));
        firstActionList.add(createTestAction("#3", "2.5", "false", "QA"));
        firstActionList.add(createTestAction("#4", "2.5", "false", "QA"));
        TrainingTopic firstTopic  = createTrainingTopic("equals", firstActionList);
        TrainingTopic secondTopic = createTrainingTopic("equals", firstActionList);

        assertEquals(firstTopic, secondTopic);
    }


    private TrainingAction createTestAction(String depth, String duration, String selected, String group) {
        TrainingAction action = new TrainingAction();

        action.depth  = depth;
        action.duration  = duration;
        action.selected  = selected;
        action.targetGroup  = group;
        return action;
    }

    private TrainingTopic createTrainingTopic(String name, List<TrainingAction> actionList){

        TrainingTopic topic = new TrainingTopic();
        topic.name = name;
        topic.actions = actionList;

        return topic;
    }

}
