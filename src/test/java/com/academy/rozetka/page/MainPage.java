package com.academy.rozetka.page;

import com.academy.framework.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {

    private final String enterLinkLocator = "body > app-root > div > div:nth-child(2) > div.app-rz-header > header > div > div.header-topline > div.header-topline__user.js-rz-auth > div.header-topline__user-wrapper > p > a";
    //    @FindBy(css = "body > app-root > div > div:nth-child(2) > div.app-rz-header > header > div > div.header-topline > div.header-topline__user.js-rz-auth > div.header-topline__user-wrapper > p > a")
    @FindBy(css = enterLinkLocator)
    private WebElement enterLink;


    //добавляем ссылку на страницу "Одежда, обувь и украшения" -> "Блузки и рубашки"
    //body > app-root > div > div:nth-child(2) > div.app-rz-header > header > div > div.header-bottomline > div.menu-outer.js-rz-fat-menu > fat-menu > div > ul > li.menu-categories__item.menu-categories__item_state_hovered > div > div.menu__main-cats > div.menu__main-cats-inner > div:nth-child(1) > ul:nth-child(1) > li > ul > li:nth-child(4) > a

    private final String blousesLinkLocator ="//a[@href='https://rozetka.com.ua/genskie-bluzi-i-rubashki/c4637567/']";
    @FindBy(xpath = blousesLinkLocator)
    private WebElement blousesLink;


    public BlousesPage clickBlousesLink() {
        blousesLink.click();
        return new BlousesPage(driver);
    }

    public MainPage(WebDriver driver) {
        super(driver);
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