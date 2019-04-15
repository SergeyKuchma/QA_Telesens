/*

запускаем БД, затем приложение, а потом уже по http://localhost:8081/subscribers
- добавляем нового абонента через UI
- проверяем, что последняя строка соответствует добавленному subscriber
-

*/

package com.academy.localhost;

import com.academy.framework.BaseTest;
import com.academy.localhost.page.AddPage;
import com.academy.localhost.page.SubscribersPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;


public class LocalhostUITests extends BaseTest {
    private String baseUrl = "http://localhost:8081/subscribers";
//    private final int WATEMILLISECOND = 1000; // ожидание в миллисек


    @Test
    public void testSubscriber() {
        driver.get(baseUrl);

        System.out.println(" ЗАПУСТИЛСЯ Тест добавления нового абонента ");
        SubscribersPage subscribersPage= new SubscribersPage(driver);
        AddPage addPage = subscribersPage.clickAddLink();
        addPage.enterFirstName("Иван");
        addPage.enterLastName("Иванов");
        addPage.maleClick();
        addPage.enterAge("88");
        addPage.submit(true);


        List<WebElement> subscribers_id = driver.findElements(By.xpath("/html/body/div/div[4]/div/table/tbody/tr[*]/td[2]"));
        List<WebElement> subscribers_firstName = driver.findElements(By.xpath("/html/body/div/div[4]/div/table/tbody/tr[*]/td[3]"));
        List<WebElement> subscribers_lastName = driver.findElements(By.xpath("/html/body/div/div[4]/div/table/tbody/tr[*]/td[4]"));
        List<WebElement> subscribers_age = driver.findElements(By.xpath("/html/body/div/div[4]/div/table/tbody/tr[*]/td[5]"));
        List<WebElement> subscribers_gender = driver.findElements(By.xpath("/html/body/div/div[4]/div/table/tbody/tr[*]/td[6]"));

       //вывод всех массивов
        for (int i=0;i<subscribers_id.size();i++) {
            System.out.println("{" + subscribers_id.get(i).getText() + " " + subscribers_firstName.get(i).getText() + " " + subscribers_lastName.get(i).getText() + " " + subscribers_age.get(i).getText() + " " + subscribers_gender.get(i).getText() + "}");
        }
    //    System.out.println("все имена:");
    //    subscribers_firstName.stream().map(WebElement::getText).forEach(System.out::println);

        Assert.assertEquals(subscribers_firstName.get(subscribers_id.size()-1).getText(),"Иван");
        Assert.assertEquals(subscribers_lastName.get(subscribers_id.size()-1).getText(),"Иванов");
        Assert.assertEquals(subscribers_gender.get(subscribers_id.size()-1).getText(),"м");
        Assert.assertEquals(subscribers_age.get(subscribers_id.size()-1).getText(),"88");

    }

}
