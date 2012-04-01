package com.qagile.tddatdd.agilefant.selenium.pages;

import com.qagile.tddatdd.config.SeleniumProperties;
import com.qagile.tddatdd.config.UnsupportedDriverException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by IntelliJ IDEA.
 * User: ful
 * Date: 20.03.12
 * Time: 23:26
 * To change this template use File | Settings | File Templates.
 */
public class LoginPage extends GeneralPage {

    private static By INPUT_USER = By.cssSelector("input#username");
    private static By INPUT_PWD  = By.cssSelector("input[name=j_password]");
    private static By BTN_LOGIN  = By.cssSelector("input[type=submit]");

    WebElement editUsername;
    WebElement editPwd;
    WebElement btnLogin;

    public LoginPage() throws UnsupportedDriverException {
        super();
        // check in constructor, that the page is complete
        editUsername = driver.findElement(INPUT_USER);
        editPwd      = driver.findElement(INPUT_PWD);
        btnLogin     = driver.findElement(BTN_LOGIN);

    }

    public StartPage login(String user, String password) throws UnsupportedDriverException {
        // Find the elements again - Selenium may have forgotten them :-)
        editUsername = driver.findElement(INPUT_USER);
        editPwd      = driver.findElement(INPUT_PWD);
        btnLogin     = driver.findElement(BTN_LOGIN);

        // Fill in username and data
        editUsername.sendKeys(SeleniumProperties.adminName);
        editPwd.sendKeys(SeleniumProperties.adminPwd);

        // and submit
        btnLogin.click();
        return new StartPage();
    }
}
