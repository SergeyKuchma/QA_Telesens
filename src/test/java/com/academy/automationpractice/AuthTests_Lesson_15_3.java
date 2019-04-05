/*
3) Реализовать автотест для сайта automationpractice.com:
	Сценарий:
		- перейти на главную страницу по ссылке: http://automationpractice.com
		- в меню (WOMEN | DRESSES | T-SHIRTS) кликнуть пункт 'WOMEN'
		- Сделать следующие проверки:
			1) страница title содержит слово "Women"
			2) навигатор страниц содержит пункт "women"
			3) слева над каталогом расположен раздел "WOMEN"
			4) по середение содержится текст с заголовком "Women" и содержимое текста начинается
				"You will find here all woman fashion..."
			5) наличие под текстом в середине категории "WOMEN" "There are 7 products". (Число 7 может быть другим)
			6) в футоре внизу наличие "Women" в пункте "Categories"



1) считывает данные из файлов common.properties и automationpractice.properties
 commonProperties = "D:\\Java\\Program\\javapart\\src\\main\\resources\\common.properties
 automationpracticeProperties = "D:\\Java\\Program\\javapart\\src\\main\\resources\\automationpractice.properties






3) считывает данные для ТС из  d:/Java/Program/javapart/data/automationpractice-auth-data.xlsx
 В проекте создать файл в директории src/main/resources/automationpractice.properties, в котором прописать следующие свойства:

        # путь к файлу 'automationpractice-auth-data.xlsx'
        automation.auth.data.exc=<корень_проекта>/data/automationpractice-auth-data.xlsx
        # путь к файлу 'auth-data.txt'
        automation.auth.data.txt=<корень_проекта>/data/automationpractice-auth-data.txt

 */


package com.academy.automationpractice;

import com.academy.automationpractice.ex.TestScenario;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class AuthTests_Lesson_15_3 {
    private String commonProperties = "D:\\Java\\Program\\javapart\\src\\main\\resources\\common.properties";
    private String automationpracticeProperties = "D:\\Java\\Program\\javapart\\src\\main\\resources\\automationpractice.properties";
    private String errorMessageCssLocator = "#center_column > div.alert.alert-danger > ol > li";
    private String positiveMessageCssLocator = "#center_column > p.info-account";

    private WebDriver driver;
    private String baseUrl="http://automationpractice.com/index.php";
    private String geckoDriver;
    private String chromeDriver;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    //List для наполнения из xls
    List<TestScenario> testscenarioList= new ArrayList<>();


    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        initDrivers();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        readTCFromExel();//считываем данные для ТС из эксель

     }

    private void readTCFromExel() {
        TestScenario testscenario = new TestScenario();

        try (XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(getPathExcData()))) { //путь к xls считывается из properties файла
            XSSFSheet sheet = workbook.getSheet("Лист1");
            for (int r=1; r<=sheet.getLastRowNum(); r++) {
                testscenario = new TestScenario();

                XSSFRow row = sheet.getRow(r); //r будет приходящий индекс на вариант ТС

                try{testscenario.setLogin(row.getCell(0).getStringCellValue());}
                catch(NullPointerException ex){testscenario.setLogin("");} //what to do when the exception occurs?

                try{testscenario.setPassword(row.getCell(1).getStringCellValue());}
                catch(NullPointerException ex){testscenario.setPassword("");} //what to do when the exception occurs?

                try{testscenario.setExpectedMessage(row.getCell(2).getStringCellValue());}
                catch(NullPointerException ex){testscenario.setExpectedMessage("");} //what to do when the exception occurs?

                try{testscenario.setTypeScenario(row.getCell(3).getStringCellValue());}
                catch(NullPointerException ex){testscenario.setTypeScenario("");} //what to do when the exception occurs?

                testscenarioList.add(testscenario);// запись в List
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
       // System.out.println("Считанные данные из XLS в виде List: ");
       // System.out.println(testscenarioList);

    } // конец метода считывания данных из XLS

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

    //    String[] logins = {"","qwerty"};
    //    String[] passwords = {"123","qwerty"};
    //    String[] expectedMessages = {"An email address required.","Invalid email address."};

        for (int i =0; i< testscenarioList.size(); i++) {

            TestScenario TestScenarioCurrent = testscenarioList.get(i);

                // Заполняем логин и пароль
                WebElement loginField = driver.findElement(By.id("email"));
                loginField.click();
                loginField.clear();
                loginField.sendKeys(TestScenarioCurrent.getLogin());
                //  loginField.sendKeys(logins[i]);

                WebElement passwdField = driver.findElement(By.id("passwd"));
                passwdField.click();
                passwdField.clear();
                passwdField.sendKeys(TestScenarioCurrent.getPassword());
                //passwdField.sendKeys(passwords[i]);

                WebElement submitField = driver.findElement(By.id("SubmitLogin"));
                submitField.click();

            WebElement webElementWithErrorMessage;
            //If для отдельной проверки позитивных или негативных сценариев
                if (TestScenarioCurrent.getTypeScenario().equals("negative")) {
                    webElementWithErrorMessage = driver.findElement(By.cssSelector(errorMessageCssLocator));
                } else //positive сценарий;
                    //webElementWithErrorMessage = driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='My account'])[2]/following::p[1]")); этот вариант нахождения элемента тоже рабочий
            webElementWithErrorMessage = driver.findElement(By.cssSelector(positiveMessageCssLocator));


                String actualErrorMessage = webElementWithErrorMessage.getText();
                try {
                    assertEquals(actualErrorMessage, TestScenarioCurrent.getExpectedMessage());  //expectedMessages[i]
                    System.out.println("Отработал ТС для: " + TestScenarioCurrent);
                } catch (Error e) {
                    verificationErrors.append(e.toString());
                }

            if (TestScenarioCurrent.getTypeScenario().equals("positive"))
                driver.findElement(By.linkText("Sign out")).click(); // выходим, если удалось войти
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

    // считывание пути из файла properties
    private String getPathExcData(){
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(automationpracticeProperties));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty("automation.auth.data.exc");
    }
}