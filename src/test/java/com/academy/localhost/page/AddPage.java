package com.academy.localhost.page;

import com.academy.framework.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddPage extends BasePage {

    private final String firstNameLocator = "fname";
    @FindBy(id = firstNameLocator)
    private WebElement firstNameLink;

    private final String lastNameLocator = "lname";
    @FindBy(id = lastNameLocator)
    private WebElement lastNameLink;

    //если пол мужской
    private final String maleLocator = "//*[@id=\"MALE\"]";
    @FindBy(xpath = maleLocator)
    private WebElement maleLink;

    //если пол женский
    private final String femaleLocator = "//*[@id=\"FEMALE\"]";
    @FindBy(xpath = femaleLocator)
    private WebElement femaleLink;


    private final String ageLocator = "age";
    @FindBy(id = ageLocator)
    private WebElement ageLink;


    private final String submitLocator = "/html/body/div/form/button";
    @FindBy(xpath = submitLocator)
    private WebElement submitLink;

    //вводим Имя
    public AddPage enterFirstName (String firstName) {
        enterText(firstNameLink, firstName);
        return this;
    }

    //вводим Фамилию
    public AddPage enterLastName (String lastName) {
        enterText(lastNameLink, lastName);
        return this;
    }

    //вводим Age
    public AddPage enterAge (String age) {
        enterText(ageLink, age);
        return this;
    }

    public AddPage maleClick (){
        maleLink.click();
        return this;
    }

    public AddPage femaleClick (){
        femaleLink.click();
        return this;
    }

    public BasePage submit (boolean isCorrect){
        submitLink.click();
        if (isCorrect)
            return new SubscribersPage(driver);
        else
            return new SubscribersPage(driver);
    }

    public AddPage(WebDriver driver) {
        super(driver);
    }
}
