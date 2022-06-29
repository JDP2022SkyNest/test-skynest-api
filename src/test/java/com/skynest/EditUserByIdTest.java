package com.skynest;

import com.skynest.models.EditRequest;
import com.skynest.models.EditResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.apache.http.HttpStatus.SC_OK;

public class EditUserByIdTest extends LoggedUserBaseTest {
    @Test(dataProvider = "UserData")
    void valid_edit_user_by_id_test(EditRequest editRequest) throws IOException {
        Response response = skyNestBackendClient.editUserById(editRequest);
        response.then().statusCode(SC_OK);

        EditResponse editResponse = response.as(EditResponse.class);

        Assert.assertNotNull(editResponse.getId());

        Assert.assertEquals(editResponse.getName(), editRequest.getName());
        Assert.assertEquals(editResponse.getSurname(), editRequest.getSurname());
        Assert.assertEquals(editResponse.getPhoneNumber(), editRequest.getPhoneNumber());
        Assert.assertEquals(editResponse.getAddress(), editRequest.getAddress());
    }

    @DataProvider(name = "UserData")
    public Object[][] getUserData() {
        return new Object[][]{
                new Object[]{EditRequest.generateValidEditRequest()}};
    }
}
