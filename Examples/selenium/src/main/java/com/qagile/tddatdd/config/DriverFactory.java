package com.qagile.tddatdd.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.logging.Logger;

import static com.qagile.tddatdd.config.DriverTypes.*;

/**
 * Created by IntelliJ IDEA.
 * User: ful
 * Date: 20.03.12
 * Time: 22:31
 * To change this template use File | Settings | File Templates.
 */


public class DriverFactory {

    private static Logger logger = Logger.getLogger("com.qagile.tddatdd.config");
    
    /**
     * Returns a supported driver.
     * To use Chrome, you have to add the dependency to maven, or download the driver and
     * add it to the class path
     * @param type
     * @return a driver to be used in Selenium RC
     * @throws UnsupportedDriverException
     */
    public static WebDriver getDriver(DriverTypes type) throws UnsupportedDriverException {

        logger.info("Open Driver of Type: " + type);
        switch (type) {
            case FIREFOX:
                return new FirefoxDriver();
            case CHROME:
                System.setProperty("webdriver.chrome.driver",SeleniumProperties.pathToChromeDriver);
                return new ChromeDriver();
            case IE:
                DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();  ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                return new InternetExplorerDriver();
            default:
                throw new UnsupportedDriverException(type + " is not supported. Choose: FIREFOX, CHROME or IE");
        }
    }
}
