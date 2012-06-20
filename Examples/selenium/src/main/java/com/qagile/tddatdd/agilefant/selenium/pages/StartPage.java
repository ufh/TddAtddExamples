package com.qagile.tddatdd.agilefant.selenium.pages;

import com.qagile.tddatdd.config.UnsupportedDriverException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by IntelliJ IDEA.
 * User: ful
 * Date: 23.03.12
 * Time: 14:39
 * To change this template use File | Settings | File Templates.
 */
public class StartPage extends GeneralPage {

    // Sub pages
    LeftNavBar leftNavBar = new LeftNavBar();
    ContentTabs contentTabs = new ContentTabs();
    Footer footer = new Footer();
    TopNavLinks topNavLinks = new TopNavLinks();
    
    // Gui Elements
    By LINK_NEW = By.id("createNewMenuLink");
    WebElement linkNew;
    
    By LINK_NEW_PRODUCT = By.id("createNewProduct");
    WebElement selectionNewProduct;
    
    By LINK_LOGOUT = By.cssSelector("a[href='j_spring_security_logout?exit=Logout']");
    WebElement linkLogout;

    public StartPage() throws UnsupportedDriverException {
        super();
    }

    public NewProductPage navigateToNewProduct() {

        linkNew = driver.findElement(LINK_NEW);
        linkNew.click();

        //choose Product
        selectionNewProduct = driver.findElement(LINK_NEW_PRODUCT);
        selectionNewProduct.click();
        
        return new NewProductPage(driver);
    }

    public NewStoryPage navigateToNewStory() throws UnsupportedDriverException {

        //now we are logged in, let's open a new product
        //click createNew
        WebElement linkNew = driver.findElement(By.id("createNewMenuLink"));
        linkNew.click();

        //choose Product
        WebElement selectionNewProduct = driver.findElement(By.id("createNewStory"));
        selectionNewProduct.click();

        return new NewStoryPage();
    }

    public ProductPage navigateToProduct(String productTitle, int i) throws UnsupportedDriverException {

        return leftNavBar.menuProducts.openProductPage(productTitle, i);
    }

    public void logout() {
        linkLogout = driver.findElement(LINK_LOGOUT);
        linkLogout.click();
    }
}
