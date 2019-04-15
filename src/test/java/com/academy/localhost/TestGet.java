//Тестирование Rest-Assured

package com.academy.localhost;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestGet {

@Test
public void testGet() {
RequestSpecification request = RestAssured.given();
    Response response = request.get("http://localhost:8081/rest/xml/subscribers/1"); //subscribers/1
    ResponseBody body = response.body();
    XmlPath xmlPath = body.xmlPath();
    int  id = xmlPath.getInt("subscriber.id");
    String  name = xmlPath.getString("subscriber.firstName");

    int code = response.statusCode();

    Assert.assertEquals(code, 200);
    Assert.assertEquals(id, 1);
    Assert.assertEquals(name, name);
}

}
