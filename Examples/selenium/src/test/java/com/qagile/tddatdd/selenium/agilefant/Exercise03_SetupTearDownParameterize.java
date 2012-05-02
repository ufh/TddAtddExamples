package com.qagile.tddatdd.selenium.agilefant;

import com.qagile.tddatdd.config.DriverTypes;
import com.qagile.tddatdd.config.SeleniumProperties;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import static com.qagile.tddatdd.config.DriverTypes.*;
import static junit.framework.Assert.fail;

/**
 * Created by IntelliJ IDEA.
 * User: ful
 * Date: 19.03.12
 * Time: 18:54
 * To change this template use File | Settings | File Templates.
 */
@RunWith(value = Parameterized.class)
public class Exercise03_SetupTearDownParameterize {

    private final static Logger logger = Logger.getLogger("com.qagile.tddatdd.selenium.agilefant");

    private String host = "http://localhost:8080";
    private String app = "/agilefant";
    private String adminName = "admin";
    private String adminPwd = "secret";

    private String userName = "agilefant";
    private String userPwd  = "agilefant";

    private WebDriver driver;
    private DriverTypes browser;

    public Exercise03_SetupTearDownParameterize(DriverTypes browser){
        this.browser = browser;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] { { FIREFOX },{ CHROME } };
        return Arrays.asList(data);
    }

    @Before
    public void setUp(){
        // Create a new instance of the Firefox driver
        // Notice that the remainder of the code relies on the interface,
        // not the implementation.
        logger.info("Browser: " + browser);
        switch (browser) {
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case CHROME:
                System.setProperty("webdriver.chrome.driver", SeleniumProperties.pathToChromeDriver);
                driver = new ChromeDriver();
                break;
            default:
                fail("Not supported selenium driver !!!");
            case IE:
                break;
        }


        driver.navigate().to(host + app);

        ////////////////////////////////////////////////////////////////////////////////////
        // login
        //
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
        
    }

    @Test
    public void testCreateProduct(){


        //////////////////////////////////////////////////////////////////////////////////////
        //now we are logged in, let's open a new product
        //click createNew
        //WebElement linkNew = driver.findElement(By.cssSelector("a[id='createNewMenuLink']"));
        WebElement linkNew = driver.findElement(By.id("createNewMenuLink"));
        linkNew.click();

        //choose Product
        //WebElement selectionNewProduct = driver.findElement(By.cssSelector("div#logoutDiv a#createNewMenuLink"));
        WebElement selectionNewProduct = driver.findElement(By.id("createNewProduct"));
        selectionNewProduct.click();

        //enter name and description
        WebElement newProductTitle = driver.findElement(By.cssSelector("input[type=text].dynamics-editor-element"));
        newProductTitle.sendKeys("Selenium created Product #01");
        WebElement newProductDescription = driver.findElement(By.cssSelector("div.wysiwyg iframe#IFrame.dynamics-editor-element"));
        newProductDescription.sendKeys("Product #01 is the coolest product ever!");

        //submit
        //WebElement btnOk = driver.findElement(By.linkText("Ok"));
        WebElement dialogWindow = driver.findElement(By.cssSelector("div.ui-dialog div.ui-dialog-content form"));
        dialogWindow.submit();

        //open product



    }

    @Test
    public void testCreateStory(){


        //////////////////////////////////////////////////////////////////////////////////////
        //now we are logged in, let's open a new product
        //click createNew
        //WebElement linkNew = driver.findElement(By.cssSelector("a[id='createNewMenuLink']"));
        WebElement linkNew = driver.findElement(By.id("createNewMenuLink"));
        linkNew.click();

        //choose Product
        //WebElement selectionNewProduct = driver.findElement(By.cssSelector("div#logoutDiv a#createNewMenuLink"));
        WebElement selectionNewProduct = driver.findElement(By.id("createNewStory"));
        selectionNewProduct.click();

        //enter mandatory data for the story
        // id s are generated - there is no chance to identify the elements!
        List<WebElement> list = driver.findElements(By.cssSelector("input.dynamics-editor-element"));
        WebElement editName = list.get(0);
        WebElement editBacklog = list.get(1);
        WebElement editStoryPoints = list.get(2);
        WebElement editDescription = driver.findElement(By.cssSelector("div.wysiwyg iframe#IFrame.dynamics-editor-element"));

        editName.sendKeys("Name of the Story");
        editBacklog.sendKeys("ProductTest01");
        editStoryPoints.sendKeys("13");
        editDescription.sendKeys("Super urgent story!!!!");

        //submit
        //WebElement btnOk = driver.findElement(By.linkText("Ok"));
        WebElement dialogWindow = driver.findElement(By.cssSelector("div.ui-dialog div.ui-dialog-content form"));
        dialogWindow.submit();

    }

    @After
    public void cleanUp(){
        ///////////////////////////////////////////////////////////////////////////////////////
        // at the end log out and close selenium drivers
        WebElement linkLogout = driver.findElement(By.cssSelector("a[href='j_spring_security_logout?exit=Logout']"));
        linkLogout.click();

        System.out.println("Page title is: " + driver.getTitle());
        logger.info("Title is " + driver.getTitle());

        driver.close();
        driver.quit();

    }
}
