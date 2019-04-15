package com.academy.localhost.page;

import com.academy.framework.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SubscribersPage extends BasePage {

    private final String addBtnLocator = "add";
    @FindBy(id = addBtnLocator)
    private WebElement clickAddLink;

    private final String deleteBtnLocator = "delete";
    @FindBy(id = deleteBtnLocator)
    private WebElement clickDeleteLink;


    public AddPage clickAddLink() {
        clickAddLink.click();
        return new AddPage(driver);
    }

    public SubscribersPage clickDeleteLink() {
        clickDeleteLink.click();
        return this;//new SubscribersPage(driver);
    }

    public SubscribersPage(WebDriver driver) {
        super(driver);
    }

    // ожидание в миллисекундах
    public void waitingTime(int waitingMilliSecond) {
        try {
            Thread.sleep(waitingMilliSecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}