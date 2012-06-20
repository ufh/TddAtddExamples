package com.qagile.tddatdd.agilefant.selenium.pages;

import com.qagile.tddatdd.config.UnsupportedDriverException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ful
 * Date: 28.03.12
 * Time: 18:27
 * To change this template use File | Settings | File Templates.
 */
public class MenuProducts extends GeneralPage {

    private WebElement btnProducts;
    private static By BTN_PRODUCTS = By.cssSelector("h3#menuAccordion-products.ui-accordion-header a");

    protected MenuProducts() throws UnsupportedDriverException {
        super();
    }

    public void openMenu(){
        btnProducts = driver.findElement(BTN_PRODUCTS);
        btnProducts.click();
    }

    /**
     * Use this method if you are sure, there is only one product with this name
     * @param productTitle
     * @return
     * @throws UnsupportedDriverException
     */
    public ProductPage openProductPage(String productTitle) throws UnsupportedDriverException {

        openMenu();
        WebElement linkProduct = btnProducts.findElement(By.linkText("productTitle"));
        linkProduct.click();
        return new ProductPage();
    }

    /**
     * Opens a Product Page of product with title productTitle.
     * If more than one product have this name opens #i (starts at 0) ...
     * @param productTitle
     * @return ProductPage
     * @throws UnsupportedDriverException
     */
    public ProductPage openProductPage(String productTitle, int i) throws UnsupportedDriverException {

        openMenu();
        List<WebElement> linkProducts = btnProducts.findElements(By.linkText("productTitle"));
        linkProducts.get(i).click();
        return new ProductPage();
    }
}
