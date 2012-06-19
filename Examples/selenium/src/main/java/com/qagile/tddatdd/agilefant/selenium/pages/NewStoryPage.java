package com.qagile.tddatdd.agilefant.selenium.pages;

import com.qagile.tddatdd.agilefant.domain.Story;
import com.qagile.tddatdd.config.UnsupportedDriverException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: ful
 * Date: 23.03.12
 * Time: 14:48
 * To change this template use File | Settings | File Templates.
 */
public class NewStoryPage extends GeneralPage {


    public NewStoryPage() throws UnsupportedDriverException {
        super();
    }

    ////////////////////////////////////////////////////
    // TODO merge with compileFormular(Story)
    // This version doesn't work !!!
    public void compileFormular(String backlog, String title, String estimation, String description) {

        //enter mandatory data for the story
        // id s are generated - there is no chance to identify the elements!
        List<WebElement> list = driver.findElements(By.cssSelector("input.dynamics-editor-element"));
        WebElement editName = list.get(0);
        WebElement editBacklog = list.get(1);
        WebElement editStoryPoints = list.get(2);
        WebElement editDescription = driver.findElement(By.cssSelector("div.wysiwyg iframe#IFrame.dynamics-editor-element"));

        editName.sendKeys(title);
        editBacklog.sendKeys(backlog);
        editStoryPoints.sendKeys(estimation);
        editDescription.sendKeys(description);
    }

    public StartPage submit() throws UnsupportedDriverException {
        List<WebElement> buttons = driver.findElements(By.cssSelector("div.ui-dialog div.ui-dialog-buttonpane button.ui-button"));
        if (buttons.get(0).getText().equals("Ok")){
            buttons.get(0).click();
        }else{
            buttons.get(1).click();
        }
        return new StartPage();
    }

    public void compileFormular(Story story) throws InterruptedException {
        //enter mandatory data for the story
        // id s are generated - there is no chance to identify the elements!
        WebElement editDescription = driver.findElement(By.cssSelector("div.wysiwyg iframe#IFrame.dynamics-editor-element"));
        List<WebElement> list = driver.findElements(By.cssSelector("input.dynamics-editor-element"));
        WebElement editName = list.get(0);
        WebElement editStoryPoints = list.get(2);
        editName.sendKeys(story.title);
        editStoryPoints.sendKeys(story.estimation);
        editDescription.sendKeys(story.description);

        WebElement editBacklog = driver.findElement(By.cssSelector("input.dynamics-editor-element.ui-autocomplete-input"));
        editBacklog.click();
        editBacklog.sendKeys(story.backlog.title);

        WebDriverWait wait = new WebDriverWait(driver, 10);
        logger.info("Waiting for autocomplete suggestions");
        //WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li.ui-menu-item")));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("html body ul.ui-autocomplete li.ui-menu-item a.ui-corner-all")));

        logger.info("Element found: " + (element != null));
        element.click();
//        logger.info("Element clicked...");
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("li.ui-menu-item")));

    }
}
