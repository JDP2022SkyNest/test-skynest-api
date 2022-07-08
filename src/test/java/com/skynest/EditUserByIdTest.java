package com.skynest;

import com.skynest.models.EditRequest;
import com.skynest.models.EditResponse;
import com.skynest.models.UserResponse;
import com.skynest.utils.JsonTransformer;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

public class EditUserByIdTest extends BaseTest {
    private static final String WORKER_EMAIL = "fedese7585@kahase.com";

    @Test(dataProvider = "UserData")
    void edit_admin_details_as_admin_test(EditRequest editRequest) {
        loginAs(Roles.ADMIN);
        UUID loggedUserId = getLoggedUserId();

        Response editUserResponse = skyNestBackendClient.editUserById(editRequest, loggedUserId);
        editUserResponse.then().statusCode(SC_OK);

        EditResponse editResponse = editUserResponse.as(EditResponse.class);

        assertNotNull(editResponse.getId());

        assertEquals(editResponse.getName(), editRequest.getName());
        assertEquals(editResponse.getSurname(), editRequest.getSurname());
        assertEquals(editResponse.getPhoneNumber(), editRequest.getPhoneNumber());
        assertEquals(editResponse.getAddress(), editRequest.getAddress());
        assertEquals(editResponse.getPositionInCompany(), editRequest.getPositionInCompany());
    }

    @DataProvider(name = "UserData")
    public Object[][] getUserData() {
        return new Object[][]{
                new Object[]{EditRequest.generateValidEditRequest()}};
    }

    @Test(dataProvider = "UserData")
    void edit_random_worker_details_as_admin_test(EditRequest editRequest) throws IOException {
        loginAs(Roles.ADMIN);
        Response getAllUsersResponse = skyNestBackendClient.getAllUsers();
        getAllUsersResponse.then().statusCode(SC_OK);

        List<UserResponse> userResponses = JsonTransformer.mapResponseToList(getAllUsersResponse, UserResponse.class);

        UUID specificWorkerId = getSpecificWorkerId(userResponses);

        Response editUserResponse = skyNestBackendClient.editUserById(editRequest, specificWorkerId);
        editUserResponse.then().statusCode(SC_OK);

        EditResponse editResponse = editUserResponse.as(EditResponse.class);

        assertNotNull(editResponse.getId());

        assertEquals(editResponse.getName(), editRequest.getName());
        assertEquals(editResponse.getSurname(), editRequest.getSurname());
        assertEquals(editResponse.getPhoneNumber(), editRequest.getPhoneNumber());
        assertEquals(editResponse.getAddress(), editRequest.getAddress());
        assertEquals(editResponse.getPositionInCompany(), editRequest.getPositionInCompany());
    }

    private UUID getSpecificWorkerId(List<UserResponse> userResponses) {
        for (int i = 0; i < userResponses.size(); i++) {
            UserResponse userResponse = userResponses.get(i);
            if (userResponse.getEmail().equals(WORKER_EMAIL)) {
                return userResponse.getId();
            }
        }
        return null;
    }
}
