/*
это версия ТС без считывания данных из файла XLS - данные берутся из готовых массивов
 */

//"d:/Java/Driver_for_Selenium/geckodriver.exe" - это мой пусть к драйверу WireFox
//d:/Java/Driver_for_Selenium/chromedriver.exe- это мой пусть к драйверу Хром

package com.academy.automationpractice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class AuthTestsHomeTask_v2 {
    private String commonProperties = "D:\\Java\\Program\\javapart\\src\\main\\resources\\common.properties";
    private String automationpracticeProperties = "D:\\Java\\Program\\javapart\\src\\main\\resources\\automationpractice.properties";
    private String errorMessageCssLocator = "#center_column > div.alert.alert-danger > ol > li";
    private WebDriver driver;
    private String baseUrl="http://automationpractice.com/index.php";
    private String geckoDriver;
    private String chromeDriver;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        initDrivers();

    //    baseUrl = "http://automationpractice.com/index.php";
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    private void initDrivers() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(commonProperties));
        } catch (IOException e) {
            e.printStackTrace();
        }
       System.setProperty("webdriver.chrome.driver", properties.getProperty("chrome.driver"));
       //System.setProperty("webdriver.gecko.driver", properties.getProperty("gecko.driver"));
        driver = new ChromeDriver();
       // driver = new FirefoxDriver();

    }

    @Test
    public void testUntitledTestCase() throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.linkText("Sign in")).click();

        String[] logins = {"","qwerty"};
        String[] passwords = {"123","qwerty"};
        String[] expectedMessages = {"An email address required.","Invalid email address."};

        for (int i =0; i< logins.length; i++) {

            // Заполняем логин и пароль
            WebElement loginField = driver.findElement(By.id("email"));
            loginField.click();
            loginField.clear();
            loginField.sendKeys(logins[i]);

            WebElement passwdField = driver.findElement(By.id("passwd"));
            passwdField.click();
            passwdField.clear();
            passwdField.sendKeys(passwords[i]);

            WebElement submitField = driver.findElement(By.id("SubmitLogin"));
            submitField.click();

            WebElement webElementWithErrorMessage = driver.findElement(By.cssSelector(errorMessageCssLocator));
            String actualErrorMessage = webElementWithErrorMessage.getText();
            try {
                assertEquals(actualErrorMessage, expectedMessages[i]);
                System.out.println(expectedMessages[i]);
               } catch (Error e) {
                verificationErrors.append(e.toString());
            }

            //            driver.findElement(By.linkText("Sign out")).click();

        }

      } //это конец ТС


    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
}