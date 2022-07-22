package com.skynest;

import com.skynest.models.BucketResponse;
import com.skynest.models.EditBucketRequest;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;

public class EditBucketTest extends BucketBaseTest {

    @Test
    void logged_admin_should_be_able_to_edit_specific_bucket_with_valid_inputs() {
        EditBucketRequest editBucketRequest = EditBucketRequest.generateValidBucketEditRequest();
        Response response = skyNestBackendClient.editBucket(editBucketRequest, createdBucketResponse.getBucketId());
        response.then().statusCode(SC_OK);

        BucketResponse editBucketResponse = response.as(BucketResponse.class);

        assertEquals(editBucketResponse.getName(), editBucketRequest.getName());
        assertEquals(editBucketResponse.getDescription(), editBucketRequest.getDescription());
        assertEquals(editBucketResponse.getIsPublic(), editBucketRequest.getIsPublic());

        createdBucketResponse = editBucketResponse;
    }

    @Test(dataProvider = "invalidBucketData")
    void logged_admin_should_not_be_able_to_edit_specific_bucket_with_invalid_inputs(EditBucketRequest editBucketRequest) {
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

}
