package com.qagile.tddatdd.agilefant.selenium.pages;

import com.qagile.tddatdd.agilefant.domain.Story;
import com.qagile.tddatdd.config.UnsupportedDriverException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

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
        WebElement dialogWindow = driver.findElement(By.cssSelector("div.ui-dialog div.ui-dialog-content form"));
        dialogWindow.submit();
        
        return new StartPage();
    }

    public void compileFormular(Story story) {
        //enter mandatory data for the story
        // id s are generated - there is no chance to identify the elements!
        List<WebElement> list = driver.findElements(By.cssSelector("input.dynamics-editor-element"));
        WebElement editName = list.get(0);
        WebElement editBacklog = list.get(1);
        WebElement editStoryPoints = list.get(2);
        WebElement editDescription = driver.findElement(By.cssSelector("div.wysiwyg iframe#IFrame.dynamics-editor-element"));

        editName.sendKeys(story.title);
        editBacklog.sendKeys(story.backlog.title);
        editStoryPoints.sendKeys(story.estimation);
        editDescription.sendKeys(story.description);
    }
}
