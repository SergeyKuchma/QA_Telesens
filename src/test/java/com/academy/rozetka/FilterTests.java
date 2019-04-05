/*   4) (Сергей) Тест "Фильтр"
        - перейти на главную страницу сайта https://rozetka.com.ua
        - пойти по ссылке в категорию "Одежда, обувь и украшения" -> "Блузки и рубашки"
        - выбрать фильтр по цене (от 200 до 500)
        -* 	либо установить фильтр по цене с помощью ползунка
        - проверить, что цена на все позиции товара в диапазоне от 200 до 500
        (см. скриншоты в директории '4_rozetka_filter')

работает!!! но пока на прямых ссылках, с ожиданиями и без доп.классов!!!!


*/

package com.academy.rozetka;

import com.academy.framework.BaseTest;
import com.academy.rozetka.page.BlousesPage;
import com.academy.rozetka.page.MainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import javax.swing.*;

public class FilterTests extends BaseTest {
    private String baseUrl = "https://rozetka.com.ua/ua/";

    @Test
    public void testFilterByPrice() {
        driver.get(baseUrl);

        String locatorMainLink = "body > app-root > div > div:nth-child(2) > div.app-rz-main-page > div > aside > main-page-sidebar > sidebar-fat-menu > div > ul > li:nth-child(9) > a";
        WebElement mainLink = driver.findElement(By.cssSelector(locatorMainLink));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Actions action = new Actions(driver);
        action.moveToElement(mainLink)
                .build()
                .perform();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.cssSelector("body > app-root > div > div:nth-child(2) > div.app-rz-header > header > div > div.header-bottomline > div.menu-outer.js-rz-fat-menu > fat-menu > div > ul > li.menu-categories__item.menu-categories__item_state_hovered > div > div.menu__main-cats > div.menu__main-cats-inner > div:nth-child(1) > ul:nth-child(1) > li > ul > li:nth-child(4) > a")).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.xpath("/html/body/app-root/div/div[1]/div[3]/div[1]/main/div[2]/aside/app-sidebar/app-filters/app-slider-filter/div/div/form/fieldset/div/input[1]")).click();
        driver.findElement(By.xpath("/html/body/app-root/div/div[1]/div[3]/div[1]/main/div[2]/aside/app-sidebar/app-filters/app-slider-filter/div/div/form/fieldset/div/input[1]")).clear();
        driver.findElement(By.xpath("/html/body/app-root/div/div[1]/div[3]/div[1]/main/div[2]/aside/app-sidebar/app-filters/app-slider-filter/div/div/form/fieldset/div/input[1]")).sendKeys("200");

        driver.findElement(By.xpath("/html/body/app-root/div/div[1]/div[3]/div[1]/main/div[2]/aside/app-sidebar/app-filters/app-slider-filter/div/div/form/fieldset/div/input[2]")).click();
        driver.findElement(By.xpath("/html/body/app-root/div/div[1]/div[3]/div[1]/main/div[2]/aside/app-sidebar/app-filters/app-slider-filter/div/div/form/fieldset/div/input[2]")).clear();
        driver.findElement(By.xpath("/html/body/app-root/div/div[1]/div[3]/div[1]/main/div[2]/aside/app-sidebar/app-filters/app-slider-filter/div/div/form/fieldset/div/input[2]")).sendKeys("500");

        driver.findElement(By.xpath("/html/body/app-root/div/div[1]/div[3]/div[1]/main/div[2]/aside/app-sidebar/app-filters/app-slider-filter/div/div/form/fieldset/div/button")).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //String max=driver.findElements(By.xpath("/html/body/app-root/div/div[1]/div[3]/div[1]/main/div[2]/section/app-goods/ul/li[1]/app-goods-tile/app-goods-tile-default/div/div[2]/div[4]/div[2]/p/span[i]")).getText();
        String priceList[] = new String[61];
        for (int i=1;i<=60;i++) {
            priceList[i] = driver.findElement(By.xpath("/html/body/app-root/div/div[1]/div[3]/div[1]/main/div[2]/section/app-goods/ul/li["+i+"]/app-goods-tile/app-goods-tile-default/div/div[2]/div[4]/div[2]/p/span[1]")).getText();
            if(Integer.parseInt(priceList[i])<200 | Integer.parseInt(priceList[i])>500)
                System.out.println("Цена вышла из заданного диапазована и ="+priceList[i]);
            else System.out.println("Цена в диапазоне ="+priceList[i]);
        }

    }

    protected boolean waitForJSandJQueryToLoad() {

        WebDriverWait wait = new WebDriverWait(driver, 30);

        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = driver -> {
            try {
                return ((Long)((JavascriptExecutor)driver).executeScript("return jQuery.active") == 0);
            }
            catch (Exception e) {
                // no jQuery present
                return true;
            }
        };

        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor)driver).executeScript("return document.readyState")
                .toString().equals("complete");

        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }
}