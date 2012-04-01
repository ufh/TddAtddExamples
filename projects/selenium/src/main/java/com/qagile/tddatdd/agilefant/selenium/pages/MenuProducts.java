package com.qagile.tddatdd.agilefant.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by IntelliJ IDEA.
 * User: ful
 * Date: 28.03.12
 * Time: 18:27
 * To change this template use File | Settings | File Templates.
 */
public class MenuProducts {

    private WebElement products;
    private static By PRODUCTS = By.cssSelector("h3#menuAccordion-products.ui-accordion-header a");

    public MenuProducts(){

    }
}
