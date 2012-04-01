package com.qagile.tddatdd.selenium.agilefant;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: ful
 * Date: 13.03.12
 * Time: 21:55
 * To change this template use File | Settings | File Templates.
 */
public class Exercise01_GettingStarted {

    private final static Logger logger = Logger.getLogger("com.qagile.tddatdd.selenium.agilefant");
    
    private String host = "http://localhost:8080";
    private String app = "/agilefant";
    private String adminName = "admin";
    private String adminPwd = "secret";

    private String userName = "agilefant";
    private String userPwd  = "agilefant";

    @Test
    public void testJustOpenPage(){

        WebDriver driver = new FirefoxDriver();

        driver.navigate().to(host + app);

        // Check if opened / user/pwd fields can be found
        WebElement editUsername = driver.findElement(By.cssSelector("input#username"));
        WebElement editPwd      = driver.findElement(By.cssSelector("input[name=j_password]"));
        WebElement btnLogin     = driver.findElement(By.cssSelector("input[type=submit]"));

        driver.close();
        //driver.quit();
    }

    @Test
    public void testOpenLoginLogout(){

        // Create a new instance of the Firefox driver
        // Notice that the remainder of the code relies on the interface,
        // not the implementation.
        WebDriver driver = new FirefoxDriver();

        driver.navigate().to(host + app);

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

        WebElement linkLogout = driver.findElement(By.cssSelector("a[href='j_spring_security_logout?exit=Logout']"));
        linkLogout.click();

        System.out.println("Page title is: " + driver.getTitle());
        logger.info("Title is " + driver.getTitle());

        driver.close();
        //driver.quit();

    }
}
