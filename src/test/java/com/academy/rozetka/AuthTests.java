package com.academy.rozetka;

import com.academy.framework.BaseTest;
import com.academy.framework.util.PropertyReader;
import com.academy.rozetka.page.AuthFormPage;
import com.academy.rozetka.page.MainPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AuthTests extends BaseTest {
    private final static Logger LOG = LogManager.getLogger(com.academy.automationpractice.AuthTests.class);

    protected String baseUrl = "https://rozetka.com.ua/ua/";

    @Test(dataProvider = "testSuccessAuthDataProvider")
    public void testSuccessAuth(String login, String password, String userNameExpected) {
        driver.get(baseUrl);

        MainPage mainPage = new MainPage(driver);
        String oldMessage = mainPage.getEnterLinkText();
        AuthFormPage authFormPage = mainPage.clickEnterLink();

        authFormPage.enterLogin(login);
        authFormPage.enterPassword(password);
        mainPage = (MainPage)authFormPage.submit(true);

        mainPage = mainPage.waitUntilLinkTextChanged(oldMessage);
        String userNameActual = mainPage.getEnterLinkText();
        Assert.assertEquals(userNameActual, userNameExpected);
    }

    @DataProvider(name="testSuccessAuthDataProvider")
    private Object[][] testSuccessAuthDataProvider() {
//        String login = "oleg.kh81@gmail.com";
        String login = PropertyReader.from("rozetka").getProperty("login");
        String password = PropertyReader.from("rozetka").getProperty("password");
//        String userNameExpected = "Oleg";
        String userNameExpected = PropertyReader.from("rozetka").getProperty("username");

        return new Object[][] {
                {login, password, userNameExpected}
        };
    }
}