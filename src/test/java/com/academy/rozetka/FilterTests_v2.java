/*   4) (Сергей) Тест "Фильтр"
        - перейти на главную страницу сайта https://rozetka.com.ua
        - пойти по ссылке в категорию "Одежда, обувь и украшения" -> "Блузки и рубашки"
        - выбрать фильтр по цене (от 200 до 500)
        -* 	либо установить фильтр по цене с помощью ползунка
        - проверить, что цена на все позиции товара в диапазоне от 200 до 500
        (см. скриншоты в директории '4_rozetka_filter')

работает!!! но пока на прямых ссылках, с ожиданиями !!!!


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
import org.testng.Assert;

import javax.swing.*;


public class FilterTests_v2 extends BaseTest {
    private String baseUrl = "https://rozetka.com.ua/ua/";
    private final int WATEMILLISECOND = 1000; // ожидание в миллисек


    @Test
    public void testFilterByPrice() {
        driver.get(baseUrl);

        System.out.println("ЗАПУСТИЛСЯ Тест Фильтра по цене для категории 'Одежда, обувь и украшения' -> 'Блузки и рубашки' ___");

        MainPage mainPage = new MainPage(driver);
        mainPage.waitingTime(WATEMILLISECOND);
        mainPage.selectMainMenu(); //выделяем пункт меню
        mainPage.waitingTime(WATEMILLISECOND);
        BlousesPage blousesPage = mainPage.clickBlousesLink();//нажимаем на нужный пункт подменю
        mainPage.waitingTime(WATEMILLISECOND); //возможно нужно этот метод перенести и в BlousesPage - либо в BasePage

        blousesPage.enterMinPrice("200");// вводим минимальную цену
        blousesPage.enterMaxPrice("500");// вводим максимальную цену
        blousesPage.submit(true); // нажимаем ОК
        mainPage.waitingTime(WATEMILLISECOND*2); //возможно нужно этот метод перенести и в BlousesPage - либо в BasePage


        //запись всех цен со страницы в массив (всего 60 элементов на странице
       //это надо преобразовать через метод получения всех элементов
        String priceList[] = new String[61];
        int countOfIncorrectPrice=0; //счетчик цен, не попадающих в диапазон
        for (int i=1;i<=60;i++) {
            priceList[i] = driver.findElement(By.xpath("/html/body/app-root/div/div[1]/div[3]/div[1]/main/div[2]/section/app-goods/ul/li["+i+"]/app-goods-tile/app-goods-tile-default/div/div[2]/div[4]/div[2]/p/span[1]")).getText();
            if(Integer.parseInt(priceList[i])<200 | Integer.parseInt(priceList[i])>500) {
                System.out.println("Цена вышла из заданного диапазована и =" + priceList[i]);
                countOfIncorrectPrice++;
            }
        }
       Assert.assertEquals(countOfIncorrectPrice,0);

        if(countOfIncorrectPrice==0) System.out.println("Все цены в заданном диапазоне");
    }

}
