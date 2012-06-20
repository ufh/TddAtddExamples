package com.qagile.tddatdd.agilefant.selenium.pages;

import com.qagile.tddatdd.config.UnsupportedDriverException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * User: ful
 * Date: 19.06.12
 * Time: 22:37
 */
public class ProductPage  extends GeneralPage {

    WebElement productContents;
    By CONTENTS = By.cssSelector("div#outerWrapper div#bodyWrapper div#productContents.structure-main-block");

    protected ProductPage() throws UnsupportedDriverException {
        super();
    }

    public boolean storyIsShown(String titel) {
        productContents = driver.findElement(CONTENTS);

        return productContents.getText().contains(titel);
    }
}
