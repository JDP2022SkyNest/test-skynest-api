package com.skynest;

import com.skynest.models.EditRequest;
import com.skynest.models.EditResponse;
import com.skynest.models.UserResponse;
import com.skynest.utils.JsonTransformer;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static org.apache.http.HttpStatus.SC_OK;

public class EditUserByIdTest extends BaseTest {
    private static final String WORKER_EMAIL = "fedese7585@kahase.com";

    @Test(dataProvider = "UserData")
    void edit_admin_details_as_admin_test(EditRequest editRequest) throws IOException {
        loginAs(Roles.ADMIN);
        UUID loggedUserId = getLoggedUserId();
        Response editUserResponse = skyNestBackendClient.editUserById(editRequest, loggedUserId);
        editUserResponse.then().statusCode(SC_OK);

        EditResponse editResponse = editUserResponse.as(EditResponse.class);

        Assert.assertNotNull(editResponse.getId());

        Assert.assertEquals(editResponse.getName(), editRequest.getName());
        Assert.assertEquals(editResponse.getSurname(), editRequest.getSurname());
        Assert.assertEquals(editResponse.getPhoneNumber(), editRequest.getPhoneNumber());
        Assert.assertEquals(editResponse.getAddress(), editRequest.getAddress());
        Assert.assertEquals(editResponse.getPositionInCompany(), editRequest.getPositionInCompany());
    }

    @DataProvider(name = "UserData")
    public Object[][] getUserData() {
        return new Object[][]{new Object[]{EditRequest.generateValidEditRequest()}};
    }

    @Test(dataProvider = "UserData")
    void edit_random_worker_details_as_admin_test(EditRequest editRequest) throws IOException {
        loginAs(Roles.ADMIN);
        Response getAllUsersResponse = skyNestBackendClient.getAllUsers();
        getAllUsersResponse.then().statusCode(SC_OK);

        List<UserResponse> userResponses = JsonTransformer.mapResponseToList(getAllUsersResponse, UserResponse.class);
        UUID firstWorkerId = getRandomWorkerId(userResponses);


        Response editUserResponse = skyNestBackendClient.editUserById(editRequest, firstWorkerId);
        editUserResponse.then().statusCode(SC_OK);

        EditResponse editResponse = editUserResponse.as(EditResponse.class);

        Assert.assertNotNull(editResponse.getId());

        Assert.assertEquals(editResponse.getName(), editRequest.getName());
        Assert.assertEquals(editResponse.getSurname(), editRequest.getSurname());
        Assert.assertEquals(editResponse.getPhoneNumber(), editRequest.getPhoneNumber());
        Assert.assertEquals(editResponse.getAddress(), editRequest.getAddress());
        Assert.assertEquals(editResponse.getPositionInCompany(), editRequest.getPositionInCompany());
    }

    private UUID getRandomWorkerId(List<UserResponse> userResponses) {
        for (int i = 0; i < userResponses.size(); i++) {
            UserResponse userResponse = userResponses.get(i);
            if (userResponse.getEmail().equals(WORKER_EMAIL)) {
                return userResponse.getId();
            }
        }
        return null;
    }
}
