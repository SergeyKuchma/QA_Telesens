package com.academy.rozetka.page;

import com.academy.framework.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.interactions.Actions;

public class MainPage extends BasePage {

    private final String enterLinkLocator = "body > app-root > div > div:nth-child(2) > div.app-rz-header > header > div > div.header-topline > div.header-topline__user.js-rz-auth > div.header-topline__user-wrapper > p > a";
    //    @FindBy(css = "body > app-root > div > div:nth-child(2) > div.app-rz-header > header > div > div.header-topline > div.header-topline__user.js-rz-auth > div.header-topline__user-wrapper > p > a")
    @FindBy(css = enterLinkLocator)
    private WebElement enterLink;


    //добавляем ссылку на страницу "Одежда, обувь и украшения" -> "Блузки и рубашки"
    // не работает данная ссылка "//a[@href='https://rozetka.com.ua/genskie-bluzi-i-rubashki/c4637567/']", поэтому разобъем ее на две
    // для этого сначала нужно навести на "Одежда, обувь и украшения"
    private final String locatorMainLinkClothes = "body > app-root > div > div:nth-child(2) > div.app-rz-main-page > div > aside > main-page-sidebar > sidebar-fat-menu > div > ul > li:nth-child(9) > a";
    @FindBy(css = locatorMainLinkClothes)
    private WebElement clothesLink;

    // а затем наводим на -> "Блузки и рубашки"
    private final String blousesLinkLocator ="body > app-root > div > div:nth-child(2) > div.app-rz-header > header > div > div.header-bottomline > div.menu-outer.js-rz-fat-menu > fat-menu > div > ul > li.menu-categories__item.menu-categories__item_state_hovered > div > div.menu__main-cats > div.menu__main-cats-inner > div:nth-child(1) > ul:nth-child(1) > li > ul > li:nth-child(4) > a";
    @FindBy(css = blousesLinkLocator)
    private WebElement blousesLink;


    public BlousesPage clickBlousesLink() {
        blousesLink.click();
        return new BlousesPage(driver);
    }

    public MainPage(WebDriver driver) {
        super(driver);
    }

    //только наводит на пункт меню
    public void selectMainMenu(){
        Actions action = new Actions(driver);
        action.moveToElement(clothesLink)
                .build()
                .perform();
    }

    // ожидание в миллисекундах
    public void waitingTime(int waitingMilliSecond) {
        try {
            Thread.sleep(waitingMilliSecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getEnterLinkText() {
        return enterLink.getText().trim();
    }

    public AuthFormPage clickEnterLink() {
        enterLink.click();
        return new AuthFormPage(driver);
    }

    public MainPage waitUntilLinkTextChanged(String oldMessage){
        waitUntilTextChanged(enterLinkLocator, oldMessage);
        return this;
    }
}