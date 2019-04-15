package com.academy.rozetka.page;

import com.academy.framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BlousesPage extends BasePage {

    private final String minPriceLinkLocator = "/html/body/app-root/div/div[1]/div[3]/div[1]/main/div[2]/aside/app-sidebar/app-filters/app-slider-filter/div/div/form/fieldset/div/input[1]";
    @FindBy(xpath= minPriceLinkLocator)
    private WebElement minPriceLink;

    private final String maxPriceLinkLocator = "/html/body/app-root/div/div[1]/div[3]/div[1]/main/div[2]/aside/app-sidebar/app-filters/app-slider-filter/div/div/form/fieldset/div/input[2]";
    @FindBy(xpath= maxPriceLinkLocator)
    private WebElement maxPriceLink;

    private final String submitButtonLinkLocator = "/html/body/app-root/div/div[1]/div[3]/div[1]/main/div[2]/aside/app-sidebar/app-filters/app-slider-filter/div/div/form/fieldset/div/button";
    @FindBy(xpath= submitButtonLinkLocator)
    private WebElement submitButtonLink;

    private final String allPricesLinkLocator = "/html/body/app-root/div/div[1]/div[3]/div[1]/main/div[2]/section/app-goods/ul/li/app-goods-tile/app-goods-tile-default/div/div[2]/div[4]/div[2]/p/span[1]";
    @FindBy(xpath= allPricesLinkLocator)
    private WebElement allPricesLink;


    //вводим минимальное значение
    public BlousesPage enterMinPrice (String minPrice) {
        enterText(minPriceLink, minPrice);
        return this;
    }

    //вводим максимальное значение
    public BlousesPage enterMaxPrice (String maxPrice) {
        enterText(maxPriceLink, maxPrice);
        return this;
    }

    public BasePage submit (boolean isCorrect){
        submitButtonLink.click();
        if (isCorrect)
            return new BlousesPage (driver);
        else
            return this;
    }


    public BlousesPage(WebDriver driver) {
        super(driver);
    }

}
