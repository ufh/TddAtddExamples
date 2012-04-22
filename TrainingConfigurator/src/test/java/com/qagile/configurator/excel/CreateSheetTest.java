package com.qagile.configurator.excel;

import org.junit.Test;

import java.io.IOException;

/**
 * User: ful
 * Date: 22.04.12
 * Time: 21:29
 */
public class CreateSheetTest {

    @Test
    public void testCreateSheet() throws IOException {
        
        Training training = new Training();
        String filename = "CreateSheetTest.xls";
        TrainingWriterExcel writer = new TrainingWriterExcel();
        
        writer.newTrainingFile(filename);

    }


}
