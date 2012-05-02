package com.qagile.tddatdd.agilefant.selenium.pages;

import com.qagile.tddatdd.config.UnsupportedDriverException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by IntelliJ IDEA.
 * User: ful
 * Date: 28.03.12
 * Time: 17:58
 * To change this template use File | Settings | File Templates.
 */
public class MenuMyAssignments extends GeneralPage {

    private WebElement myAssignments;
    private static By MY_ASSIGNMENTS  = By.cssSelector("h3#menuAccordion-myAssignments.ui-accordion-header a");;

    public MenuMyAssignments() throws UnsupportedDriverException {
        super();
        myAssignments = driver.findElement(MY_ASSIGNMENTS);
    }
}
