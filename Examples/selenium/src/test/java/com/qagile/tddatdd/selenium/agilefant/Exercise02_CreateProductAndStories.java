package com.qagile.tddatdd.selenium.agilefant;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: ful
 * Date: 19.03.12
 * Time: 18:54
 * To change this template use File | Settings | File Templates.
 */
public class Exercise02_CreateProductAndStories {

    private final static Logger logger = Logger.getLogger("com.qagile.tddatdd.selenium.agilefant");

    private String host = "http://localhost:8080";
    private String app = "/agilefant";
    private String adminName = "admin";
    private String adminPwd = "secret";

    private String userName = "agilefant";
    private String userPwd  = "agilefant";


    @Test
    public void testCreateProduct(){

        // Create a new instance of the Firefox driver
        // Notice that the remainder of the code relies on the interface,
        // not the implementation.
        WebDriver driver = new FirefoxDriver();

        driver.navigate().to(host + app);

        ////////////////////////////////////////////////////////////////////////////////////
        // login
        //
        // Find the text input element by its name
        WebElement editUsername = driver.findElement(By.cssSelector("input#username"));
        WebElement editPwd      = driver.findElement(By.cssSelector("input[name=j_password]"));
        WebElement btnLogin     = driver.findElement(By.cssSelector("input[type=submit]"));

        // Enter nomething to search for
        editUsername.sendKeys(adminName);
        editPwd.sendKeys(adminPwd);

        // Now submit the form. WebDriver will find the form for us from the element
        //editUsername.submit();
        btnLogin.click();

        //////////////////////////////////////////////////////////////////////////////////////
        //now we are logged in, let's open a new product
        WebElement linkNew = driver.findElement(By.id("createNewMenuLink"));
        linkNew.click();

        //choose Product
        //WebElement selectionNewProduct = driver.findElement(By.cssSelector("div#logoutDiv a#createNewMenuLink"));
        WebElement selectionNewProduct = driver.findElement(By.id("createNewProduct"));
        selectionNewProduct.click();

        //enter name and description
        WebElement newProductTitle = driver.findElement(By.cssSelector("input[type=text].dynamics-editor-element"));
        newProductTitle.sendKeys("Selenium created Product #01 (Exercise 2)");
        WebElement newProductDescription = driver.findElement(By.cssSelector("div.wysiwyg iframe#IFrame.dynamics-editor-element"));
        newProductDescription.sendKeys("Product #01 is the coolest product ever!");

        //submit
        List<WebElement> buttons = driver.findElements(By.cssSelector("div.ui-dialog div.ui-dialog-buttonpane button.ui-button"));
        if (buttons.get(0).getText().equals("Ok")){
            buttons.get(0).click();
        }else{
            buttons.get(1).click();
        }


        ///////////////////////////////////////////////////////////////////////////////////////
        // at the end log out and close selenium drivers
        WebElement linkLogout = driver.findElement(By.cssSelector("a[href='j_spring_security_logout?exit=Logout']"));
        linkLogout.click();

        System.out.println("Page title is: " + driver.getTitle());
        logger.info("Title is " + driver.getTitle());

        driver.close();
        driver.quit();

    }

    @Test
    public void testCreateStory(){

        // Create a new instance of the Firefox driver
        // Notice that the remainder of the code relies on the interface,
        // not the implementation.
        WebDriver driver = new FirefoxDriver();

        driver.navigate().to(host + app);

        ////////////////////////////////////////////////////////////////////////////////////
        // login
        //
        // Find the text input element by its name
        WebElement editUsername = driver.findElement(By.cssSelector("input#username"));
        WebElement editPwd      = driver.findElement(By.cssSelector("input[name=j_password]"));
        WebElement btnLogin     = driver.findElement(By.cssSelector("input[type=submit]"));

        // set user and pwd
        editUsername.sendKeys(adminName);
        editPwd.sendKeys(adminPwd);

        // Now submit the form. WebDriver will find the form for us from the element
        //editUsername.submit();
        btnLogin.click();

        //////////////////////////////////////////////////////////////////////////////////////
        //now we are logged in, let's open a new product
        WebElement linkNew = driver.findElement(By.id("createNewMenuLink"));
        linkNew.click();

        //choose Product
        //WebElement selectionNewProduct = driver.findElement(By.cssSelector("div#logoutDiv a#createNewMenuLink"));
        WebElement selectionNewProduct = driver.findElement(By.id("createNewStory"));
        selectionNewProduct.click();

        //enter mandatory data for the story
        // id s are generated - there is no chance to identify the elements!
        WebElement editDescription = driver.findElement(By.cssSelector("div.wysiwyg iframe#IFrame.dynamics-editor-element"));
        List<WebElement> list = driver.findElements(By.cssSelector("input.dynamics-editor-element"));
        WebElement editName = list.get(0);
        WebElement editStoryPoints = list.get(2);
        // set fields
        editName.sendKeys("Story Exercise #2");
        editStoryPoints.sendKeys("13");
        editDescription.sendKeys("Super urgent story!!!!");
        // due to auto completion backlog filed needs special treatment..
        WebElement editBacklog = driver.findElement(By.cssSelector("input.dynamics-editor-element.ui-autocomplete-input"));
        editBacklog.click();
        editBacklog.sendKeys("Selenium created Product #01 (Exercise 2)");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        logger.info("Waiting for autocomplete suggestions");
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("html body ul.ui-autocomplete li.ui-menu-item a.ui-corner-all")));
        element.click();

        //submit by clicking ok
        List<WebElement> buttons = driver.findElements(By.cssSelector("div.ui-dialog div.ui-dialog-buttonpane button.ui-button"));
        if (buttons.get(0).getText().equals("Ok")){
            buttons.get(0).click();
        }else{
            buttons.get(1).click();
        };

        ///////////////////////////////////////////////////////////////////////////////////////
        // at the end log out and close selenium drivers
        WebElement linkLogout = driver.findElement(By.cssSelector("a[href='j_spring_security_logout?exit=Logout']"));
        linkLogout.click();

        System.out.println("Page title is: " + driver.getTitle());
        logger.info("Title is " + driver.getTitle());

        driver.close();
        driver.quit();

    }


}
