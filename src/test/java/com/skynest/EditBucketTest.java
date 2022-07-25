package com.skynest;

import com.skynest.models.BucketResponse;
import com.skynest.models.EditBucketRequest;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.UUID;

import static org.apache.http.HttpStatus.*;
import static org.testng.Assert.assertEquals;

public class EditBucketTest extends BucketBaseTest {

    @Test(dataProvider = "validBucketData")
    void logged_owner_should_be_able_to_edit_private_bucket_with_valid_inputs(EditBucketRequest editBucketRequest) {
        Response response = skyNestBackendClient.editBucket(editBucketRequest, createdBucketResponse.getBucketId());
        response.then().statusCode(SC_OK);

        BucketResponse editBucketResponse = response.as(BucketResponse.class);

        assertEquals(editBucketResponse.getName(), editBucketRequest.getName());
        assertEquals(editBucketResponse.getDescription(), editBucketRequest.getDescription());
        assertEquals(editBucketResponse.getIsPublic(), editBucketRequest.getIsPublic());

        createdBucketResponse = editBucketResponse;
    }

    @Test
    void every_logged_user_should_be_able_to_edit_public_bucket_with_valid_inputs() {
        loginAs(Roles.WORKER);
        EditBucketRequest editBucketRequest = EditBucketRequest.generateEditRequestForPublicBucket();
        Response response = skyNestBackendClient.editBucket(editBucketRequest, createdBucketResponse.getBucketId());
        response.then().statusCode(SC_OK);

        BucketResponse editBucketResponse = response.as(BucketResponse.class);

        assertEquals(editBucketResponse.getName(), editBucketRequest.getName());
        assertEquals(editBucketResponse.getDescription(), editBucketRequest.getDescription());
        assertEquals(editBucketResponse.getIsPublic(), editBucketRequest.getIsPublic());

        createdBucketResponse = editBucketResponse;
    }

    @Test
    void logged_user_should_not_be_able_to_edit_bucket_by_not_existing_id() {
        EditBucketRequest editBucketRequest = EditBucketRequest.generateValidBucketEditRequest();
        Response response = skyNestBackendClient.editBucket(editBucketRequest, UUID.randomUUID());
        response.then().statusCode(SC_NOT_FOUND);
    }

    @Test(dataProvider = "invalidBucketData")
    void logged_user_should_not_be_able_to_edit_specific_bucket_with_invalid_inputs(EditBucketRequest editBucketRequest) {
        Response response = skyNestBackendClient.editBucket(editBucketRequest, createdBucketResponse.getBucketId());
        response.then().statusCode(SC_BAD_REQUEST);
    }

    @DataProvider(name = "invalidBucketData")
    public Object[][] getInvalidBucketData() {
        return new Object[][]{
                new Object[]{EditBucketRequest.generateValidBucketEditRequest().withName("")},
                {EditBucketRequest.generateValidBucketEditRequest().withDescription("")},
                {EditBucketRequest.generateValidBucketEditRequest().withName(null)},
                {EditBucketRequest.generateValidBucketEditRequest().withDescription(null)},
                {EditBucketRequest.generateValidBucketEditRequest().withIsPublic(null)}
        };
    }

    @DataProvider(name = "validBucketData")
    public Object[][] getValidBucketData() {
        return new Object[][]{
                new Object[]{EditBucketRequest.generateValidBucketEditRequest()},
                {EditBucketRequest.generateValidBucketEditRequest().withName("Кирилица").withDescription("Опис на кирилица")},
                {EditBucketRequest.generateValidBucketEditRequest().withName("   Leading spaces").withDescription("Trailing spaces    ")}
        };
    }

}
