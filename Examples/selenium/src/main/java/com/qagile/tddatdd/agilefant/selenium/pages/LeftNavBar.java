package com.qagile.tddatdd.agilefant.selenium.pages;

import com.qagile.tddatdd.config.UnsupportedDriverException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by IntelliJ IDEA.
 * User: ful
 * Date: 23.03.12
 * Time: 14:40
 * To change this template use File | Settings | File Templates.
 */
public class LeftNavBar extends GeneralPage {
    
    private WebElement root;
    private static By ROOT;

    public MenuMyAssignments menuMyAssignments;
    public MenuProducts menuProducts = new MenuProducts();
    public MenuAdmin menuAdmin;

    protected LeftNavBar() throws UnsupportedDriverException {
        super();
    }
}
