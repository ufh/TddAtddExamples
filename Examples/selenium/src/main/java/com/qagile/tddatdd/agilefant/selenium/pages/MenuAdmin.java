package com.qagile.tddatdd.agilefant.selenium.pages;

import com.qagile.tddatdd.config.UnsupportedDriverException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by IntelliJ IDEA.
 * User: ful
 * Date: 28.03.12
 * Time: 18:23
 * To change this template use File | Settings | File Templates.
 */
public class MenuAdmin extends GeneralPage  {

    private WebElement admin;
    private static By ADMIN  = By.cssSelector("h3#menuAccordion-administration.ui-accordion-header a");

    public MenuAdmin() throws UnsupportedDriverException {
        super();
        admin = driver.findElement(ADMIN);
    }

}
