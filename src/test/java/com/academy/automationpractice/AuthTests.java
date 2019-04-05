//считывает конфиг и данные из xls
//1) запускается тест на данных, которые получаем из excel файла
//2) путь к excel файлу прописать в конфигурации, automationpractice.properties
//3) путь к конфигурации automationpractice.properties пробросить через Maven - pom.xml (по аналогии с common.properties), т.е. не прописывать никаких путей в коде


package com.academy.automationpractice;

import com.academy.automationpractice.model.AuthData;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class AuthTests {
    private WebDriver driver;
    private String baseUrl;
    private static String pathExcData;

    private String errorMessageCssLocator = "#center_column > div.alert.alert-danger > ol > li";

    private StringBuffer verificationErrors = new StringBuffer();

    @Parameters("browser")
    @BeforeClass(alwaysRun = true)
    public void setUp(@Optional("firefox") String browser) throws Exception {
        initDrivers(browser);

        baseUrl = "http://automationpractice.com/index.php";
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    private void initDrivers(String browser) {
        // Здесь читаю путь к файлу конфигурации веб-драйверов
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

       // System.out.println("Тестируем на таких данных: {"+login+", "+password+", "+expectedMessage+"}");

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

       // Cчитывание пути к файлу конфигурации: 'automationpractice.common', где находится путь к xls
        String commonProperties = System.getProperty("automationpractice.cfg"); //automationpractice.cfg
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(commonProperties));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pathExcData=properties.getProperty("automation.auth.data.exc");
//        System.out.println("считали путь к эксель файлу с данными - " + pathExcData);

        return readTestData(); //вызов метода считывания из эксель - возвращает двумерный массив object
    }


    // считывание из Эксель через List
    private Object[][] readTestData() {
        AuthData authData = new AuthData();
        //List для наполнения из xls
        List<AuthData> authDataList= new ArrayList<>();

        // Читаем из excel файла
        try (XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(pathExcData))) {
            XSSFSheet sheet = workbook.getSheet("Лист1");
            for (int r=1; r<=sheet.getLastRowNum(); r++) {
                authData = new AuthData();

                XSSFRow row = sheet.getRow(r); //r будет приходящий индекс на вариант ТС

                if (row.getCell(0)==null)authData.setEmail("");
                else authData.setEmail(row.getCell(0).getStringCellValue()); //tempObject[r - 1][0] = row.getCell(0).getStringCellValue();

                if (row.getCell(1)==null) authData.setPassword("");
                else authData.setPassword(row.getCell(1).getStringCellValue());

                if (row.getCell(2)==null) authData.setErrorMessage("");
                else authData.setErrorMessage(row.getCell(2).getStringCellValue());

                // это понадобится, когда захотим сделать положительный сценарий
                //      if (row.getCell(3)==null) authData.setTypeScenario("");
                //      else authData.setTypeScenario(row.getCell(3).getStringCellValue());
                authDataList.add(authData);// запись в List
                }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Object[][] tempObject = new Object[authDataList.size()][3]; //массив требует четкие границы!!!

        //переносим List в двумерный массив
        for (int i=0; i<authDataList.size(); i++) {
            tempObject[i][0] = authDataList.get(i).getEmail();
            tempObject[i][1] = authDataList.get(i).getPassword();
            tempObject[i][2] = authDataList.get(i).getErrorMessage();
            }
        return tempObject;
    }
}