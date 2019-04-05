package com.academy.rozetka;

import com.academy.framework.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthTests_old extends BaseTest {

    protected String baseUrl = "https://rozetka.com.ua/ua/";
    private String linkEnterLocator = "body > app-root > div > div:nth-child(2) > div.app-rz-header > header > div > div.header-topline > div.header-topline__user.js-rz-auth > div.header-topline__user-wrapper > p > a";

    @Test
    public void testSuccessAuth() {
        System.out.println("***Rozetka test Auth start***");
        driver.get(baseUrl);
        WebElement enterLink = driver.findElement(By.cssSelector(linkEnterLocator));
        String oldMessage = enterLink.getText();
        enterLink.click();
        WebElement loginField = driver.findElement(By.id("auth_email"));
        loginField.click();
        loginField.clear();
        loginField.sendKeys("oleg.kh81@gmail.com"); //oleg.kh81@gmail.com

        WebElement passwordField = driver.findElement(By.id("auth_pass"));
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys("Qwerty123"); //

        driver.findElement(By.cssSelector("body > app-root > div > div:nth-child(2) > div.app-rz-common > auth-modal > modal-window > div > div > div > auth-content > div > form > div > button")).click();
        // Подождать, пока не закроется всплывающее окно
        WebDriverWait webDriverWait = new WebDriverWait(driver, 5);

        webDriverWait.until(ExpectedConditions.not(
                ExpectedConditions.textToBe(By.cssSelector(linkEnterLocator), oldMessage)));
        String userName = driver.findElement(By.cssSelector("body > app-root > div > div:nth-child(2) > div.app-rz-header > header > div > div.header-topline > div.header-topline__user.js-rz-auth > div.header-topline__user-wrapper > p > a")).getText().trim();
        Assert.assertEquals(userName, "Oleg"); //"Oleg"
    }
}