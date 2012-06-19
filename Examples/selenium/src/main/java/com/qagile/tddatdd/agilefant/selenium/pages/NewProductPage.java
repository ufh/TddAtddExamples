package com.qagile.tddatdd.agilefant.selenium.pages;

import com.qagile.tddatdd.agilefant.domain.Product;
import com.qagile.tddatdd.config.UnsupportedDriverException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ful
 * Date: 23.03.12
 * Time: 14:48
 * To change this template use File | Settings | File Templates.
 */
public class NewProductPage {
    
    WebDriver driver;
    
    public NewProductPage(WebDriver driver){
        this.driver = driver;
    }

    public void compileFormular(String title, String description) {

        WebElement newProductTitle = driver.findElement(By.cssSelector("input[type=text].dynamics-editor-element"));
        newProductTitle.sendKeys(title);
        WebElement newProductDescription = driver.findElement(By.cssSelector("div.wysiwyg iframe#IFrame.dynamics-editor-element"));
        newProductDescription.sendKeys(description);
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

    public void compileFormular(Product product) {
        WebElement newProductTitle = driver.findElement(By.cssSelector("input[type=text].dynamics-editor-element"));
        newProductTitle.sendKeys(product.title);
        WebElement newProductDescription = driver.findElement(By.cssSelector("div.wysiwyg iframe#IFrame.dynamics-editor-element"));
        newProductDescription.sendKeys(product.description);
    }
}
