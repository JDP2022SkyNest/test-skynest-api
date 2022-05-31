package com.skynest.tests.tests;

import com.skynest.tests.files.EndpointConstants;
import com.skynest.tests.files.Payload;
import com.skynest.tests.files.ReusableMethods;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class RegistrationTest extends BaseTest{
    @Test(dataProvider = "UserData")
    void registering_new_valid_user_should_return_specified_response(String email, String pass, String name, String surname, String phoneNo, String address){
        String response = given().log().all().header("Content-Type","application/json")
                .body(Payload.userRegistrationData(email,pass,name,surname,phoneNo,address))
                .when()
                .post(EndpointConstants.registerUrl)
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js= ReusableMethods.rawToJson(response);
        String id = js.getString("id");
        System.out.println("The unique id of the registered user is: "+id);
        String firstName = js.getString("name");
        System.out.println("The first name of the registered user in the response body is: "+firstName);
        Assert.assertEquals(firstName,name);
    }
    @DataProvider(name="UserData")
    public Object [][] getUserData(){
        return new Object [][] {{"hris1234567@gmail.com","abcd1234576","Anaana1","Francfranc1","77857452185375","test85757574398"}};
    }
}
