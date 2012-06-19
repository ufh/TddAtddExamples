package com.qagile.tddatdd.agilefant.selenium.pages;

import com.qagile.tddatdd.config.DriverFactory;
import com.qagile.tddatdd.config.DriverTypes;
import com.qagile.tddatdd.config.SeleniumProperties;
import com.qagile.tddatdd.config.UnsupportedDriverException;
import org.openqa.selenium.WebDriver;

import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: ful
 * Date: 23.03.12
 * Time: 14:53
 * To change this template use File | Settings | File Templates.
 */
public class GeneralPage {
    
    protected static WebDriver driver;

    protected Logger logger = Logger.getLogger("com.qagile.tddatdd.agilefant.selenium.pages");

    protected GeneralPage() throws UnsupportedDriverException {
        if (driver == null){
            setDriver(SeleniumProperties.browser);
        }
    }

    public static void setDriver(DriverTypes browser) throws UnsupportedDriverException {

        driver = DriverFactory.getDriver(browser);
    }

    public static WebDriver getWebDriver(){
        return driver;
    }
    
    public static void start() {
        driver.navigate().to(SeleniumProperties.host + SeleniumProperties.app);
    }

    public static void stop(){
        driver.close();
    }
}
