/*
запуск через TestNG
считывание конфигурации - никаких путей и данных не должно быть в коде - все в конфиг
считывание данных из эксель
 */
package com.academy.automationpractice;

import com.academy.automationpractice.model.AuthData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class AuthTests_v2 {
    private WebDriver driver;
    private String baseUrl;
    private static String pathExcData;
    private String errorMessageCssLocator = "#center_column > div.alert.alert-danger > ol > li";

    private StringBuffer verificationErrors = new StringBuffer();

    @Parameters("browser")
    @BeforeClass(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser) throws Exception {
        initDrivers(browser);
        //получение путей из automationpractice.properties
        pathExcData=getPathExcData();

        baseUrl = "http://automationpractice.com/index.php";
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    private static String getPathExcData(){
        // Cчитывание пути к файлу конфигурации: 'automationpractice.common', где находится путь к xls
        String commonProperties = System.getProperty("automationpractice.cfg");
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(commonProperties));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties.getProperty("automation.auth.data.exc");
    }

    private void initDrivers(String browser) {
        // Здесь читаю пусть к файлу конфигурации
        String commonProperties = System.getProperty("common.cfg");
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(commonProperties));
        } catch (IOException e) {
            e.printStackTrace();
        }

        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", properties.getProperty("chrome.driver"));
                driver = new ChromeDriver();
                break;

            case "firefox":
                System.setProperty("webdriver.gecko.driver", properties.getProperty("gecko.driver"));
                driver = new FirefoxDriver();
                break;

            default:
                throw new IllegalArgumentException("Unknown browser " + browser);
        }
    }

    @Test(dataProvider = "authDataProvider")
    public void testUntitledTestCase(String login, String password, String expectedMessage) throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.linkText("Sign in")).click();

        // Заполняем форму логин/пароль
        WebElement loginField = driver.findElement(By.id("email"));
        loginField.click();
        loginField.clear();
        loginField.sendKeys(login);

        driver.findElement(By.id("passwd")).click();
        driver.findElement(By.id("passwd")).clear();
        driver.findElement(By.id("passwd")).sendKeys(password);
        driver.findElement(By.id("SubmitLogin")).click();

        WebElement webElementWithErrorMessage = driver.findElement(By.cssSelector(errorMessageCssLocator));
        String actualErrorMessage = webElementWithErrorMessage.getText();
        try {
            assertEquals(actualErrorMessage, expectedMessage);
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    private List<AuthData> readTestData() {
        List<AuthData> testDataList = new ArrayList<>();

        // Прочитать из excel файла
        return testDataList;
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    // TODO FROM EXCEL
    @DataProvider(name="authDataProvider")
    public Object[][] authDataProvider() {
        return new Object[][] {
                {"", "123", "An email address required."},
                {"qwertyLogin", "qwertyPassword", "Invalid email address."}
        };
    }
}